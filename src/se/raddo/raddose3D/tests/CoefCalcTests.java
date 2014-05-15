package se.raddo.raddose3D.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.*;

import se.raddo.raddose3D.CoefCalcFromParams;

/**
 * @author magd3052
 */
public class CoefCalcTests {
  /**
   * Take a unit cell consisting entirely of water and make sure
   * number of hydrogens is twice the number of oxygens.
   */
  @Test
  public void testCoefCalcWaterOnly() {
    List<String> atoms = new ArrayList<String>();
    List<Double> numbers = new ArrayList<Double>();

    CoefCalcFromParams coefCalc = new CoefCalcFromParams(100.0,
        100.0, 100.0, 90.0, 90.0, 90.0, 0, 0, 0, 0, atoms, numbers,
        atoms, numbers, 100.0);

    Double oxygenOccurrence = Double.valueOf(coefCalc
        .getSolventOccurrence(coefCalc.getParser()
            .getElement("O")));
    Double hydrogenOccurrence = Double.valueOf(coefCalc
        .getSolventOccurrence(coefCalc.getParser()
            .getElement("H")));

    Assertion.equals(hydrogenOccurrence, oxygenOccurrence * 2, "O vs H");
  }

  /**
   * Test to make sure protein heavy atoms are added and
   * multiplied by no. of monomers correctly
   */
  @Test
  public void testCoefCalcHeavyProteinAtoms() {
    List<String> emptyAtoms = new ArrayList<String>();
    List<Double> emptyNumbers = new ArrayList<Double>();

    List<String> atoms = new ArrayList<String>();
    List<Double> numbers = new ArrayList<Double>();

    atoms.add("zn");
    numbers.add(Double.valueOf(2));

    CoefCalcFromParams coefCalc = new CoefCalcFromParams(100.0,
        100.0, 100.0, 90.0, 90.0, 90.0, 24, 10, 0, 0, atoms,
        numbers, emptyAtoms, emptyNumbers, 100.0);

    Double zincOccurrence = coefCalc.getMacromolecularOccurrence(
        coefCalc.getParser().getElement("ZN"));

    Assertion.equals(zincOccurrence, 48, "Zn = 48");
  }

}
