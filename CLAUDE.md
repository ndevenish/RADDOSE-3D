# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

RADDOSE-3D is a radiation dose modelling tool for macromolecular crystallography (MX), SAXS, electron diffraction (MicroED), and XFEL experiments. It calculates 3D dose distributions in crystal volumes over angular wedges. Developed by the Garman Lab at the University of Oxford.

The repository contains two implementations:
- **Java** (original, production): `src/se/raddo/raddose3D/`
- **Rust** (modern rewrite, all phases complete): `raddose3d/` — see `raddose3d/CLAUDE.md` for Rust-specific details

## Build & Run

### Java (Apache Ant)
```bash
ant build                # Compile (Java 1.8 target)
ant jar                  # Build raddose3d.jar (excludes tests & server)
ant clean                # Remove bin/ and deploy/
java -jar raddose3d.jar -i path/to/input.txt   # Run
```

### Rust (Cargo workspace in `raddose3d/`)
```bash
cd raddose3d
cargo build              # Debug build
cargo build --release    # Release build
cargo run -- -i tests/fixtures/insulin_test.txt
```

## Testing

### Java (TestNG)
```bash
ant test                 # Basic tests (excludes "advanced" group)
ant test-all             # All tests including advanced
```
Test config: `testng.xml`. Tests in `src/se/raddo/raddose3D/tests/`.

### Rust
```bash
cd raddose3d
cargo test                                    # All tests
cargo test --test golden_validation           # Single integration test
cargo test --package raddose3d-parser         # Single crate
```
Integration tests in `raddose3d/crates/raddose3d/tests/`. Validation tests compare Rust output bit-for-bit against Java baselines.

### Code Quality (Java)
```bash
ant checkstyle           # Checkstyle (config: lib/checkstyle-5.6/raddose_checks.xml)
ant pmd                  # PMD (config: lib/pmd-5.1.1/raddose-ruleset.xml)
ant findbugs             # FindBugs (config: lib/findbugs-2.0.3/raddose-exclusions.xml)
```

## Architecture

### Core Simulation Pipeline
1. **Parse** input file (ANTLR grammar in Java, pest PEG in Rust)
2. **Construct** Crystal, Beam, CoefCalc, Wedge via factory pattern
3. **Expose**: iterate over angles × energies × voxels, computing fluence → dose
4. **Output**: observer pattern notifies multiple output modules (text, CSV, voxel data, etc.)

### Key Abstractions (Java interfaces / Rust traits)
- **Crystal**: geometry types — Cuboid, Cylinder, Polyhedron, Spherical
- **Beam**: intensity profiles — Gaussian, Tophat, Experimental
- **CoefCalc**: absorption coefficient calculation — Compute (RD3D default), FromParams, FromPDB, FromSequence, SAXS, MicroED, SmallMolecules, FromCIF
- **DDM**: dose decay models — Simple, Linear, Leal, Bfactor
- **Container**: surrounding material — Transparent, Mixture, Elemental
- **Output**: observer modules — SummaryText, SummaryCSV, DWDs, RDE, VoxelDose, etc.

All major types use factory pattern: `*Factory.java` in Java, `create_*()` functions in Rust.

### Specialized Simulations
- **Monte Carlo** (`MC.java` / `mc.rs`): photoelectron/Auger/Compton tracking with escape
- **XFEL** (`XFEL.java` / `xfel.rs`): time-resolved femtosecond dose with 4D voxel arrays
- **MicroED** (`MicroED.java` / `micro_ed.rs`): electron diffraction, CSDA range, stopping power

### Physics Data
`constants/` directory contains cross-section tables, energy coefficients, and element data. These must be present at runtime for Java; they are embedded in the Rust binary.

### Voxel Model
3D voxel grid indexed as flat array: `idx = i*ny*nz + j*nz + k`. Default resolution 0.5 voxels/micron, auto-reduces if >1M voxels. Dimensions in microns, energy in keV, dose in MGy, angles in radians internally (degrees in input).

## Input File Format
Text-based with Crystal/Beam/Wedge blocks. Comments: `#`, `!`, `//`. Case-insensitive keywords. See `README.md` for a full insulin example and `examples/` for other sample types.

## CLI Flags
`-i <file>` input, `-p <prefix>` output prefix, `-r <path>` RADDOSE v2 path, `-t` test mode, `-V` version, `-?` help.
