"""
Run both Java and Rust RADDOSE-3D on the same input file and collect results.
Both processes run in parallel from an isolated temp directory.
"""

import os
import shutil
import subprocess
import tempfile
import time
from dataclasses import dataclass, field
from pathlib import Path
from typing import Optional

REPO_ROOT = Path(__file__).parent.parent
DEFAULT_JAVA_JAR = REPO_ROOT / "raddose3d.jar"
DEFAULT_RUST_BIN = REPO_ROOT / "raddose3d" / "target" / "release" / "raddose3d"
DEFAULT_TIMEOUT = 60.0   # seconds


@dataclass
class RunResult:
    impl: str                   # "java" | "rust"
    exit_code: Optional[int]    # None = timed out
    wall_time: float            # seconds
    stdout: str
    stderr: str
    output_dir: Path            # where output files were written
    timed_out: bool = False
    error: str = ""             # harness-level error (e.g. binary not found)

    @property
    def succeeded(self) -> bool:
        return self.exit_code == 0 and not self.timed_out and not self.error

    @property
    def crashed(self) -> bool:
        return not self.timed_out and not self.error and self.exit_code != 0

    def summary_csv_path(self) -> Optional[Path]:
        p = self.output_dir / f"{self.impl}-Summary.csv"
        return p if p.exists() else None


def run_both(
    input_path: Path,
    work_dir: Path,
    java_jar: Path = DEFAULT_JAVA_JAR,
    rust_bin: Path = DEFAULT_RUST_BIN,
    timeout: float = DEFAULT_TIMEOUT,
) -> tuple[RunResult, RunResult]:
    """
    Run Java and Rust on input_path simultaneously.
    Each gets its own subdirectory under work_dir.
    Returns (java_result, rust_result).
    """
    java_out_dir = work_dir / "java_out"
    rust_out_dir = work_dir / "rust_out"
    java_out_dir.mkdir(parents=True, exist_ok=True)
    rust_out_dir.mkdir(parents=True, exist_ok=True)

    java_prefix = str(java_out_dir / "java-")
    rust_prefix = str(rust_out_dir / "rust-")

    java_cmd = [
        "java", "-jar", str(java_jar),
        "-i", str(input_path),
        "-p", java_prefix,
    ]
    rust_cmd = [
        str(rust_bin),
        "-i", str(input_path),
        "-p", rust_prefix,
    ]

    # Launch both in parallel
    t0 = time.monotonic()
    java_proc = _launch(java_cmd, work_dir)
    rust_proc = _launch(rust_cmd, work_dir)

    # Wait for both with shared deadline
    java_result = _collect(java_proc, "java", java_out_dir, t0, timeout)
    rust_result = _collect(rust_proc, "rust", rust_out_dir, t0, timeout)

    return java_result, rust_result


# ---------------------------------------------------------------------------
# Internal helpers
# ---------------------------------------------------------------------------

def _launch(cmd: list[str], cwd: Path) -> Optional[subprocess.Popen]:
    try:
        return subprocess.Popen(
            cmd,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            cwd=str(cwd),
        )
    except FileNotFoundError as e:
        return e


def _collect(
    proc,
    impl: str,
    output_dir: Path,
    t0: float,
    timeout: float,
) -> RunResult:
    if isinstance(proc, Exception):
        return RunResult(
            impl=impl,
            exit_code=None,
            wall_time=0.0,
            stdout="",
            stderr="",
            output_dir=output_dir,
            error=str(proc),
        )

    elapsed = time.monotonic() - t0
    remaining = max(0.1, timeout - elapsed)

    try:
        stdout_b, stderr_b = proc.communicate(timeout=remaining)
        wall_time = time.monotonic() - t0
        return RunResult(
            impl=impl,
            exit_code=proc.returncode,
            wall_time=wall_time,
            stdout=stdout_b.decode(errors="replace"),
            stderr=stderr_b.decode(errors="replace"),
            output_dir=output_dir,
        )
    except subprocess.TimeoutExpired:
        proc.kill()
        try:
            stdout_b, stderr_b = proc.communicate(timeout=5)
        except Exception:
            stdout_b, stderr_b = b"", b""
        return RunResult(
            impl=impl,
            exit_code=None,
            wall_time=timeout,
            stdout=stdout_b.decode(errors="replace"),
            stderr=stderr_b.decode(errors="replace"),
            output_dir=output_dir,
            timed_out=True,
        )
