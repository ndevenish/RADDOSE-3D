package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

import se.raddo.raddose3D.ElementDatabase.DatabaseFields;

/**
 * The Element class contains physical constants of an element associated with
 * x-ray cross sections.
 */
public class Element {
  /**
   * Element name.
   */
  private final String                                      elementName;

  /**
   * Atomic number.
   */
  private final int                                         atomicNumber;

  /**
   * Stored information about the chemical element.
   */
  private final Map<ElementDatabase.DatabaseFields, Double> elementData;

  /**
   * Stored absorption edge coefficients.
   */
  private final Map<AbsorptionEdge, Double[]>               coefficients;

  /**
   * List of absorption edges.
   */
  private enum AbsorptionEdge {
    /** innermost electron shell, 1 shell. */
    K,
    /** 2 shell. */
    L,
    /** 3 shell. */
    M,
    /** 4 shell. */
    N,
    /** Coherent scattering polynomial coefficients. */
    C,
    /** Incoherent scattering polynomial coefficients. */
    I
  }

  /** Atomic mass unit in grams. */
  private static final double ATOMIC_MASS_UNIT          = 1.66E-24;

  /** LJ_1 variable from Fortran, used to correct atomic elements < 29 Z. */
  private static final double LJ_1                      = 1.160;

  /** LJ_2 variable from Fortran, used to correct atomic elements < 29 Z. */
  private static final double LJ_2                      = 1.41;

  /** Light/heavy element threshold, 29 is treated as light atom. */
  public static final int     LIGHT_ATOM_MAX_NUM        = 29;

  /** Absorption edge room for error. */
  private static final double ABSORPTION_EDGE_TOLERANCE = 0.001;

  /** Number of expansions of the polynomial. */
  private static final int    POLYNOMIAL_EXPANSION      = 4;

  /** Different types of calculated cross-sections. */
  public enum CrossSection {
    /**
     * Cross-section for the photoelectric effect. This does not contribute to
     * scattering.
     */
    PHOTOELECTRIC,
    /**
     * Cross-section for coherent (elastic) scattering.
     */
    COHERENT,
    /**
     * Attenuation cross-section.
     */
    TOTAL
  }

  /**
   * Create a new element with name, atomic number and associated information.
   * 
   * @param element
   *          element name
   * @param protons
   *          atomic number
   * @param elementInformation
   *          Map containing the associated element information
   */
  public Element(final String element, final int protons,
      final Map<ElementDatabase.DatabaseFields, Double> elementInformation) {
    elementName = element;
    atomicNumber = protons;
    elementData = new HashMap<ElementDatabase.DatabaseFields, Double>(
        elementInformation);
    coefficients = edgeCoefficients(elementInformation);
  }

