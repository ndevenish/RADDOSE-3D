# CLAUDE.md — Java RADDOSE-3D (Reference)

Original Java implementation of [RADDOSE-3D](https://github.com/GarmanGroup/RADDOSE-3D), linked here as a reference for the Rust rewrite at `../` (the primary project). Do not make changes here unless fixing the Java reference itself.

The full porting plan is at `snoopy-imagining-noodle.md`.

## Build & Run

```bash
ant build                # Compile to bin/ (Java 1.8 target)
ant jar                  # Build raddose3d.jar (excludes tests & server)
ant clean                # Remove bin/ and deploy/
java -jar raddose3d.jar -i path/to/input.txt   # Run
```

## Testing (TestNG)

```bash
ant test                 # Basic tests (excludes "advanced" group)
ant test-all             # All tests including advanced
```

Test config: `testng.xml`. Tests in `src/se/raddo/raddose3D/tests/`.

## Code Quality

```bash
ant checkstyle           # Checkstyle (config: lib/checkstyle-5.6/raddose_checks.xml)
ant pmd                  # PMD (config: lib/pmd-5.1.1/raddose-ruleset.xml)
ant findbugs             # FindBugs (config: lib/findbugs-2.0.3/raddose-exclusions.xml)
```

## Source Layout

```
src/se/raddo/raddose3D/   # All Java source files
constants/                # Cross-section tables, element data (needed at runtime)
examples/                 # Sample input files
lib/                      # Ant build dependencies (TestNG, Checkstyle, PMD, FindBugs)
raddose3d.jar             # Pre-built JAR (used by fuzz harness)
```

## Architecture

### Core Simulation Pipeline
1. **Parse** input file (ANTLR grammar)
2. **Construct** Crystal, Beam, CoefCalc, Wedge via factory pattern
3. **Expose**: iterate over angles × energies × voxels, computing fluence → dose
4. **Output**: observer pattern notifies multiple output modules (text, CSV, voxel data, etc.)

### Key Abstractions
- **Crystal**: geometry types — Cuboid, Cylinder, Polyhedron, Spherical
- **Beam**: intensity profiles — Gaussian, Tophat, Experimental
- **CoefCalc**: absorption coefficient calculation — Compute (RD3D default), FromParams, FromPDB, FromSequence, SAXS, MicroED, SmallMolecules, FromCIF
- **DDM**: dose decay models — Simple, Linear, Leal, Bfactor
- **Container**: surrounding material — Transparent, Mixture, Elemental
- **Output**: observer modules — SummaryText, SummaryCSV, DWDs, RDE, VoxelDose, etc.

All major types use factory pattern: `*Factory.java`.

### Specialized Simulations
- **Monte Carlo** (`MC.java`): photoelectron/Auger/Compton tracking with escape
- **XFEL** (`XFEL.java`): time-resolved femtosecond dose with 4D voxel arrays
- **MicroED** (`MicroED.java`): electron diffraction, CSDA range, stopping power

## CLI Flags

`-i <file>` input, `-p <prefix>` output prefix, `-r <path>` RADDOSE v2 path, `-t` test mode, `-V` version, `-?` help.

## Input File Format

Text-based with Crystal/Beam/Wedge blocks. Comments: `#`, `!`, `//`. Case-insensitive keywords. See `README.md` for a full insulin example and `examples/` for other sample types.
