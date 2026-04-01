#!/usr/bin/env python3
"""
Differential fuzzer for RADDOSE-3D Java vs Rust.

Usage:
    python fuzz.py [options]

Strategies:
    grammar    -- generate inputs from the full grammar (default)
    mutate     -- mutate seed files from corpus/seeds/
    both       -- interleave grammar and mutation (50/50)

Output is written under corpus/:
    corpus/diffs/         -- inputs with significant numerical differences
    corpus/crashes/java/  -- inputs where Java crashes, Rust succeeds
    corpus/crashes/rust/  -- inputs where Rust crashes, Java succeeds
    corpus/crashes/both/  -- inputs where both crash
    results/              -- per-run JSON logs and aggregate stats
"""

import argparse
import json
import os
import random
import shutil
import sys
import tempfile
import time
from collections import Counter
from concurrent.futures import ThreadPoolExecutor, as_completed
from pathlib import Path

# Add fuzz/ dir to sys.path so sibling imports work
sys.path.insert(0, str(Path(__file__).parent))

from compare import Category, compare
from generate import (
    DEFAULT_BUDGET, Config, GrammarGenerator,
    estimate_cost, mutate_text, render,
)
from harness import (
    DEFAULT_JAVA_JAR, DEFAULT_RUST_BIN, DEFAULT_TIMEOUT, run_both,
)

FUZZ_DIR = Path(__file__).parent
CORPUS_DIR = FUZZ_DIR / "corpus"
RESULTS_DIR = FUZZ_DIR / "results"
SEEDS_DIR = CORPUS_DIR / "seeds"


def main():
    ap = argparse.ArgumentParser(description="RADDOSE-3D differential fuzzer")
    ap.add_argument("--iterations", "-n", type=int, default=100,
                    help="Number of fuzz iterations (default: 100)")
    ap.add_argument("--strategy", choices=["grammar", "mutate", "both"],
                    default="both", help="Input generation strategy (default: both)")
    ap.add_argument("--budget", type=float, default=DEFAULT_BUDGET,
                    help=f"Max cost relative to insulin (default: {DEFAULT_BUDGET})")
    ap.add_argument("--timeout", type=float, default=DEFAULT_TIMEOUT,
                    help=f"Per-run wall-clock timeout in seconds (default: {DEFAULT_TIMEOUT})")
    ap.add_argument("--java-jar", type=Path, default=DEFAULT_JAVA_JAR)
    ap.add_argument("--rust-bin", type=Path, default=DEFAULT_RUST_BIN)
    ap.add_argument("--seed", type=int, default=None,
                    help="RNG seed for reproducibility")
    ap.add_argument("--save-all", action="store_true",
                    help="Save every input, not just interesting ones")
    ap.add_argument("--minor-diff-threshold", type=float, default=1e-2,
                    help="Relative diff threshold for MAJOR_DIFF (default: 0.01 = 1%%)")
    ap.add_argument("--workers", "-j", type=int, default=4,
                    help="Parallel workers (default: 4). Workers wait on subprocesses so "
                         "this can exceed CPU count. Each worker runs one Java+Rust pair.")
    args = ap.parse_args()

    # Override compare module threshold if requested
    import compare as compare_mod
    compare_mod.TOL_MINOR = args.minor_diff_threshold

    rng = random.Random(args.seed)
    grammar_gen = GrammarGenerator(budget=args.budget, rng=rng)
    seeds = _load_seeds()

    RESULTS_DIR.mkdir(parents=True, exist_ok=True)
    run_id = time.strftime("%Y%m%d_%H%M%S")
    log_path = RESULTS_DIR / f"run_{run_id}.jsonl"
    stats_path = RESULTS_DIR / f"run_{run_id}_stats.json"

    category_counts: Counter = Counter()
    interesting_saved = 0
    total_java_time = 0.0
    total_rust_time = 0.0

    print(f"RADDOSE-3D differential fuzzer  |  {args.iterations} iterations  |  "
          f"strategy={args.strategy}  |  budget={args.budget}x  |  "
          f"timeout={args.timeout}s  |  workers={args.workers}")
    print(f"Java:  {args.java_jar}")
    print(f"Rust:  {args.rust_bin}")
    print(f"Log:   {log_path}")
    print()

    # Pre-generate all inputs up front (fast, single-threaded) so the RNG
    # sequence is deterministic regardless of worker scheduling order.
    work_items = []
    for i in range(args.iterations):
        source, input_text, cfg_cost = _generate(
            args.strategy, grammar_gen, seeds, rng, args.budget
        )
        work_items.append((i, source, input_text, cfg_cost))

    completed: dict[int, tuple] = {}   # iter → (result, java_r, rust_r, source, cost)

    def _run_one(item):
        i, source, input_text, cfg_cost = item
        with tempfile.TemporaryDirectory(prefix="raddose_fuzz_") as tmp:
            tmp_path = Path(tmp)
            input_path = tmp_path / "input.txt"
            input_path.write_text(input_text)
            java_r, rust_r = run_both(
                input_path, tmp_path,
                java_jar=args.java_jar,
                rust_bin=args.rust_bin,
                timeout=args.timeout,
            )
            result = compare(java_r, rust_r)
        return i, result, java_r, rust_r, source, cfg_cost, input_text

    with open(log_path, "w") as log_f, \
         ThreadPoolExecutor(max_workers=args.workers) as pool:

        futures = {pool.submit(_run_one, item): item[0] for item in work_items}
        pending = args.iterations

        for fut in as_completed(futures):
            i, result, java_r, rust_r, source, cfg_cost, input_text = fut.result()
            pending -= 1

            # ---- Accumulate stats ----
            category_counts[result.category.name] += 1
            total_java_time += result.java_time
            total_rust_time += result.rust_time

            # ---- Log ----
            log_entry = {
                "iter": i,
                "source": source,
                "estimated_cost": round(cfg_cost, 3),
                "category": result.category.name,
                "max_rel_diff": result.max_rel_diff if not (
                    result.max_rel_diff == float("inf")
                ) else "inf",
                "java_time": round(result.java_time, 2),
                "rust_time": round(result.rust_time, 2),
                "note": result.note,
            }
            if result.diffs:
                log_entry["diffs"] = [
                    {"metric": d.name,
                     "java": d.java_val,
                     "rust": d.rust_val,
                     "rel_diff": d.rel_diff if d.rel_diff != float("inf") else "inf"}
                    for d in result.diffs
                ]
            log_f.write(json.dumps(log_entry) + "\n")
            log_f.flush()

            # ---- Save interesting inputs ----
            save_path = None
            if result.category.interesting or args.save_all:
                save_path = _save_input(
                    input_text, result, i, run_id, java_r, rust_r
                )
                interesting_saved += 1

            # ---- Progress line ----
            done = args.iterations - pending
            marker = "!" if result.category.interesting else " "
            print(f"[{done:4d}/{args.iterations}]{marker} {result.summary_line()}"
                  + (f"  -> {save_path.name}" if save_path else ""))

    # ---- Final stats ----
    stats = {
        "run_id": run_id,
        "iterations": args.iterations,
        "strategy": args.strategy,
        "budget": args.budget,
        "timeout": args.timeout,
        "interesting_saved": interesting_saved,
        "avg_java_time": total_java_time / args.iterations,
        "avg_rust_time": total_rust_time / args.iterations,
        "categories": dict(category_counts),
    }
    stats_path.write_text(json.dumps(stats, indent=2))

    print()
    print("=" * 60)
    print(f"Done.  {interesting_saved} interesting cases saved.")
    print(f"Category breakdown:")
    for cat, count in sorted(category_counts.items(), key=lambda x: -x[1]):
        bar = "#" * min(count, 40)
        print(f"  {cat:<22} {count:4d}  {bar}")
    print(f"Stats: {stats_path}")