  /**
   * Converts the edge coefficients into easier-to-handle arrays.
   * 
   * @param elementInformation
   *          The database fields of the current element
   * @return
   *         Map containing all edge coefficients as Double[] arrays.
   */
  private Map<AbsorptionEdge, Double[]> edgeCoefficients(
      final Map<ElementDatabase.DatabaseFields, Double> elementInformation) {

    Map<AbsorptionEdge, Double[]> coeffMap =
        new HashMap<AbsorptionEdge, Double[]>();

    Double[] coeff;
    coeff = new Double[POLYNOMIAL_EXPANSION];
    coeff[0] = elementInformation
        .get(ElementDatabase.DatabaseFields.K_COEFF_0);
    coeff[1] = elementInformation
        .get(ElementDatabase.DatabaseFields.K_COEFF_1);
    coeff[2] = elementInformation
        .get(ElementDatabase.DatabaseFields.K_COEFF_2);
    coeff[3] = elementInformation
        .get(ElementDatabase.DatabaseFields.K_COEFF_3);
    coeffMap.put(AbsorptionEdge.K, coeff);

    coeff = new Double[POLYNOMIAL_EXPANSION];
    coeff[0] = elementInformation
        .get(ElementDatabase.DatabaseFields.L_COEFF_0);
    coeff[1] = elementInformation
        .get(ElementDatabase.DatabaseFields.L_COEFF_1);
    coeff[2] = elementInformation
        .get(ElementDatabase.DatabaseFields.L_COEFF_2);
    coeff[3] = elementInformation
        .get(ElementDatabase.DatabaseFields.L_COEFF_3);
    coeffMap.put(AbsorptionEdge.L, coeff);

    coeff = new Double[POLYNOMIAL_EXPANSION];
    coeff[0] = elementInformation
        .get(ElementDatabase.DatabaseFields.M_COEFF_0);
    coeff[1] = elementInformation
        .get(ElementDatabase.DatabaseFields.M_COEFF_1);
    coeff[2] = elementInformation
        .get(ElementDatabase.DatabaseFields.M_COEFF_2);
    coeff[3] = elementInformation
        .get(ElementDatabase.DatabaseFields.M_COEFF_3);
    coeffMap.put(AbsorptionEdge.M, coeff);

    coeff = new Double[POLYNOMIAL_EXPANSION];
    coeff[0] = elementInformation
        .get(ElementDatabase.DatabaseFields.N_COEFF_0);
    coeff[1] = elementInformation
        .get(ElementDatabase.DatabaseFields.N_COEFF_1);
    coeff[2] = elementInformation
        .get(ElementDatabase.DatabaseFields.N_COEFF_2);
    coeff[3] = elementInformation
        .get(ElementDatabase.DatabaseFields.N_COEFF_3);
    coeffMap.put(AbsorptionEdge.N, coeff);

    coeff = new Double[POLYNOMIAL_EXPANSION];
    coeff[0] = elementInformation
        .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_0);
    coeff[1] = elementInformation
        .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_1);
    coeff[2] = elementInformation
        .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_2);
    coeff[3] = elementInformation
        .get(ElementDatabase.DatabaseFields.COHERENT_COEFF_3);
    coeffMap.put(AbsorptionEdge.C, coeff);

    coeff = new Double[POLYNOMIAL_EXPANSION];
    coeff[0] = elementInformation
        .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_0);
    coeff[1] = elementInformation
        .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_1);
    coeff[2] = elementInformation
        .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_2);
    coeff[3] = elementInformation
        .get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_3);
    coeffMap.put(AbsorptionEdge.I, coeff);

    return coeffMap;
  }

  /**
   * Calculation of "bax" for corresponding edge and energy.
   * 
   * @param energy
   *          beam energy in keV
   * @param edge
   *          Selected edge coefficient (K, L, M, N, C, I).
   * @return
   *         value of bax
   */
  private double baxForEdge(final double energy, final AbsorptionEdge edge) {
    // calculation from logarithmic coefficients in McMaster tables.

    double sum = 0;
    Double[] coeffs = coefficients.get(edge);

    for (int i = 0; i < POLYNOMIAL_EXPANSION; i++) {
      if (energy == 1) {
        /*
         * This is actually wrong, as it causes a discontinuity in the
         * cross-section function. However, this is how Pathikrit Bandyopadhyay
         * chose to implement it, so it stays. It also only affects values at
         * E = 1keV.
         */
        sum += coeffs[i];
      } else {
        sum += coeffs[i] * Math.pow(Math.log(energy), i);
      }
    }

    return Math.exp(sum);
  }

  /**
   * Obtain the photoelectric, elastic and incoherent cross-sections for a given
   * energy and calculate the total cross-section.
   * 
   * @param energy
   *          X-ray photon energy in keV
   * @return
   *         Map structure containing the photoelectric, coherent and total
   *         cross sections in units Barns/Atom.
   */
  public Map<CrossSection, Double> getAbsCoefficients(final double energy) {
    double photoelectric = getPhotoelectricXSForEnergy(energy);

    double elastic = 0;
    if (elementData.get(ElementDatabase.DatabaseFields.COHERENT_COEFF_0) != 0) {
      elastic = baxForEdge(energy, AbsorptionEdge.C);
    }

    double binx = 0;
    if (elementData.get(ElementDatabase.DatabaseFields.INCOHERENT_COEFF_0) != 0)
    {
      binx = baxForEdge(energy, AbsorptionEdge.I);
    }

    double attenuation = photoelectric + elastic + binx;

    Map<CrossSection, Double> results = new HashMap<CrossSection, Double>();
    results.put(CrossSection.COHERENT, elastic);
    results.put(CrossSection.PHOTOELECTRIC, photoelectric); // mu, abs coeff.
    results.put(CrossSection.TOTAL, attenuation);
    return results;
  }

  /**
   * Determine the photoelectric cross-section for a given energy.
   * Find the corresponding edge for the energy and the known absorption edges.
   * Obtain the cross-section and correct it for atomic numbers below 29. Print
   * a warning if we're too close to an absorption edge.
   * 
   * @param energy
   *          X-ray photon energy in keV.
   * @return
   *         photoelectric absorption cross-section in units Barns/Atom.
   */
  @SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.NPathComplexity" })
  private double getPhotoelectricXSForEnergy(final double energy) {
    Double absorptionEdgeK =
        elementData.get(ElementDatabase.DatabaseFields.EDGE_K);
    if (absorptionEdgeK == null) {
      throw new IllegalStateException("K Absorption Edge undefined");
    }
    Double absorptionEdgeL =
        elementData.get(ElementDatabase.DatabaseFields.EDGE_L);
    Double absorptionEdgeM =
        elementData.get(ElementDatabase.DatabaseFields.EDGE_M);

    if ((energy < absorptionEdgeK
        && energy > absorptionEdgeK - ABSORPTION_EDGE_TOLERANCE)
        || (absorptionEdgeL != null && energy < absorptionEdgeL
        && energy > absorptionEdgeL - ABSORPTION_EDGE_TOLERANCE)
        || (absorptionEdgeM != null && energy < absorptionEdgeM
        && energy > absorptionEdgeM - ABSORPTION_EDGE_TOLERANCE)) {

      System.err.println("Warning: Energy is close to absorption edge of "
          + elementName);
    }

    // Obtain photoelectric absorption coefficient using the closest edge.
    double photoelectric = 0;
    if ((energy > absorptionEdgeK) || (absorptionEdgeL == null)) {
      photoelectric = baxForEdge(energy, AbsorptionEdge.K);
    } else if ((energy > absorptionEdgeL) || (absorptionEdgeM == null)) {
      photoelectric = baxForEdge(energy, AbsorptionEdge.L);
    } else if (energy > absorptionEdgeM) {
      photoelectric = baxForEdge(energy, AbsorptionEdge.M);
    } else {
      photoelectric = baxForEdge(energy, AbsorptionEdge.N);
    }

    // Correction of the absorption coefficient for light elements
    if (atomicNumber <= LIGHT_ATOM_MAX_NUM) {
      // Fortran says...
      // correct for L-edges since McMaster uses L1 edge.
      // Use edge jumps for correct X-sections.
      if ((energy > elementData.get(ElementDatabase.DatabaseFields.L3))
          && (energy < elementData.get(ElementDatabase.DatabaseFields.L2))) {
        photoelectric /= (LJ_1 * LJ_2);
      }

      if ((energy > elementData.get(ElementDatabase.DatabaseFields.L2))
          && (energy < absorptionEdgeL)) {
        photoelectric /= LJ_1;
      }
    }

    return photoelectric;
  }

  /**
   * @return the elementName
   */
  public String getElementName() {
    return elementName;
  }

  /**
   * @return the atomicNumber
   */
  public int getAtomicNumber() {
    return atomicNumber;
  }

  /**
   * Return the atomic weight of this element in unified atomic mass (u).
   * 
   * @return
   *         the atomic weight in u
   */
  public Double getAtomicWeight() {
    return elementData.get(DatabaseFields.ATOMIC_WEIGHT);
  }

  /**
   * Return the atomic weight of this element in grams.
   * 
   * @return
   *         the atomic weight in grams
   */
  public Double getAtomicWeightInGrams() {
    return getAtomicWeight() * ATOMIC_MASS_UNIT;
  }
  
  /**
   * Return the K edge energy in keV of the element
   * 
   * @return
   *         the K edge energy in keV
   */
  public Double getKEdge() {
    return elementData.get(DatabaseFields.EDGE_K);
  }
  
  /**
   * Return the L1 edge energy in keV of the element
   * 
   * @return
   *         the L1 edge energy in keV
   */
  public Double getL1Edge() {
    return elementData.get(DatabaseFields.EDGE_L);
  }
  
  /**
   * Return the L2 edge energy in keV of the element
   * 
   * @return
   *         the L2 edge energy in keV
   */
  public Double getL2Edge() {
    return elementData.get(DatabaseFields.L2);
  }
  
  /**
   * Return the L3 edge energy in keV of the element
   * 
   * @return
   *         the L3 edge energy in keV
   */
  public Double getL3Edge() {
    return elementData.get(DatabaseFields.L3);
  }
  
  /**
   * Return the M1 edge energy in keV of the element
   * 
   * @return
   *         the M1 edge energy in keV
   */
  public Double getM1Edge() {
    return elementData.get(DatabaseFields.EDGE_M);
  }
  
  /**
   * Return the K edge ratio which is defined as the ratio
   * of the K shell to L1 shell photoelectric cross sections.
   * i.e. muK / muL1
   * 
   * @return
   *         the K edge ratio
   */
  public Double getKEdgeRatio() {
    return elementData.get(DatabaseFields.K_EDGE_RATIO);
  }
  
  /**
   * Return the L1 edge ratio which is defined as the ratio
   * of the L1 shell to M shell photoelectric cross sections.
   * i.e. muL1 / muM
   * 
   * @return
   *         the L1 edge ratio
   */
  public Double getL1EdgeRatio() {
    return elementData.get(DatabaseFields.L1_EDGE_RATIO);
  }
  
  /**
   * Return the L2 edge ratio which is defined as the ratio
   * of the L2 shell to M shell photoelectric cross sections.
   * i.e. muL2 / muM
   * 
   * @return
   *         the L2 edge ratio
   */
  public Double getL2EdgeRatio() {
    return elementData.get(DatabaseFields.L2_EDGE_RATIO);
  }
  
  /**
   * Return the L3 edge ratio which is defined as the ratio
   * of the L3 shell to M shell photoelectric cross sections.
   * i.e. muL3 / muM
   * 
   * @return
   *         the L3 edge ratio
   */
  public Double getL3EdgeRatio() {
    return elementData.get(DatabaseFields.L3_EDGE_RATIO);
  }
  
  /**
   * Return the K shell fluorescence yield of the atom
   * 
   * @return
   *         the K shell fluorescence yield
   */
  public Double getKShellFluorescenceYield() {
    return elementData.get(DatabaseFields.FLUORESCENCE_YIELD_K);
  }
  
  /**
   * Return the L1 shell fluorescence yield of the atom
   * 
   * @return
   *         the L1 shell fluorescence yield
   */
  public Double getL1ShellFluorescenceYield() {
    return elementData.get(DatabaseFields.FLUORESCENCE_YIELD_L1);
  }
  
  /**
   * Return the L2 shell fluorescence yield of the atom
   * 
   * @return
   *         the L2 shell fluorescence yield
   */
  public Double getL2ShellFluorescenceYield() {
    return elementData.get(DatabaseFields.FLUORESCENCE_YIELD_L2);
  }
  
  /**
   * Return the L3 shell fluorescence yield of the atom
   * 
   * @return
   *         the L3 shell fluorescence yield
   */
  public Double getL3ShellFluorescenceYield() {
    return elementData.get(DatabaseFields.FLUORESCENCE_YIELD_L3);
  }
}