# ---------------------------------------------------------------------------
# Helpers
# ---------------------------------------------------------------------------

def _generate(
    strategy: str,
    grammar_gen: GrammarGenerator,
    seeds: list[str],
    rng: random.Random,
    budget: float,
) -> tuple[str, str, float]:
    """Return (source_description, input_text, estimated_cost)."""
    use_grammar = (
        strategy == "grammar"
        or (strategy == "both" and rng.random() < 0.5)
        or not seeds
    )

    if use_grammar:
        cfg = grammar_gen.generate()
        text = render(cfg)
        cost = estimate_cost(cfg)
        return "grammar", text, cost
    else:
        seed_text = rng.choice(seeds)
        mutated = mutate_text(seed_text, mutation_rate=0.2, rng=rng)
        # Cost estimation for mutations: use grammar cost model on mutated text
        # We approximate by using a cheap parse; fall back to 1.0 if uncertain
        cost = _estimate_cost_from_text(mutated)
        # If cost exceeds budget, scale down mutation by re-mutating with lower rate
        if cost > budget * 2:
            mutated = mutate_text(seed_text, mutation_rate=0.05, rng=rng)
            cost = _estimate_cost_from_text(mutated)
        return "mutate", mutated, cost


def _estimate_cost_from_text(text: str) -> float:
    """
    Rough cost estimate from raw input text.
    Extracts key cost-driving parameters with regex.
    """
    import re
    from generate import MC_COST_PER_ELECTRON, XFEL_PER_VOXEL_PER_SECOND, MICROED_MULTIPLIER, INSULIN_BASE_COST

    def _find(pattern, default):
        m = re.search(pattern, text, re.IGNORECASE)
        return float(m.group(1)) if m else default

    # Dimensions: "Dimensions <x> <y> <z>" or "Dimensions <x> <y>" or "Dimensions <x>"
    dim_m = re.search(r'Dimensions\s+([\d.eE+\-]+)\s*([\d.eE+\-]*)\s*([\d.eE+\-]*)',
                       text, re.IGNORECASE)
    if dim_m:
        dx = float(dim_m.group(1)) if dim_m.group(1) else 100.0
        dy = float(dim_m.group(2)) if dim_m.group(2) else dx
        dz = float(dim_m.group(3)) if dim_m.group(3) else dy
    else:
        dx = dy = dz = 100.0

    ppm = _find(r'PixelsPerMicron\s+([\d.eE+\-]+)', 0.5)
    voxels = min(dx * dy * dz * ppm ** 3, 1_000_000)

    wedge_m = re.search(r'Wedge\s+([\d.eE+\-]+)\s+([\d.eE+\-]+)', text, re.IGNORECASE)
    if wedge_m:
        span = abs(float(wedge_m.group(2)) - float(wedge_m.group(1)))
    else:
        span = 360.0
    res = _find(r'AngularResolution\s+([\d.eE+\-]+)', 2.0)
    steps = max(1.0, span / res) if span > 0 else 1.0

    base = voxels * steps

    # Subprogram modifiers
    sub_m = re.search(r'Subprogram\s+(\w+)', text, re.IGNORECASE)
    subprogram = sub_m.group(1).upper() if sub_m else ""

    if subprogram == "MONTECARLO":
        runs = int(_find(r'Runs\s+([\d]+)', 1))
        sim_e = _find(r'SimElectrons\s+([\d.eE+\-]+)', 1_000_000)
        return (base + runs * sim_e * MC_COST_PER_ELECTRON) / INSULIN_BASE_COST
    elif subprogram == "XFEL":
        runs = int(_find(r'Runs\s+([\d]+)', 1))
        exposure = _find(r'ExposureTime\s+([\d.eE+\-]+)', 1.0)
        xfel_voxels = min(dx * dy * dz * ppm ** 3, 1_000_000)
        return xfel_voxels * exposure * XFEL_PER_VOXEL_PER_SECOND * runs
    elif subprogram in ("EMSP", "MICROED"):
        return base * MICROED_MULTIPLIER / INSULIN_BASE_COST
    else:
        return base / INSULIN_BASE_COST


def _save_input(
    input_text: str,
    result,
    iteration: int,
    run_id: str,
    java_r,
    rust_r,
) -> Path:
    """Save an interesting input and its outputs to corpus/."""
    cat = result.category

    if cat in (Category.JAVA_CRASH, Category.RUST_CRASH, Category.BOTH_CRASH):
        subdir = (
            "crashes/java" if cat == Category.JAVA_CRASH else
            "crashes/rust" if cat == Category.RUST_CRASH else
            "crashes/both"
        )
    elif cat in (Category.JAVA_TIMEOUT, Category.RUST_TIMEOUT,
                 Category.BOTH_TIMEOUT, Category.PERF_DIVERGE):
        subdir = "timeouts"
    else:
        subdir = "diffs"

    save_dir = CORPUS_DIR / subdir
    save_dir.mkdir(parents=True, exist_ok=True)

    base = f"{run_id}_{iteration:04d}_{cat.name}"
    input_save = save_dir / f"{base}.txt"
    input_save.write_text(input_text)

    # Save a short metadata sidecar
    meta = {
        "category": cat.name,
        "max_rel_diff": result.max_rel_diff if result.max_rel_diff != float("inf") else "inf",
        "note": result.note,
        "java_time": result.java_time,
        "rust_time": result.rust_time,
        "java_exit": java_r.exit_code,
        "rust_exit": rust_r.exit_code,
        "java_stderr": java_r.stderr[-500:] if java_r.stderr else "",
        "rust_stderr": rust_r.stderr[-500:] if rust_r.stderr else "",
    }
    if result.diffs:
        meta["diffs"] = [
            {"metric": d.name, "java": d.java_val, "rust": d.rust_val,
             "rel_diff": d.rel_diff if d.rel_diff != float("inf") else "inf"}
            for d in result.diffs
        ]
    (save_dir / f"{base}.json").write_text(json.dumps(meta, indent=2))

    return input_save


def _load_seeds() -> list[str]:
    """Load all .txt files from corpus/seeds/."""
    seeds = []
    if SEEDS_DIR.exists():
        for p in sorted(SEEDS_DIR.glob("*.txt")):
            try:
                seeds.append(p.read_text())
            except Exception:
                pass
    return seeds


if __name__ == "__main__":
    main()
