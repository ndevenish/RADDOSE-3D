package se.raddo.raddose3D;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

//import javafx.util.Pair;

import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.*;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;




public class MicroED {
  private int hit;
  //polyhderon variables
  public double[][] verticesEM;
  public int[][] indicesEM;
  public double[][][][] crystCoordEM;
  public double crystalPixPerUMEM;
  public int[] crystalSizeVoxelsEM;
  public boolean[][][][] crystOccEM;
  /**
   * Vertices which have been rotated for a given wedge angle.
   */
  private double[][]            rotatedVertices;
  /**
   * Similar in style to the index array, except each index is replaced
   * by the corresponding rotatedVertex.
   */
  private double[][][]          expandedRotatedVertices;
  /**
   * Normal array holding normalised direction vectors for
   * each triangle specified by the index array.
   * Contains an i, j, k vector per triangle.
   * Should have same no. of entries as the indices array.
   */
  private double[][]            normals, rotatedNormals;
  
  private TreeMap<Double, double[]>[]  lowEnergyAngles;
  private TreeMap<Double, double[]>[]  highEnergyAngles;

  /**
   * Distances from origin for each of the triangle planes.
   * Should have same no. of entries as the indices array.
   */
  private double[]              originDistances, rotatedOriginDistances;
  
  
  public double crystalSurfaceArea;  //A^2

  public double sampleThickness; //nm

  public double crystalVolume;  //dm^3
  
  public double XDimension; //um
  public double YDimension;
  public double ZDimension;
  public String crystalTypeEM;
  
  private long numSimulatedElectrons;
  private double numElectrons;
  private long simNumber;
  
  private double doseOutput;
  private double gosDoseOutput;
  
  private double numberElastic;
  private double numberSingleElastic;
  private double numberNotInelasticEqu;
  private double numberNotInelasticRatio;
  private double numberProductive;
  private double stoppingPowerESTAR;
  private double MonteCarloRuntime;
  //to see if multislice is necessary at all
  private final int numberSlices = 1;
  
  private double MonteCarloDose;
  private double MonteCarloImageDose;
  private double MonteCarloTotElasticCount;
  private double MonteCarloSingleElasticCount;
  private double MonteCarloFSEEscape;
  private double MonteCarloFSEEntry; 
  private double MonteCarloFlEscape;
  private double MonteCarloAugerEscape;
  private double MonteCarloAugerEntry;
  private double MonteCarloProductive;
  private double MonteCarloProductiveSolvent;
  private double MonteCarloUnproductive;
  private double MonteCarloUnproductiveMicroED;
  private double MonteCarloUnproductiveMicroEDSolvent;
  private double MonteCarloProductiveImage;
  private double extraFlEscape;
  private double extraAugerEscape;
  private double newMonteCarloFSEEscape;
  private double FSEsum;
  private int FSEcount;
  private double lowEnDose;
  
  private double MonteCarloElectronsExited;
  private double MonteCarloElectronsEntered;
  private double MonteCarloCharge;
  private double MonteCarloChargeDensity;
  
  private double elasticEnergyTot;
  private double displacementEnergy;
 
  private double totFSEEnergy;
  private double totAugerEnergy;
  private double totShellEnergy;
  private double totPlasmonEnergy;
  private double totBreakdownEnergy;
  
  private double avgDist;
  
  private int numAuger;
  private int numFL;
  private int numFSEFromSurr;
  private int numFSEFromSample;
  
  private double[][][] voxelCharge;
  private double[][][] voxelDose;
  private double avgVoxelDose;
  private double maxX, maxY, maxZ;
  private double[] regionDose;
  private double[] regionVolume;
  private double avgRegionDose;
  
  
  public final double Wcc = 0.0;
  private double MonteCarloGOSDose;
  private double MonteCarloGOSEscape;
  private double energyLostGOS;
  
  private double GOSImageDose;
  private double totImageSecEnergy;
  private double imageSecDeposited;
  private double imageEntry;
  private double gosSurroundingElastic;
  private double gosSurroundingElasticImage;
  
  private double avgW;
  private double Wcount;
  private double avgShell;
  
  private double inelAngleSum;
  private double inelAngleCount;
  ArrayList<Double> inelAngles = new ArrayList<Double>();
  private HashMap<Double, Double> inelEnAngle;
  
  private boolean GOS = true;
  
  private boolean scatteredSolvent = false;
  
  protected static final int NUM_REGIONS = 10;
  
  
  
  protected static final long NUM_MONTE_CARLO_ELECTRONS = 10000;
  
  protected static final double c = 299792458; //m/s
  protected static final double m =  9.10938356E-31; //Kg
  
  protected static final double CUTOFF = 0.0001;
  public double energyCUTOFF = 3;
  public final boolean considerCharge = false;
  
  
  protected static final int BIN_DIVISION = 2; //how many bins to divide the dose deposition into 
  
  //for cylinder
  public boolean rotated;
  
  
  
  
  @SuppressWarnings("unchecked")
  public MicroED(double vertices[][], int[][] indices, double[][][][] crystCoord, 
                  double crystalPixPerUM, int[] crystSizeVoxels, boolean[][][][] crystOcc, String crystalType) {
    verticesEM = vertices;
    indicesEM = indices;
    crystCoordEM = crystCoord;
    crystalPixPerUMEM = crystalPixPerUM;
    crystalSizeVoxelsEM = crystSizeVoxels;
    crystOccEM = crystOcc;
    crystalTypeEM = crystalType;
    
    double[] xMinMax = this.minMaxVertices(0, vertices);
    double[] yMinMax = this.minMaxVertices(1, vertices);
    double[] zMinMax = this.minMaxVertices(2, vertices);
    XDimension = 1000 * (xMinMax[1] - xMinMax[0]);
    YDimension = 1000 * (yMinMax[1] - yMinMax[0]);
    ZDimension = 1000 * (zMinMax[1] - zMinMax[0]);
    
    
    crystalSurfaceArea = XDimension * YDimension * 1E02; //convert from nm^2 to A^2
    if (crystalTypeEM == "CYLINDER") {
      crystalSurfaceArea = (Math.PI * (XDimension/2) * (YDimension/2)) * 1E02;
     // ZDimension = 99.99999;
      ZDimension -= 0.001;
    }
    sampleThickness = ZDimension; //nm
    crystalVolume = (crystalSurfaceArea * (sampleThickness * 10) * 1E-27);    //A^3 to dm^3
    if (crystalTypeEM == "SPHERICAL") {
      crystalVolume = ((4/3) * Math.PI * (XDimension/2) * (YDimension/2) * (ZDimension/2)) * 1E-24; //nm^3
    }
    //note the volume would need to be updated for a polyhedron!!! - currently just a cube or cylinder 
    //although it isn't used
    
    lowEnergyAngles = new TreeMap[95];
    highEnergyAngles = new TreeMap[95];
    
    //initialise voxel dose and charge
    int[] maxVoxel = getMaxPixelCoordinates();
    voxelCharge = new double[maxVoxel[0]][maxVoxel[1]][maxVoxel[2]];
    voxelDose = new double[maxVoxel[0]][maxVoxel[1]][maxVoxel[2]];
    maxX = maxVoxel[0];
    maxY = maxVoxel[1];
    maxZ = maxVoxel[2];
    regionDose = new double[NUM_REGIONS];
    regionVolume = new double[NUM_REGIONS];
    populateRegionVolumes();
  }
  
  public void getCSDArange(CoefCalc coefCalc) {
    double en = 100;
    int divisions = 100;
    double distance = 0;
    double energyStep = en/divisions;
    //get distance to lose the energy step
    while (en > 0.05) {
    double stoppingPower = coefCalc.getStoppingPower(en, false);
    distance += energyStep/stoppingPower;
    en -= energyStep;
    }
    distance = distance /1000;
  }
  
  public void CalculateEM(Beam beam, Wedge wedge, CoefCalc coefCalc) { // also pass in crystal dimensions
    // Just to be clear these are all dose of the exposed volume
 //   testingXFELQuick(beam, coefCalc);
    
    //get a CSDA range for any given electron energy
 //   getCSDArange(coefCalc);
    
    //getGOSinel
   // double test = coefCalc.getGOSInel(false);
    
    System.out.println(String.format("The density is: %.2e", coefCalc.getDensity()));
    
    double wavelength = getWavelength(beam);
    double resRough = getResolutionRough(wavelength);
    double maxRes = getMaxRes(wavelength);
    System.out.println(String.format("The rough maximum resolution is: %.2e", resRough));
    System.out.println(String.format("The max res is: %.2e", maxRes));
    
    
    double dose1 = EMLETWay(beam, wedge, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area by LET: %.8e", dose1));
    System.out.println(" MGy\n");
 
  
    double dose2 = EMEquationWay(beam, wedge, coefCalc, true);
    System.out.print(String.format("\nThe Dose in the exposed area by equation: %.8e", dose2));
    System.out.println(" MGy\n");
    
    dose2 = EMEquationWay(beam, wedge, coefCalc, false);
    System.out.print(String.format("\nThe Dose in the exposed area by 3:1: %.8e", dose2));
    System.out.println(" MGy\n");
    
    //calculate Sternheimer adjustment factor
    
    double dose3 = EMStoppingPowerWay(beam, wedge, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area by stopping power: %.8e", dose3));
    System.out.println(" MGy\n");
    
    //start the Monte carlo stuff
    long start = System.nanoTime();
    startMonteCarlo(coefCalc, beam); 
    double[] dose4 = processMonteCarloDose(beam, coefCalc);
    System.out.print(String.format("\nThe Dose in the exposed area by Monte Carlo: %.8e", dose4[0]));
    System.out.println(" MGy\n");
    System.out.print(String.format("The Dose in the imaged area by Monte Carlo: %.8e", dose4[1]));
    System.out.println(" MGy\n");
    long runtime = System.nanoTime() - start;
    System.out.println(String.format("The Monte Carlo runtime in seconds was: %.8e", runtime/1E9));
    MonteCarloRuntime = runtime/1E9;
    
    /*
    accessESTAR(coefCalc, beam.getPhotonEnergy());
    double dose4 = getESTARDose(coefCalc, beam);
    System.out.print(String.format("\nThe Dose in the exposed area by ESTAR: %.8e", dose4));
    System.out.println(" MGy\n");
    */
    
    System.out.println("\nNumber elastic events: " + numberElastic);
    System.out.println("Number single elastic events: " + numberSingleElastic);
    System.out.println("Number productive events: " + numberProductive);
    
    System.out.println("Number elastic events Monte Carlo: " + MonteCarloTotElasticCount);
    System.out.println("Number single elastic events Monte Carlo: " + MonteCarloSingleElasticCount);
    System.out.println("Number of productive electrons Monte Carlo: " + MonteCarloProductive);
    
    System.out.println("\nCharge buildup: " + MonteCarloCharge);
    System.out.println("Charge density " + MonteCarloChargeDensity);
    
    try {
      WriterFile("outputMicroED.CSV", dose4[0], beam);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.exit(0);
  }
  
  private double getWavelength(Beam beam) {
    double h = 6.626070040E-34;
    double c = 299792458;
    double csquared = c*c;
    double m0 = 9.109383356E-31; //Kg
    double V0 = beam.getPhotonEnergy()*Beam.KEVTOJOULES; 
    double lambda = (h*c)/Math.pow(Math.pow(V0, 2) + 2*V0*m0*csquared, 0.5); // in m
    lambda *= 1E10; //convert m to A
    return lambda;
  }
  
  private double getResolutionRough(double wavelength) {
    double a = 0.01; //radians
    double n = 1;
    return (wavelength / (2*n*Math.sin(a)));  
  }
  private double getMaxRes(double wavelength) {
    double Cs = 1E7; //A
    double res = Math.pow(Cs*Math.pow(wavelength, 3)/6, 0.25);
    return res;
  }
  

  private double EMLETWay(Beam beam, Wedge wedge, CoefCalc coefCalc) {
//  double electronNumber = beam.getPhotonsPerSec() * wedge.getTotSec(); // total incident electrons
  double exposure = beam.getExposure();
  
  //check if the beam is bigger or smaller than the sample - need to check in x and in y (x = horizontal, y = vertical)
  double exposedAreaY = getExposedY(beam);
  double exposedAreaX = getExposedX(beam);
  double totExposedArea = 0;
  if (beam.getIsCircular() == false) {
    totExposedArea = (exposedAreaX * exposedAreaY) * 1E08; //convert  um^2 to A^2
  }
  else {
    totExposedArea = Math.PI * ((exposedAreaX/2) * (exposedAreaY/2)) * 1E08; //convert  um^2 to A^2
  }
  
//  double electronNumber = exposure * totExposedArea;
  
  //Reduce electron number if beam bigger than the sample
  /*
  if (totExposedArea < (beam.getBeamX()*beam.getBeamY() * 1E08)) { 
    double fractionFlux = totExposedArea / (beam.getBeamX()*beam.getBeamY() * 1E08);
    electronNumber = electronNumber * fractionFlux; //convert total electron number to electron incident on the sample
  }
  */
  
//  double exposure = electronNumber/totExposedArea;  //exposure in e/A^2
  double beamEnergy = beam.getPhotonEnergy();
  
  
  
  double baseDose = 0;
  double theDose = 0;
  //set case exposure = 1
if (beamEnergy == 100) {
  baseDose = 6.6;
}
else if (beamEnergy == 200) {
  baseDose = 4.5;
}
else if (beamEnergy == 300) {
  baseDose = 3.7;
}
theDose = baseDose * exposure;
return theDose;
}

private double EMEquationWay(Beam beam, Wedge wedge, CoefCalc coefCalc, boolean useInelEqu) {
  double exposure = beam.getExposure();
 // double energyPerEvent = 0.02; //in keV
  double energyPerEvent = (7 * coefCalc.getZav())/1000; //in keV  //Change this to 7* Zav
  energyPerEvent = 0.025;

  //will need to edit when I add in circular
  double exposedArea = 0;
  if (beam.getIsCircular() == false) {
    exposedArea = (getExposedX(beam) * getExposedY(beam)); //um^2
  }
  else {
    exposedArea = Math.PI * ((getExposedX(beam)/2) * (getExposedY(beam)/2)); //um^2
  }
  double electronNumber = exposure * (exposedArea * 1E08);
  numElectrons = electronNumber;
  
  double exposedVolume = exposedArea  * (sampleThickness/1000) * 1E-15; //exposed volume in dm^3
  
//  double electronNumber = getElectronNumber(beam, wedge, exposedArea);

  
//  double solventFraction = coefCalc.getEMSolventFraction();
  
  //now I need to calcWaters here as don't have access to crystal properties in coefCalcEM 
 
  //way 1 - density

  //way 2 = their way
//  coefCalc.calculateSolventWaterEM(solventFraction, exposedVolume);
  //density
//  coefCalc.calculateDensityEM(exposedVolume);
//  System.out.println(String.format("\nDensity: %.2e", coefCalc.getDensity()));
  
  //Elastic collisions
  // put in multislice here as well
  double elasticProb = 0;
  double avgEnergy = beam.getPhotonEnergy();
  for (int i = 1; i <= numberSlices; i++) {
    double elasticProbOverT = coefCalc.getElectronElastic(avgEnergy);
    elasticProb += elasticProbOverT * (sampleThickness/numberSlices); 
    //I need to update the electron energy, will do this with the stopping power for consistency
    double stoppingPower = coefCalc.getStoppingPower(avgEnergy, false); //send it electron energy
    double energyPerEl =  stoppingPower * (sampleThickness/numberSlices);
    avgEnergy -= energyPerEl;
  }

  
  numberElastic = elasticProb * electronNumber;
  numberSingleElastic = electronNumber * 
                        Math.exp(-elasticProb) * (Math.pow(elasticProb, 1) / 1); //Poisson distribution
  
  //inelastic 
  double inelasticProbOverT = 0;
  double inelasticProb = 0;
  avgEnergy = beam.getPhotonEnergy();
  if (useInelEqu == true) {
    for (int i = 1; i <= numberSlices; i++) {
      inelasticProbOverT = coefCalc.getElectronInelastic(avgEnergy, exposedVolume);
      inelasticProb += inelasticProbOverT * (sampleThickness/numberSlices);
      
      //testing this
      double startingLambda_el = coefCalc.getElectronElasticMFPL(avgEnergy, false);
      inelasticProb = sampleThickness/coefCalc.getElectronInelasticMFPL(avgEnergy, false);
      
      
      //I need to update the electron energy, will do this with the stopping power for consistency
      double stoppingPower = coefCalc.getStoppingPower(avgEnergy, false); //send it electron energy
      double energyPerEl =  stoppingPower * (sampleThickness/numberSlices);
      avgEnergy -= energyPerEl;
    }
    numberNotInelasticEqu = Math.exp(-inelasticProb) * electronNumber;
  }
  else {
    inelasticProb = elasticProb * 3;
    numberNotInelasticRatio = Math.exp(-inelasticProb) * electronNumber;
  }
  
  numberProductive = numberSingleElastic* numberNotInelasticEqu / electronNumber;

  //calculate backscattering coefficient - Use Heinrichs equation as a start
  double eta = coefCalc.getEta();
  double numberBackscattered = electronNumber * (eta / 100);
  //how I deal with backscattering in terms of dose and productive is really not trivial!!!! 
  //If I take them off at the start, they don't contribute to dose so that is understimated
  //If I take them off just the productive, I'm overestimating dose a little
  //How do I take off - I need to take it off single elastic via a probability
  //Indep of beam energy also scares me a bit
  
  //I should defo do % that were elastically scattered within the specified angle from the
  //objective aperture as this is much better!!!!!!!!!!!!!!!!!
  
  
//Am I doing the mass right???? What is dose it is energy per mass of all right not just protein....
  double numberInelasticEvents = (inelasticProb * electronNumber);
  double energyDeposited = (energyPerEvent * numberInelasticEvents) * Beam.KEVTOJOULES; //in J
  double exposedMass = (((coefCalc.getDensity()*1000) * exposedVolume) / 1000);  //in Kg 
  double dose = (energyDeposited/exposedMass) / 1E06; //dose in MGy //thickness isn't making a difference on dose as mass increases with it
  
  return dose;
}

private double EMStoppingPowerWay(Beam beam, Wedge wedge, CoefCalc coefCalc) {
  double exposedArea = 0;
  double exposure = beam.getExposure();
  if (beam.getIsCircular() == false) {
    exposedArea = (getExposedX(beam) * getExposedY(beam)); //um^2
  }
  else {
    exposedArea = Math.PI * ((getExposedX(beam)/2) * (getExposedY(beam)/2)); //um^2
  }
  double electronNumber = exposure * (exposedArea * 1E08);
  
  double exposedVolume = exposedArea  * ((sampleThickness/1000)) * 1E-15; //exposed volume in dm^3
  double exposedMass = (((coefCalc.getDensity()*1000) * exposedVolume) / 1000);  //in Kg 
  double stoppingPower = 0, energyDeposited = 0, dose = 0;
  double avgEnergy = beam.getPhotonEnergy();
  for (int i = 1; i <= numberSlices; i++) {
    // need to get the stopping power from coefcalc
    stoppingPower = coefCalc.getStoppingPower(avgEnergy, false); //send it electron energy

 
    double energyPerEl =  stoppingPower * (sampleThickness/numberSlices);
    avgEnergy -= energyPerEl; 
    energyDeposited = electronNumber * energyPerEl * Beam.KEVTOJOULES;  //in J, currently per electron
    dose += (energyDeposited/exposedMass) / 1E06; //dose in MGy 
  }
  return dose;
}
/**
 * Returns the exposed area in the x dimensions of the sample in um
 * 
 * @param beam
 * @return exposedAreaX
 */
private double getExposedX(Beam beam) {
  double exposedAreaX;
  double beamX = beam.getBeamX();
  if (XDimension/1000 > beamX) {
    exposedAreaX = beamX;
  }
  else {
    exposedAreaX = XDimension/1000;
  }
  return exposedAreaX;
}

/**
 * Returns the exposed area in the y dimensions of the sample in um
 * 
 * @param beam
 * @return exposedAreaY
 */
private double getExposedY(Beam beam) {
  double exposedAreaY;
  double beamY = beam.getBeamY();

  if (YDimension/1000 > beamY) {
    exposedAreaY = beamY;
  }
  else {
    exposedAreaY = YDimension/1000;
  }
  return exposedAreaY;
}

private void WriterFile(final String filename, final double dose4, Beam beam) throws IOException {
  BufferedWriter outFile;
  outFile = new BufferedWriter(new OutputStreamWriter(
      new FileOutputStream(filename), "UTF-8"));
  try {
    outFile.write("beam_en, numSimulated, dose, gosDose, Productive, Unproductive\n");
    outFile.write(String.format(
        " %f, %d, %f, %f, %f, %f%n", beam.getPhotonEnergy(), numSimulatedElectrons, doseOutput, gosDoseOutput, MonteCarloProductive, MonteCarloUnproductiveMicroED));
  } catch (IOException e) {
    e.printStackTrace();
    System.err.println("WriterFile: Could not write to file " + filename);
  }
  
  try {
    outFile.close();
  } catch (IOException e) {
    e.printStackTrace();
    System.err.println("WriterFile: Could not close file " + filename);
  }
}

private void writeDoseCSV(final String filename) throws IOException {
  BufferedWriter outFile;
  outFile = new BufferedWriter(new OutputStreamWriter(
      new FileOutputStream(filename), "UTF-8"));
  try {
    outFile.write("X,Y,Z,scalar\n");
    for (int i = 0; i < maxX; i++) {
      for (int j = 0; j < maxY; j++) {
        for (int k = 0; k < maxZ; k++) {
          double[] coords = convertToCartesianCoordinates(i, j, k);
          outFile.write(String.format(
              "%f,%f,%f,%f%n", coords[0], coords[1], coords[2], voxelDose[i][j][k]));
        }
      }
    }
    
  } catch (IOException e) {
    e.printStackTrace();
    System.err.println("WriterFile: Could not write to file " + filename);
  }
  
  try {
    outFile.close();
  } catch (IOException e) {
    e.printStackTrace();
    System.err.println("WriterFile: Could not close file " + filename);
  }
}

private void writeAngleCSV(HashMap<Double, Double> EnAngles, double startEn, ArrayList<Double> inelAngles) throws IOException{
  String filename = "OutputAngles.csv";
  BufferedWriter outFile;
  outFile = new BufferedWriter(new OutputStreamWriter(
      new FileOutputStream(filename), "UTF-8"));
  
  //write the headings
  try {
  //  outFile.write("Beam energy,Thickness, Energy lost,Angle\n");
    outFile.write("Beam energy, Thickness, Angle\n");
  } catch (IOException e) {
    e.printStackTrace();
    System.err.println("WriterFile: Could not write to file " + filename);
  }
  
 // for (Double e: EnAngles.keySet()) {
  for (int i = 0; i < inelAngles.size(); i++) {
    try {
    outFile.write(String.format(
   //     " %f,%f,%f,%f%n", startEn, ZDimension,e, EnAngles.get(e)));
        " %f,%f,%f%n", startEn, ZDimension, inelAngles.get(i)));
    }catch (IOException e1) {
      e1.printStackTrace();
      System.err.println("WriterFile: Could not write to file " + filename);
    }
  }

  try {
    outFile.close();
  } catch (IOException e) {
    e.printStackTrace();
    System.err.println("WriterFile: Could not close file " + filename);
  }
  
}

public void accessESTAR(CoefCalc coefCalc, double avgElectronEnergy) {
  String exePath = "lib\\selenium\\chromedriver.exe";
  System.setProperty("webdriver.chrome.driver", exePath);
// Create a new instance of the Firefox driver
  WebDriver driver = new ChromeDriver();
  //Launch the Website
  driver.get("https://physics.nist.gov/PhysRefData/Star/Text/ESTAR-u.html");
  
  //Enter material name
  WebElement name = driver.findElement(By.name("Name"));
  name.sendKeys("Protein");
  
  //Enter density
  double densityNum = coefCalc.getDensity();
  String densityString = Double.toString(densityNum);
  WebElement density = driver.findElement(By.name("Density"));
  density.sendKeys(densityString);
  
  //Enter element fractions
  Map<String, Double> fractionElementEM = new HashMap<String, Double>();
  fractionElementEM = coefCalc.getFractionElementEM();
  WebElement fractions = driver.findElement(By.name("Formulae"));
  NumberFormat formatNoSF = new DecimalFormat();
  formatNoSF = new DecimalFormat("0.000000"); //will break if in standard form
  
  for (String elementName : fractionElementEM.keySet()) {
    String fractionElement = formatNoSF.format(fractionElementEM.get(elementName));
    String toSend = (elementName + " " + fractionElement); 
    //Write this in the textbox
    fractions.sendKeys(toSend);
    fractions.sendKeys(Keys.RETURN);
  }
  
  //submit this information
  WebElement submit = driver.findElement(By.cssSelector("input[value='Submit']"));
  submit.click();
  
  
  //enter the beam energy
  String beamMeV = Double.toString((avgElectronEnergy / 1000));
  WebElement energy = driver.findElement(By.name("Energies"));
  energy.sendKeys(beamMeV);
  //uncheck default energies
  WebElement checkBox = driver.findElement(By.cssSelector("input[value='on']"));
  checkBox.click();
  //remove the graph as unnecessary
  WebElement radioButton = driver.findElement(By.cssSelector("input[value='None']"));
  radioButton.click();
  //submit this page
  submit = driver.findElement(By.cssSelector("input[value='Submit']"));
  submit.click();
  
  //select to output total stopping power
  checkBox = driver.findElement(By.name("total"));
  checkBox.click();
  //Download data
  submit = driver.findElement(By.cssSelector("input[value='Download data']"));
  submit.click();
  
  //copy and paste whole page
  Actions action = new Actions(driver); 
  action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0061')).perform();
  action.keyUp(Keys.CONTROL).perform();
  action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0063')).perform();
  String wholeTable = getSysClipboardText();

  //get beam energy in a string
  double MeV = avgElectronEnergy/1000;
  NumberFormat formatter = new DecimalFormat();
  formatter = new DecimalFormat("0.000E00");
  String beamEnergy = formatter.format(MeV); 
  // search using beam energy
  int beamEnergyIndex = wholeTable.indexOf(beamEnergy);
  String numbers = wholeTable.substring(beamEnergyIndex);
  //find stopping power by the space
  int spaceIndex = numbers.indexOf(" ");
  String stoppingPowerString = numbers.substring(spaceIndex + 1);
  stoppingPowerString = stoppingPowerString.trim();
  
  stoppingPowerESTAR = Double.parseDouble(stoppingPowerString);
  driver.quit(); // close all windows opened by selenium
   
}

/**
* get string from Clipboard
*/
public static String getSysClipboardText() {
   String ret = "";
   Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();

   Transferable clipTf = sysClip.getContents(null);

   if (clipTf != null) {

       if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
           try {
               ret = (String) clipTf
                       .getTransferData(DataFlavor.stringFlavor);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }

   return ret;
}

private double getESTARDose(CoefCalc coefCalc, Beam beam) {
  double exposedArea = 0;
  if (beam.getIsCircular() == false) {
    exposedArea = (getExposedX(beam) * getExposedY(beam)); //um^2
  }
  else {
    exposedArea = Math.PI * ((getExposedX(beam)/2) * (getExposedY(beam)/2)); //um^2
  }
  
  double exposedVolume = exposedArea  * (sampleThickness/1000) * 1E-15; //exposed volume in dm^3
  double exposure = beam.getExposure();
  double electronNumber = exposure * (exposedArea * 1E08);
  
  double stoppingPower = (stoppingPowerESTAR * coefCalc.getDensity() * 1000) / 1E7; //keV/nm
  
  double energyDeposited = electronNumber * stoppingPower * sampleThickness * Beam.KEVTOJOULES;  //in J, currently per electron
  
  double exposedMass = (((coefCalc.getDensity()*1000) * exposedVolume) / 1000);  //in Kg 
  double dose = (energyDeposited/exposedMass) / 1E06; //dose in MGy 
  
  return dose;
}

// Everything below will be the Monte Carlo section of the code
private void startMonteCarlo(CoefCalc coefCalc, Beam beam) {
 // boolean GOS = true;
  int triggered = 0; //testing
  int thisTriggered = 0; //testing
  
  //get number of electrons to simulate
  long numSim = coefCalc.getNumberSimulatedElectrons();
  if (numSim == 0) {
    numSim = NUM_MONTE_CARLO_ELECTRONS;
    numSim = 10000;
  }
  numSimulatedElectrons = numSim;
  
  
  //set up for one electron to start with and then test how many needed to get little deviation and then scale up
  int numberBackscattered = 0;
  //Start stuff to make quicker
  
  // These are for the sample, need to do them all again for the surrounding 
  
  double startingEnergy = beam.getPhotonEnergy();
  double startingStoppingPower = coefCalc.getStoppingPower(startingEnergy, false);
  double startingLambda_el = coefCalc.getElectronElasticMFPL(startingEnergy, false);
  Map<ElementEM, Double> elasticProbs = coefCalc.getElasticProbs(false);
  
  //the FSE stuff 
  double startingFSExSection = getFSEXSection(startingEnergy);
  double startingFSELambda = coefCalc.getFSELambda(startingFSExSection, false);
  
  //Inner shell ionisation x section
  coefCalc.populateCrossSectionCoefficients();
  double startingInnerShellLambda = coefCalc.betheIonisationxSection(startingEnergy, false);
 // Map<Element, Double> ionisationProbs = coefCalc.getInnerShellProbs(); //Really need to make sure that these are in the same order
  Map<Element, double[]> ionisationProbs = coefCalc.getAllShellProbs(false); //Really need to make sure that these are in the same order
  
  //plasmon stuff
  
  double startingPlasmonLambda = coefCalc.getPlasmaMFPL(startingEnergy, false);
  double plasmaEnergy = coefCalc.getPlasmaFrequency(false)/1000.0; //in keV
  
  
  double gosInelasticLambda = 0, gosInnerLambda = 0, gosOuterLambda = 0;
  Map<Element, double[]> gosIonisationProbs = null;
  Map<Element, Double> gosOuterIonisationProbs = null;
 // if (surrounding == false) {
  if (GOS == true) {
    gosInelasticLambda = coefCalc.getGOSInel(false, startingEnergy);
    gosInnerLambda = coefCalc.getGOSInnerLambda(false);
    gosOuterLambda = coefCalc.getGOSOuterLambda(false);
    gosOuterIonisationProbs = coefCalc.getGOSOuterShellProbs(false, gosOuterLambda); //note atm this does not work with plasma in this way
    gosIonisationProbs = coefCalc.getGOSShellProbs(false, gosInelasticLambda);
    if (startingInnerShellLambda > 0) {
      gosInelasticLambda = 1/(1/gosOuterLambda + 1/startingInnerShellLambda);
    }
    else {
      gosInelasticLambda = gosOuterLambda;
    }
  }
  //}
  
  
  //tot inelastic
  double startingInelasticLambda = coefCalc.getElectronInelasticMFPL(startingEnergy, false);
  Map<ElementEM, Double> elasticProbsSurrounding = null;
  Map<Element, double[]> ionisationProbsSurrounding = null;
  Map<Element, double[]> gosIonisationProbsSur = null;
  Map<Element, Double> gosOuterIonisationProbsSur = null;
  //now do all of the starting stuff again for electrons in the surrounding 
  double startingStoppingPowerSurrounding = 0, startingLambda_elSurrounding = 0, startingInelasticLambdaSurrounding = 0;
  double startingFSELambdaSurrounding = 0, startingInnershellLambdaSurrounding = 0, gosInelasticLambdaSur = 0, gosInnerLambdaSur = 0, gosOuterLambdaSur = 0;
  if (coefCalc.isCryo()) {
    //stopping power
    startingStoppingPowerSurrounding = coefCalc.getStoppingPower(startingEnergy, true);
    //elastic
    startingLambda_elSurrounding = coefCalc.getElectronElasticMFPL(startingEnergy, true);
    elasticProbsSurrounding = coefCalc.getElasticProbs(true);
    //total inelastic
    startingInelasticLambdaSurrounding = coefCalc.getElectronInelasticMFPL(startingEnergy, true); 
    //FSE stuff
    startingFSELambdaSurrounding = coefCalc.getFSELambda(startingFSExSection, true); //xSection per electron is the same
    //inner shell ionisation
    startingInnershellLambdaSurrounding = coefCalc.betheIonisationxSection(startingEnergy, true);
    ionisationProbsSurrounding = coefCalc.getAllShellProbs(true);
    if (GOS == true) {
    gosInelasticLambdaSur = coefCalc.getGOSInel(true, startingEnergy);
    gosInnerLambdaSur  = coefCalc.getGOSInnerLambda(true);
    gosOuterLambdaSur  = coefCalc.getGOSOuterLambda(true);
    gosOuterIonisationProbsSur  = coefCalc.getGOSOuterShellProbs(true, gosOuterLambdaSur); //note atm this does not work with plasma in this way
    gosIonisationProbsSur  = coefCalc.getGOSShellProbs(true, gosInelasticLambdaSur);
    if (startingInnershellLambdaSurrounding  > 0) {
      gosInelasticLambdaSur  = 1/(1/gosOuterLambdaSur  + 1/startingInnershellLambdaSurrounding);
    }
    else {
      gosInelasticLambdaSur = gosOuterLambdaSur;
    }
    }
  }
  
  
  //not going to change the direction of the program yet going to write it separately and then
  //incorporate it in -remember that lambda_el now needs to always be lambda_t!!!!!!!!!
  
  //test ELSEPA
 // startingLambda = 236;
  double progress = 0, lastProgress = 0;
  inelEnAngle = new HashMap<Double, Double>();
  for (int i = 0; i < numSimulatedElectrons; i++) { //for every electron to simulate
    simNumber = i;
    progress = ((double)i)/numSimulatedElectrons;
    if (progress - lastProgress >= 0.05) {
      lastProgress = progress;
      System.out.print((int)(progress*100) + "% ");
    }
    //position stuff first
    
    //Need to change these to a uniform beam
    double previousX = 0, previousY = 0; //atm starting going straight 
    double xNorm = 0.0000, yNorm = 0.0000, zNorm = 1.0; //direction cosine are such that just going down in one
    double theta = 0, phi = 0, previousTheta = 0, previousPhi = 0, thisTheta = 0;
    
    double aboveThickness = 0;
    if (coefCalc.isCryo()){
      aboveThickness = 0;
    }
    
    double previousZ = (-ZDimension/2)-aboveThickness;  //dodgy if specimen not flat - change for concave holes
    
    //position
    double RNDx = Math.random();
    double beamX = beam.getBeamX()*1000;
    previousX = (RNDx * XDimension) - (XDimension/2); //places on sample
    previousX = (0.99*RNDx * beamX) - (beamX/2); //places in beam area
    
    double RNDy = Math.random();
    double beamY = beam.getBeamY()*1000;
    previousY = (RNDy * YDimension) - (YDimension/2);
    if (beam.getIsCircular()) {   //reduce Y limits so you can't put it out of the circle / ellipse
  //    double fractionLimit = 1 - Math.pow(Math.pow(previousX/(beamX/2), 2), 0.5);
  //    RNDy *= fractionLimit;
      previousY = 0.99*RNDy*Math.pow(Math.pow(beamY/2, 2)*(0.99-(Math.pow(previousX, 2)/Math.pow(beamX/2, 2))), 0.5);
      previousY *= PosOrNeg();
    }
    else {
      previousY = (RNDy * beamY) - (beamY/2);
    }
    
    
    //so the circle/ellipse is currently wrong as it is overly biasing the x towards the edges of the circle
    if (beam.getIsCircular()) { 
      //just do for a circle for now, choose polar coordinates and convert to cartesian
      double r = (beamX/2);
      double u = Math.random()+Math.random();
      if (u > 1) {
        r = r* (2-u);
      }
      else {
        r = r*u;
      }
      double polarAngle = Math.random()*2*Math.PI;
      previousX = r*Math.cos(polarAngle);
      previousY = r*Math.sin(polarAngle);
    }
    
    
    
    //direction 
    double[] directionVector = getElectronStartingDirection(beam, previousX, previousY, previousZ);
    xNorm = directionVector[0];
    yNorm = directionVector[1];
    zNorm = directionVector[2];
    
    //need to update theta and phi for these direction vectors 
    
    // theta angle between 0 0 1 and vector, phi angle between 1 0 0 and vector
    double[] zaxis = {0, 0, 1};
    theta = Math.acos(Vector.dotProduct(zaxis, directionVector));
    double[]xaxis = {1, 0, 0};
    double[] phiVector = {xNorm, yNorm, 0};
    //test
//    double[] phiVector = {0, -1, 0};
    double phiVectorMag = Vector.vectorMagnitude(phiVector);
    for (int m = 0; m <= 2; m++) {
      phiVector[m] /= phiVectorMag;
    }
    phi = Math.acos(Vector.dotProduct(xaxis, phiVector));
    if (yNorm < 0) {
      phi = 2*Math.PI - phi;  //so phi can be between 0 and 2pi not just pi
    }
    
   
    //determine if the electron is incident on the sample or not - 
    boolean surrounding = !isMicrocrystalAt(previousX, previousY, 0); //Z = 0 as just looking at x and y
    if (surrounding == false) {
      hit +=1;
    }
    
    // if it is a certain distance away from the sample ignore it entirely - if it is times 2?
    boolean track = false;
    if (surrounding == true) {
      double distanceFrom = Math.pow(Math.pow(Math.abs(previousX) - (XDimension/2), 2) + Math.pow(Math.abs(previousY) - (YDimension/2), 2), 0.5);
      double distanceOf = Math.pow(Math.pow(XDimension, 2) + Math.pow(YDimension, 2), 0.5);
      if (distanceFrom < distanceOf) {
        track = true;
      }
    }
    //if it isn't I need to track it, untrack if exits this boundary I set up, deposit dose if something goes in the xtal
    //secondaries from the surrounding should only be tracked if they are heading in the right direction initially 
    //remember to adjust how the Monte Carlo dose is adjusted accordingly 
    if (coefCalc.isCryo() == false) {
      track = false;
    }  

  boolean inelastic = false;
  boolean backscattered = false;
  int elasticCount = 0;
  double electronEnergy = startingEnergy;
  double energyLost = 0;
  
  double stoppingPower = 0, lambdaT = 0, PEL = 0, Pinel = 0, Pfse = 0, s = 0, xn = 0, yn = 0, zn = 0, Pinner=0;
  boolean entered = false;
  if (surrounding == true) {
    stoppingPower = startingStoppingPowerSurrounding;
 //   lambdaT = 1 / (1/startingLambda_elSurrounding + 1/startingInelasticLambdaSurrounding);
  //  lambdaT = 1 / (1/startingLambda_elSurrounding + 1/gosInelasticLambdaSur);
    lambdaT = 1 / (1/startingLambda_elSurrounding + 1/startingInelasticLambdaSurrounding);
    if (GOS == true) {
    if (gosInelasticLambdaSur > 0) {
      lambdaT = 1 / (1/startingLambda_elSurrounding + 1/gosInelasticLambdaSur);
    }
    else {
      lambdaT = startingLambda_elSurrounding;
    }
    }
    PEL = lambdaT / startingLambda_elSurrounding;
    Pinel = 1 - (lambdaT / startingLambda_elSurrounding);
    if (GOS == true) {
    if (startingInnershellLambdaSurrounding > 0) {
      Pinner = (gosInelasticLambdaSur/startingInnershellLambdaSurrounding);
    }
    }
    Pfse = startingInelasticLambdaSurrounding/startingFSELambdaSurrounding;
    double testRND = Math.random();
    s = -lambdaT*Math.log(testRND);
    // I need to check if it's going to intersect and what the distance is
    double intersectionDistance = 1000*getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm);
    double[] intersectionPoint = getIntersectionPoint(intersectionDistance, previousX, previousY, previousZ, xNorm, yNorm, zNorm);
    boolean pointInCrystal = isIntersectionInCrystal(intersectionPoint);
    if (intersectionDistance < s && pointInCrystal == true) { //then need to change region here and reset stuff
      surrounding = false;
      entered = true;
      
      if (GOS == false) {
        electronEnergy -= intersectionDistance * stoppingPower; //taken out for GOS
      }
      
      previousX = intersectionPoint[0]*1000;
      previousY = intersectionPoint[1]*1000;
      previousZ = intersectionPoint[2]*1000;
    }
  }
  
  
  if (surrounding == false) {
    if (electronEnergy < startingEnergy) { //if this electron has entered from the surrounding 
      stoppingPower = coefCalc.getStoppingPower(electronEnergy, false);
    }
    else {
    stoppingPower = startingStoppingPower;
    }
    
    
   // double lambdaT = startingLambda_el; //lambda in nm  -//Just elastic
   // double lambdaT = 1 / (1/startingLambda_el + 1/startingFSELambda); //FSE one
  //  double lambdaT = 1 / (1/startingLambda_el + 1/startingFSELambda + 1/startingPlasmonLambda);
   // double lambdaT = 1 / (1/startingLambda_el + 1/startingInnerShellLambda + 1/startingFSELambda);
    lambdaT = 1 / (1/startingLambda_el + 1/startingInelasticLambda);
 //   lambdaT = 1 / (1/startingLambda_el + 1/gosInelasticLambda);
 //   lambdaT = 1 / (1/startingLambda_el + 1/startingFSELambda);

if (GOS == true) {
    if (gosInelasticLambda > 0) {
      lambdaT = 1 / (1/startingLambda_el + 1/gosInelasticLambda);
    }
    else {
      lambdaT = startingLambda_el;
    }
    if (startingInnerShellLambda > 0) {
      Pinner = (gosInelasticLambda/startingInnerShellLambda);
    }
}
    
    PEL = lambdaT / startingLambda_el;
    Pinel = 1 - (lambdaT / startingLambda_el);
    
    
  //  double Pplasmon = startingPlasmonLambda/ (startingFSELambda + startingPlasmonLambda);
  // double PinnerShell = startingFSELambda/(startingInnerShellLambda + startingFSELambda); //this is not making sense, it's all innner shell now
  //  Pfse = startingInelasticLambda/startingFSELambda;
    Pfse = 1;
    double testRND = Math.random();
    s = -lambdaT*Math.log(testRND);
    //now I'm going to go through the coordinates
  
  //  double ca = cx;
  //  double cb = cy;
  //  double cc = cz;

  }

  xn = previousX + s * xNorm;
  yn = previousY + s * yNorm;
  zn = previousZ + s * zNorm;
  
  
  boolean exited = false;
  boolean scattered = false;
  
  
  long timesScattered = 0, scatteredSol = 0, scatteredMacro = 0;
  //check if the electron has left the sample, if it has just do the dose of Z
  //if it has not left move onto the loop
  while (exited == false) {
  if (isMicrocrystalAt(xn, yn, zn) == true) {
    if (surrounding == true) {
      entered = true;
    }
    surrounding = false;
    ElementEM elasticElement = null;
    scattered = true;
    thisTriggered += 1;
    
    //update dose and energy and stoppingPower
    energyLost = s * stoppingPower;
    //will need to split this energy lost up to get the full spatially resolved dose model
    avgDist += s;
    MonteCarloDose += energyLost;   //keV
    
    //split the dose up into voxels
  //  addDoseToVoxels(s, xNorm, yNorm, zNorm, previousX, previousY, previousZ, energyLost, beam, coefCalc);
    addDoseToRegion(s, xNorm, yNorm, zNorm, previousX, previousY, previousZ, energyLost);
 //   addDoseToImagedRegion(s, xNorm, yNorm, zNorm, previousX, previousY, previousZ, energyLost, beam);
    
    //reset
    previousTheta = theta;
    previousPhi = phi;
    previousX = xn;
    previousY = yn;
    previousZ = zn;

     
    //add an elastic collision

    //Determining if the scattering event was inelastic or elastic 
    double RNDscatter = Math.random();
//    RNDscatter = 0; // test
 //   double phi = 0, cosPhi = 1, psi = 0, AN = 0, AM = 0, V1 = 0, V2 = 0, V3 = 0, V4 = 0;
    
    if (RNDscatter < Pinel) {
      //Do inelastic
      inelastic = true;
      /*
      //if plasmon do plasmon
      double RNDplasmon = Math.random();
      if (RNDplasmon > Pplasmon) {
        theta = 0;
        totPlasmonEnergy += plasmaEnergy;
      }
      */
      //if no secondary elecrton produced (other type of inelastic interaction such as a plasmon)
      double RNDFSE = Math.random();
   //   if (RNDFSE > Pfse) { //this was another interaction
   //     theta = 0;
   //   }
    //  else {
      //else produce an FSE
      triggered += 1;
      theta = doPrimaryInelastic(coefCalc, previousX, previousY, previousZ, electronEnergy, ionisationProbs, false, beam, i, previousTheta, previousPhi, Pinner, gosOuterIonisationProbs, entered, xNorm, yNorm, zNorm);
    //  inelAngleSum += theta;
    //  inelAngleCount += 1;
     // inelAngles.add(theta);
      if (GOS == true) {
        electronEnergy -= energyLostGOS;
      }
    //  } //end if not plasmon
    } //end if inelastic scatter
    else { //else it stays false and the collision will be elastic
      elasticCount += 1;
      timesScattered += 1;
      MonteCarloTotElasticCount += 1;
      
      //reupdate elastic probs because Monte carlo seconadry may have messed it up
      double fix = coefCalc.getElectronElasticMFPL(electronEnergy, false);
      elasticProbs = coefCalc.getElasticProbs(false);
      theta = doPrimaryElastic(electronEnergy, elasticProbs, false, coefCalc);
      if (scatteredSolvent == false) {
        scatteredMacro += 1;
      }
      else {
        scatteredSol += 1;
      }
    }
    //now further update the primary
    /*
    phi =  2 * Math.PI * Math.random();
    theta = previousTheta + theta;
    if (theta >= (2 * Math.PI)) {
      theta -= 2*Math.PI;
    }
    phi = previousPhi + phi;
    if (phi >= (2 * Math.PI)) {
      phi -= 2*Math.PI;
    }
    */
    double scatterPhi = getScatteringPhi();
   
    double[] newDirectionVector = getNewDirectionVector(xNorm, yNorm, zNorm, theta, scatterPhi);
    /*
    xNorm = Math.sin(theta) * Math.cos(phi);
    yNorm = Math.sin(theta) * Math.sin(phi);
    zNorm = Math.cos(theta);
    */
    xNorm = newDirectionVector[0];
    yNorm = newDirectionVector[1];
    zNorm = newDirectionVector[2];
    theta = Math.acos(zNorm);
    phi = Math.acos(xNorm / Math.sin(theta));
    
      //update stopping powers
      //get new stoppingPower
    
    
    //  electronEnergy -= energyLost; //this needs to be changed for GOS
      
      
      stoppingPower = coefCalc.getStoppingPower(electronEnergy, false);
      //get new lambdaT
      double FSExSection = getFSEXSection(electronEnergy);
      double FSELambda = coefCalc.getFSELambda(FSExSection, false);
      double lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, false);
      double lambdaInel = coefCalc.getElectronInelasticMFPL(electronEnergy, false);
      double innerShellLambda = coefCalc.betheIonisationxSection(electronEnergy, false);
      double plasmonLambda = coefCalc.getPlasmaMFPL(electronEnergy, false);
    //  lambdaT =  1 / (1/lambdaEl + 1/FSELambda);
  //    lambdaT = 1 / (1/lambdaEl + 1/FSELambda + 1/plasmonLambda);
 //     lambdaT =  1 / (1/lambdaEl + 1/innerShellLambda + 1/FSELambda);
 //     lambdaT =  1 / (1/lambdaEl);
      lambdaT = 1 / (1/lambdaEl + 1/lambdaInel);
      if (GOS == true) {
      gosInelasticLambda = coefCalc.getGOSInel(false, electronEnergy);
      gosOuterLambda = coefCalc.getGOSOuterLambda(false);
      gosOuterIonisationProbs = coefCalc.getGOSOuterShellProbs(false, gosOuterLambda);
      gosIonisationProbs = coefCalc.getGOSShellProbs(false, gosInelasticLambda);
      if (innerShellLambda > 0) {
        gosInelasticLambda = 1/(1/gosOuterLambda + 1/innerShellLambda);
      }
      else {
        gosInelasticLambda = gosOuterLambda;
      }
      
      
      lambdaT = 1 / (1/lambdaEl + 1/gosInelasticLambda);
      }
      s = -lambdaT*Math.log(Math.random());
      PEL = lambdaT / lambdaEl;
      Pinel = 1 - (lambdaT / lambdaEl);
     // Pfse = lambdaInel / FSELambda;
      Pfse = 1;
      if (GOS == true) {
      if (innerShellLambda > 0) {
        Pinner = gosInelasticLambda / innerShellLambda;
      }
      }
 //     Pplasmon = plasmonLambda/ (FSELambda + plasmonLambda); 
 //     PinnerShell = FSELambda/(innerShellLambda + FSELambda);
      
      ionisationProbs = coefCalc.getAllShellProbs(false);
      elasticProbs = coefCalc.getElasticProbs(false);
      
      //update to new position
      xn = previousX + s * xNorm;
      yn = previousY + s * yNorm;
      zn = previousZ + s * zNorm;
      
  }
  else {
    if (surrounding == false) {
      exited = true;
      //find the plane it is crossing somehow
      s = 1000 * getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm);
      //I'm going to get the point as well for now as it may be useful when doing apertures and stuff
      //It's also useful for backscattering!!!!
      double[] intersectionPoint = getIntersectionPoint(s, previousX, previousY, previousZ, xNorm, yNorm, zNorm);
      energyLost = s * stoppingPower;
      avgDist += s;
      MonteCarloDose += energyLost;   //keV
      //split the dose up into voxels
     // addDoseToVoxels(s, xNorm, yNorm, zNorm, previousX, previousY, previousZ, energyLost, beam, coefCalc);
      addDoseToRegion(s, xNorm, yNorm, zNorm, previousX, previousY, previousZ, energyLost);
 //     addDoseToImagedRegion(s, xNorm, yNorm, zNorm, previousX, previousY, previousZ, energyLost, beam);
      if (1000*intersectionPoint[2] == -ZDimension/2 || zNorm < 0) {
        numberBackscattered += 1;
        backscattered = true;
      }
    }
    else { // surrounding = true 
      // track this electron until track = false - it is out of Z area or the extended area 
      //check whether to track it or not
      if (track == true){
      double distanceFrom = Math.pow(Math.pow(Math.abs(xn) - (XDimension/2), 2) + Math.pow(Math.abs(yn) - (YDimension/2), 2), 0.5);
      double distanceOf = Math.pow(Math.pow(XDimension, 2) + Math.pow(YDimension, 2), 0.5);
      if (distanceFrom > distanceOf || zn > ZDimension/2 || zn < -ZDimension/2) {
        track = false;
      }
      }
      if (track == true) {
        previousTheta = theta;
        previousPhi = phi;
        previousX = xn;
        previousY = yn;
        previousZ = zn;
        //update dose and energy and stoppingPower
        energyLost = s * stoppingPower;
        
        double RNDscatter = Math.random();
//      RNDscatter = 0; // test
   //   double phi = 0, cosPhi = 1, psi = 0, AN = 0, AM = 0, V1 = 0, V2 = 0, V3 = 0, V4 = 0;
      
      if (RNDscatter < Pinel) {
        //Do inelastic
        inelastic = true;
        /*
        //if plasmon do plasmon
        double RNDplasmon = Math.random();
        if (RNDplasmon > Pplasmon) {
          theta = 0;
          totPlasmonEnergy += plasmaEnergy;
        }
        */
        //if no secondary elecrton produced (other type of inelastic interaction such as a plasmon)
        double RNDFSE = Math.random();
    //    if (RNDFSE > Pfse) { //this was another interaction
    //      theta = 0;
    //    }
     //   else {
        //else produce an FSE
        triggered += 1;
        theta = doPrimaryInelastic(coefCalc, previousX, previousY, previousZ, electronEnergy, ionisationProbsSurrounding, true, beam, i, previousTheta, previousPhi, Pinner, gosOuterIonisationProbsSur, entered, xNorm, yNorm, zNorm);
        if (GOS == true) {
          electronEnergy -= energyLostGOS;
        }
    //    } //end if not plasmon
      } //end if inelastic scatter
      else { //else it stays false and the collision will be elastic
        elasticCount += 1;
        timesScattered += 1;
        MonteCarloTotElasticCount += 1;
        double fix = coefCalc.getElectronElasticMFPL(electronEnergy, true);
        elasticProbsSurrounding = coefCalc.getElasticProbs(true);
        theta = doPrimaryElastic(electronEnergy, elasticProbsSurrounding, true, coefCalc);
        scatteredSol += 1;
      }
      //now further update the primary
      /*
      phi =  2 * Math.PI * Math.random();
      theta = previousTheta + theta;
      if (theta >= (2 * Math.PI)) {
        theta -= 2*Math.PI;
      }
      phi = previousPhi + phi;
      if (phi >= (2 * Math.PI)) {
        phi -= 2*Math.PI;
      }
     
      xNorm = Math.sin(theta) * Math.cos(phi);
      yNorm = Math.sin(theta) * Math.sin(phi);
      zNorm = Math.cos(theta);
      */
      double scatterPhi = getScatteringPhi();
      double[] newDirectionVector = getNewDirectionVector(xNorm, yNorm, zNorm, theta, scatterPhi);
      xNorm = newDirectionVector[0];
      yNorm = newDirectionVector[1];
      zNorm = newDirectionVector[2];
      theta = Math.acos(zNorm);
      phi = Math.acos(xNorm / Math.sin(theta));
        //update stopping powers
        //get new stoppingPower
      
      if (GOS == false) {
        electronEnergy -= energyLost; //not here for the GOS
      }
        
        stoppingPower = coefCalc.getStoppingPower(electronEnergy, true);
        //get new lambdaT
        double FSExSection = getFSEXSection(electronEnergy);
        double FSELambda = coefCalc.getFSELambda(FSExSection, true);
        double lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, true);
        double lambdaInel = coefCalc.getElectronInelasticMFPL(electronEnergy, true);
        double innerShellLambdaSurrounding = coefCalc.betheIonisationxSection(electronEnergy, true);
        double plasmonLambda = coefCalc.getPlasmaMFPL(electronEnergy, true);
      lambdaT = 1 / (1/lambdaEl + 1/lambdaInel);
        if (GOS == true) {
        gosInelasticLambdaSur = coefCalc.getGOSInel(true, electronEnergy);
        gosOuterLambdaSur = coefCalc.getGOSOuterLambda(true);
        gosOuterIonisationProbsSur = coefCalc.getGOSOuterShellProbs(true, gosOuterLambdaSur);
        gosIonisationProbsSur = coefCalc.getGOSShellProbs(true, gosInelasticLambdaSur);
        if (innerShellLambdaSurrounding > 0) {
          gosInelasticLambdaSur = 1/(1/gosOuterLambdaSur + 1/innerShellLambdaSurrounding);
        }
        else {
          gosInelasticLambdaSur = gosOuterLambdaSur;
        }
        
        lambdaT =  1 / (1/lambdaEl + 1/gosInelasticLambdaSur);
        }
      //  lambdaT =  1 / (1/lambdaEl + 1/FSELambda);
        
    //    lambdaT = 1 / (1/lambdaEl + 1/FSELambda + 1/plasmonLambda);
   //     lambdaT =  1 / (1/lambdaEl + 1/innerShellLambda + 1/FSELambda);
   //     lambdaT =  1 / (1/lambdaEl);
      
        s = -lambdaT*Math.log(Math.random());

   //     Pplasmon = plasmonLambda/ (FSELambda + plasmonLambda); 
   //     PinnerShell = FSELambda/(innerShellLambda + FSELambda);
        
        ionisationProbsSurrounding = coefCalc.getAllShellProbs(true);
        elasticProbsSurrounding = coefCalc.getElasticProbs(true);
        if (GOS == true) {
        if (innerShellLambdaSurrounding > 0) {
          Pinner = gosInelasticLambdaSur / innerShellLambdaSurrounding;
        }
        }
        //need to check if it crosses before it reaches s again and if it does update to this point
        double intersectionDistance = 1000*getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm);
        double[] intersectionPoint = getIntersectionPoint(intersectionDistance, previousX, previousY, previousZ, xNorm, yNorm, zNorm);
        boolean pointInCrystal = isIntersectionInCrystal(intersectionPoint);
        if (intersectionDistance < s && pointInCrystal == true) { //then need to change region here and reset stuff
          surrounding = false;
          entered = true;
          
         // electronEnergy -= intersectionDistance * stoppingPower; //removed for GOS model
          
          
          double innerShellLambda = coefCalc.betheIonisationxSection(electronEnergy, false);
          if (GOS == true) {
          gosInelasticLambda = coefCalc.getGOSInel(false, electronEnergy);
          gosOuterLambda = coefCalc.getGOSOuterLambda(false);
          gosOuterIonisationProbs = coefCalc.getGOSOuterShellProbs(false, gosOuterLambda);
          gosIonisationProbs = coefCalc.getGOSShellProbs(false, gosInelasticLambda);
          if (innerShellLambda > 0) {
            gosInelasticLambda = 1/(1/gosOuterLambda + 1/innerShellLambda);
          }
          else {
            gosInelasticLambda = gosOuterLambda;
          }
          }
          previousX = intersectionPoint[0]*1000;
          previousY = intersectionPoint[1]*1000;
          previousZ = intersectionPoint[2]*1000;
          stoppingPower = coefCalc.getStoppingPower(electronEnergy, false);
          FSExSection = getFSEXSection(electronEnergy);
          FSELambda = coefCalc.getFSELambda(FSExSection, false);
          lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, false);
          lambdaInel = coefCalc.getElectronInelasticMFPL(electronEnergy, false);
          lambdaT = 1 / (1/lambdaEl + 1/lambdaInel);
          //lambdaT =  1 / (1/lambdaEl + 1/FSELambda);
          if (GOS == true) {
          lambdaT = 1 / (1/lambdaEl + 1/gosInelasticLambda);
          }
          s = -lambdaT*Math.log(Math.random());
          if (GOS == true) {
          if (innerShellLambda > 0) {
            Pinner = gosInelasticLambda / innerShellLambda;
          }
          }
          ionisationProbs = coefCalc.getAllShellProbs(false);
          elasticProbs = coefCalc.getElasticProbs(false);
          
        }
        
        PEL = lambdaT / lambdaEl;
        Pinel = 1 - (lambdaT / lambdaEl);
        Pfse = lambdaInel / FSELambda;

        //update to new position
        xn = previousX + s * xNorm;
        yn = previousY + s * yNorm;
        zn = previousZ + s * zNorm;
        
        //need to also check whether to track the primary electron anymore or give up on it 

      }
      else {
        exited = true;
      }
    }
  }
  if (electronEnergy < 0.05) {
    exited = true;
    if (isMicrocrystalAt(previousX, previousY, previousZ) == true) {
      MonteCarloDose += electronEnergy;
     // lowEnDose += electronEnergy;
    }
  }
  }
  if (timesScattered == 1) {
    MonteCarloSingleElasticCount += 1;
  }
  
  //check if this was a productive electron
  if (elasticCount == 1 && backscattered == false && inelastic == false && surrounding == false && entered == false) {
    MonteCarloProductive += 1;
  }
  if (scatteredMacro == 1 && backscattered == false && inelastic == false && surrounding == false && entered == false && scatteredSol == 0) {
    MonteCarloProductiveSolvent += 1;
  }
  
  if (elasticCount != 1 && inelastic == false) {
    MonteCarloUnproductive += 1;
  }
  
  if (elasticCount > 1 && inelastic == false) {
    MonteCarloUnproductiveMicroED += 1;
  }
  if ((scatteredMacro > 1 && inelastic == false) || (scatteredSol > 0 && inelastic == false)) {
    MonteCarloUnproductiveMicroEDSolvent += 1;
  }
  
  
  //if it has not elastically scattered but has inelastically scattered at least once, 
  //I want to get it's energy loss and the angle to the initial vector
  if (elasticCount < 1 && inelastic == true) {
    //get the angle to the initial vector
    double[] currentVector = {xNorm, yNorm, zNorm};
    double[] startVector = {0, 0, 1};
    double angle = 1000*Math.acos(Vector.dotProduct(startVector, currentVector)); //mrad
    double energyLostAtEnd = beam.getPhotonEnergy() - electronEnergy;
    inelEnAngle.put(energyLostAtEnd, angle);
    inelAngles.add(angle);
  }
  //I'm also going to put the ones that didn't scatter at all in the array now as this might
  //be more representative
  if (elasticCount < 1 && inelastic == false) {
    //get the angle to the initial vector
    //double[] currentVector = {xNorm, yNorm, zNorm};
    //double[] startVector = {0, 0, 1};
    //double angle = 1000*Math.acos(Vector.dotProduct(startVector, currentVector)); //mrad
    //double energyLostAtEnd = beam.getPhotonEnergy() - electronEnergy;
    inelEnAngle.put(0.0, 0.0);
    inelAngles.add(0.0);
  }
  
  
  } //end looping through electrons
  
  //Will need to do something about exiting the correct plane here

  //Will also need to add in inel scattering here for productive (and then FSE stuff)
   System.out.print(MonteCarloElectronsExited);
   totBreakdownEnergy = totFSEEnergy + totShellEnergy + totPlasmonEnergy;
   FSEsum = FSEsum/FSEcount;
   double fraction = newMonteCarloFSEEscape/MonteCarloDose;
   double fraction2 = newMonteCarloFSEEscape/totFSEEnergy;
   //MonteCarloDose -= MonteCarloFSEEscape;
   //MonteCarloDose -= newMonteCarloFSEEscape;
   //MonteCarloDose -= MonteCarloAugerEscape;
   //MonteCarloDose -= MonteCarloFlEscape;
   //MonteCarloDose += MonteCarloAugerEntry;
  // MonteCarloDose += MonteCarloFSEEntry;
   
   
}

private int findIfElementIonised(Element e, Map<Element, double[]> ionisationProbs, double elementRND) {
  double[] elementShellProbs = ionisationProbs.get(e);
  int shell = -1;
  for (int k = 0; k < elementShellProbs.length; k++) {
    if (elementShellProbs[k] > elementRND) { //Then this element is the one that was ionised
      shell = k;
      break;
    }
  }
  return shell;
}

private double[] processMonteCarloDose(Beam beam, CoefCalc coefCalc) {
  double exposedArea = getExposedArea(beam);
  
  double imageArea = beam.getImageX() * beam.getImageY(); //um^2
  double imageVolume = imageArea  * (sampleThickness/1000) * 1E-15; //dm^3
  
  double exposedVolume = exposedArea  * (sampleThickness/1000) * 1E-15; //exposed volume in dm^3
  if (exposedVolume > crystalVolume) {
    exposedVolume = crystalVolume;
  }
  double exposure = beam.getExposure();
  double electronNumber = exposure * (exposedArea * 1E08);
  //change electron number now simulating whole area of beam
  double beamArea = beam.getBeamArea();
  electronNumber = exposure * (beamArea * 1E08);
  
  //do the elastic stuff
  MonteCarloTotElasticCount = MonteCarloTotElasticCount * (electronNumber / numSimulatedElectrons);
  MonteCarloSingleElasticCount = MonteCarloSingleElasticCount * (electronNumber / numSimulatedElectrons);
  MonteCarloProductiveImage = ((electronNumber/ numSimulatedElectrons) * (imageArea/beamArea)) * MonteCarloProductive;
  MonteCarloProductive = MonteCarloProductive * (electronNumber/ numSimulatedElectrons);
  MonteCarloUnproductive = MonteCarloUnproductive * (electronNumber/ numSimulatedElectrons);
  MonteCarloUnproductiveMicroED = MonteCarloUnproductiveMicroED * (electronNumber/ numSimulatedElectrons);
  // solvent
  MonteCarloProductiveSolvent = MonteCarloProductiveSolvent * (electronNumber/ numSimulatedElectrons);
  MonteCarloUnproductiveMicroEDSolvent = MonteCarloUnproductiveMicroEDSolvent * (electronNumber/ numSimulatedElectrons);
  
  
  MonteCarloGOSDose = (MonteCarloGOSDose * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  MonteCarloDose = (MonteCarloDose * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  MonteCarloFSEEscape = (MonteCarloFSEEscape * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
 // newMonteCarloFSEEscape = (newMonteCarloFSEEscape * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  MonteCarloAugerEscape = (MonteCarloAugerEscape * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  MonteCarloFlEscape = (MonteCarloFlEscape * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  MonteCarloAugerEntry = (MonteCarloAugerEntry * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  
  gosSurroundingElastic = (gosSurroundingElastic * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  gosSurroundingElasticImage = (gosSurroundingElasticImage * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  
 // GOSImageDose = (GOSImageDose * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES; 
  MonteCarloImageDose = (MonteCarloImageDose * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  newMonteCarloFSEEscape = (newMonteCarloFSEEscape * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  MonteCarloFSEEntry = (MonteCarloFSEEntry * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  elasticEnergyTot = (elasticEnergyTot * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  
  totFSEEnergy = (totFSEEnergy * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES;
  
  double gostotImageEn = GOSImageDose + imageEntry - (totImageSecEnergy-imageSecDeposited);
  gostotImageEn = (gostotImageEn * (electronNumber / numSimulatedElectrons)) * Beam.KEVTOJOULES; 
  
  double exposedMass = (((coefCalc.getDensity()*1000) * exposedVolume) / 1000);  //in Kg 
  double dose = (MonteCarloDose/exposedMass) / 1E06; //dose in MGy 
  double gosDose = (MonteCarloGOSDose/exposedMass) / 1E06; //dose in MGy 
  double totFSEDose = (totFSEEnergy/exposedMass) / 1E06; //dose in MGy 
  
  avgW = avgW / Wcount;
  avgShell = avgShell / Wcount;
  
  avgDist = avgDist / numSimulatedElectrons;
  
  double imageMass = (((coefCalc.getDensity()*1000) * imageVolume) / 1000);  //in Kg 
  double imageDose = (MonteCarloImageDose/imageMass) / 1E06; //dose in MGy 
  double gosImageDose = (gostotImageEn/imageMass) / 1E06; //dose in MGy 
  
  double doseExited = (newMonteCarloFSEEscape/exposedMass) / 1E06; //dose in MGy 
  double augerExited = (MonteCarloAugerEscape/exposedMass) / 1E06; //dose in MGy 
  double flExited = (MonteCarloFlEscape/exposedMass) / 1E06; //dose in MGy 
  double augerEntry = (MonteCarloAugerEntry/exposedMass) / 1E06; //dose in MGy 
  double doseEntered = (MonteCarloFSEEntry/exposedMass) / 1E06; //dose in MGy 
  
  double totEntered = doseEntered + augerEntry;
  double totExited = doseExited + augerExited + flExited;
  
  doseOutput = dose;
  gosDoseOutput = gosDose - totExited;
  
  //charge stuff
  MonteCarloCharge = (MonteCarloElectronsExited - MonteCarloElectronsEntered) * (electronNumber / numSimulatedElectrons) * Beam.ELEMENTARYCHARGE; //need to add in Auger to these
  MonteCarloChargeDensity = MonteCarloCharge / (exposedVolume/1000); // C/m^3
  
 // double avgInel = (inelAngleSum/inelAngleCount) * 1000; //mrad
  Collections.sort(inelAngles);
 // double medianAngle = inelAngles.get((int)(inelAngleCount/2));
  
  //process voxel dose
  //for every voxel, convert keV to dose and average this
  /*
  int count = 0;
  double sumDose = 0;
  for (int i = 0; i < maxX; i++) {
    for (int j = 0; j < maxY; j++) {
      for (int k = 0; k < maxZ; k++) {
        voxelDose[i][j][k] = convertVoxEnergyToDose(voxelDose[i][j][k], beam, coefCalc);
        sumDose += voxelDose[i][j][k];
        count += 1;
      }
    }
  }
  avgVoxelDose = sumDose / count;
  
  //scale
  double scaleFactor = dose / avgVoxelDose;
  for (int i = 0; i < maxX; i++) {
    for (int j = 0; j < maxY; j++) {
      for (int k = 0; k < maxZ; k++) {
        voxelDose[i][j][k] *= scaleFactor;
      }
    }
  }
  //write a csv file
  try {
    writeDoseCSV("outputVoxDose.CSV");
  } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  */
  
  
  //Write an angle csv
  
  try {
    writeAngleCSV(inelEnAngle, beam.getPhotonEnergy(), inelAngles);
  } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  
  
  //process region dose
  int count = 0;
  double sumDose = 0;
  for (int i = 0; i < NUM_REGIONS; i++) {
    regionDose[i] = convertRegionEnergyToDose(regionDose[i], i, beam, coefCalc);
    sumDose += regionDose[i];
    count += 1;
  }
  avgRegionDose = sumDose / count;
  double scaleFactor = dose / avgRegionDose;
  for (int i = 0; i < NUM_REGIONS; i++) {
        regionDose[i] *= scaleFactor;

  }
  
  double[] doses = {dose, imageDose};
  return doses;
}

public double getExposedArea(Beam beam) {
  double exposedArea = 0;
  if (beam.getIsCircular() == false) {
    exposedArea = (getExposedX(beam) * getExposedY(beam)); //um^2
  }
  else {
    exposedArea = Math.PI * ((getExposedX(beam)/2) * (getExposedY(beam)/2)); //um^2
  }
  
  if (exposedArea > crystalSurfaceArea) {
    exposedArea = crystalSurfaceArea;
  }
  return exposedArea;
}

private double getFSEXSection(double electronEnergy) {
  double elementaryCharge = 4.80320425E-10; //units = esu = g^0.5 cm^1.5 s^-1
  double m = 9.10938356E-28; // in g
  double c = 29979245800.0;  //in cm
  //classical for now
  //find the electron velocity in cm/s
//  double vsquared = ((electronEnergy*Beam.KEVTOJOULES * 2) / (m/1000)) * 10000; //(cm/s)^2
  
  double csquared = Math.pow(c/100, 2);
  double Vo = electronEnergy * Beam.KEVTOJOULES;
  double betaSquared = 1- Math.pow((m/1000)*csquared/(Vo + (m/1000)*csquared), 2);
  double vsquared = (betaSquared * csquared)*10000;
  
  //the v sqaured being relativistic or not is what makes the difference
  
  //integrate equation - currently this isn't right... Do it numerically
//  double constant = (Math.PI * Math.pow(Beam.ELEMENTARYCHARGE, 4)) / Math.pow(electronEnergy*Beam.KEVTOJOULES, 2);  //maybe go with Murata
//  double constant = (6.21E-20 / Math.pow(electronEnergy, 2)); // *1E14;  //cm^2/electron? ???
//  double constant = (4* Math.PI * Math.pow(elementaryCharge, 4)) / (Math.pow(m*vsquared, 2));
  double constant = (2* Math.PI * Math.pow(elementaryCharge, 4)) / (m*vsquared * (Vo*1000*10000));
  
  //So the equation in Murata is cross section per electron (i assume cm^2/electron). So need to
  //1) Work out electrons per unit volume
  //2) multiply to get cross section in cm^-1 and convert to nm^-1
  
 // double crossSection = 0; //so this is in cm^2
  
  //equ integrates t 1/1-x -1/x + C
  
// double  crossSection = (1/(1-0.5) - 1/0.5) - ((1/(1-0.001) - 1/0.001));
// crossSection *= constant;  // I think this is now in cm^2 per electron
  
/*
  double crossSection = 0;
  for (double i = 1.1; i <= 500; i+=0.1) {
    double omega = i /1000;
    double omegaMinusOne = (i-0.1) / 1000;
    double width = (i /1000) - ((i-0.1)/1000);
    double height = ((constant * ((1/Math.pow(omega, 2)) + (1/Math.pow(1-omega, 2))))
                    + (constant * ((1/Math.pow(omegaMinusOne, 2)) + (1/Math.pow(1-omegaMinusOne, 2))))) 
                    / 2;
    crossSection += width * height;
  }
 */
 
 
 //try the relativistic cross section from Murata et al - with times
  /*
  double restMassEnergy = 511; //keV
  double tau = electronEnergy/restMassEnergy;
  double crossSection = 0;
  for (double i = 1.1; i <= 500; i+=0.1) {
    double omega = i /1000;
    double omegaMinusOne = (i-0.1) / 1000;
    double width = (i /1000) - ((i-0.1)/1000);
    double height = ((constant * ((1/Math.pow(omega, 2)) + (1/Math.pow(1-omega, 2)) + Math.pow(tau/(tau+1), 2) 
                  - ((2*tau+1)/Math.pow(tau+1, 2)) * (1/(omega*(1-omega)))))
        
                    + (constant * ((1/Math.pow(omegaMinusOne, 2)) + (1/Math.pow(1-omegaMinusOne, 2)) + Math.pow(tau/(tau+1), 2) 
                    - ((2*tau+1)/Math.pow(tau+1, 2)) * (1/(omegaMinusOne*(1-omegaMinusOne)))))) 
                    / 2;
    crossSection += width * height;
  }
  */
  
  //numerical integral of this
  double energyCutOff;
  energyCutOff = (energyCUTOFF/1000.0)/electronEnergy; //corresponds to a 14eV cut off, the hydrogen K shell energy
  
  double restMassEnergy = 511; //keV
  double tau = electronEnergy/restMassEnergy;
  double crossSection = (((2*tau+1)/Math.pow(tau+1, 2))*(Math.log((1/0.5)-1)) + Math.pow(tau/(tau+1), 2) - (1/0.5) - (1/(0.5-1))) -
                        (((2*tau+1)/Math.pow(tau+1, 2))*(Math.log((1/energyCutOff)-1)) + Math.pow(tau/(tau+1), 2) - (1/energyCutOff) - (1/(energyCutOff-1))); 
                        
  crossSection*= constant;

 
 //Book classical
  //constant is same as above
  /*
  double T = electronEnergy * Beam.KEVTOJOULES;
  double crossSection = 0;
  for (double i = 2*(T/1000); i <= T/2; i+= T/1000) {
    double Q = i;
    double QMinusOne = i - (T/1000);
    double width = T/1000;
    double height = ((constant * ((1/Math.pow(Q, 2)) * Math.pow(T/(T-Q), 2) * (1-2*(Q/T) + 2*Math.pow(Q/T, 2))))
                    + (constant * ((1/Math.pow(QMinusOne, 2)) * Math.pow(T/(T-QMinusOne), 2) * (1-2*(QMinusOne/T) + 2*Math.pow(QMinusOne/T, 2)))))
                    / 2;
    crossSection += width * height;
  }
  */
/*
  //book relativistic
  //constant is same as above
  double T = electronEnergy * Beam.KEVTOJOULES;
  double m = 9.10938356E-31; // in Kg
  double c = 299792458;
  double csquared = c*c;
  double crossSection = 0;
  for (double i = 2*(T/1000); i <= T/2; i+= T/1000) {
    double Q = i;
    double QMinusOne = i - (T/1000);
    double width = T/1000;
    double height = ((constant * ((1/Math.pow(Q, 2)) * Math.pow(T/(T-Q), 2) * 
                    (1-(3-Math.pow(T/(T+m*csquared), 2))*(Q/T)*(1-(Q/T))+(Math.pow(Q/(T+m*csquared), 2) * Math.pow(1-(Q/T), 2)))))
        
                    + (constant * ((1/Math.pow(QMinusOne, 2)) * Math.pow(T/(T-QMinusOne), 2) * 
                        (1-(3-Math.pow(T/(T+m*csquared), 2))*(QMinusOne/T)*(1-(QMinusOne/T))+(Math.pow(QMinusOne/(T+m*csquared), 2) * Math.pow(1-(QMinusOne/T), 2))))))
                    / 2;
    crossSection += width * height;
  }
  */
  //book non-rel
  
  //book very rel

 
  return crossSection; //cm^2/atom //nm^2 per atom??? //Really not sure about units here
}

private double getRutherfordScreeningElement(ElementEM e, double electronEnergy) {
  double alpha = 0;
  alpha = 3.4E-3 * (Math.pow(e.getAtomicNumber(), 0.67)/electronEnergy);
  return alpha;
}

private int getNumberOfBins(double s) {
  double pixelDivisionSize = (1/crystalPixPerUMEM)/2;
  int pixelDivisionNumber =  (int) StrictMath.round(s/pixelDivisionSize);
  return Math.max(pixelDivisionNumber, BIN_DIVISION);
}

private void inelasticFSEProduced(double electronEnergy) {

  //next stage is to track the FSE
  // 1) Use vector stuff to draw vector to point and determine energy by stopping power
          // make sure that I don't go to a negative energy
  // 2) Track the FSE by Monte Carlo
  
  
  
  
  //Use two methods to incorporate this
  // Method 1 - Only update the primary electron energy using the stopping power and subtract escape energy from the end
  //Method 2 - try incorporating the direct energy losses from this
              // - would expect these energy losses to be much lower but need to quantify

}

private double getShellBindingEnergy(Element collidedElement, int collidedShell) {
  double shellBindingEnergy = 0;
  switch (collidedShell) {
    case 0: shellBindingEnergy = collidedElement.getKEdge();
            break;
    case 1: shellBindingEnergy = collidedElement.getL1Edge();
            break;
    case 2: shellBindingEnergy = collidedElement.getL2Edge();
            break;
    case 3: shellBindingEnergy = collidedElement.getL3Edge();
            break;
    case 4: shellBindingEnergy = collidedElement.getM1Edge();
            break;
    case 5: shellBindingEnergy = collidedElement.getM2Edge();
            break;
    case 6: shellBindingEnergy = collidedElement.getM3Edge();
            break;
    case 7: shellBindingEnergy = collidedElement.getM4Edge();
            break;
    case 8: shellBindingEnergy = collidedElement.getM5Edge();
            break;
  }
  return shellBindingEnergy;
}

private double doPrimaryInelastic(CoefCalc coefCalc, double previousX, double previousY, double previousZ, 
                                  double electronEnergy, Map<Element, double[]> ionisationProbs, boolean surrounding, Beam beam, int i,
                                  double previousTheta, double previousPhi, double Pinner,  Map<Element, Double> gosOuterIonisationProbs, boolean entered,
                                  double xNorm, double yNorm, double zNorm) {
 //I'm now going to change thi for the GOS model
  
  double theta = 0;
  double W = 0;
  boolean innerShell = false;
  double shellBindingEnergy = 0;
  Element collidedElement = null;
  int collidedShell = -1;
  //did this come from an inner shell?
  double RNDinnerShell = Math.random();
//    if (RNDinnerShell < PinnerShell) {  //they're all going to be coming from inner shells now
    //Then this secondary electron came from an inner shell
   // innerShell = true;
    //determine which elemental shell it came from
    double elementRND = Math.random();
    boolean plasmon = false;
    if (GOS == true) {
    if (RNDinnerShell < Pinner) {
      for (Element e : ionisationProbs.keySet()) {
        collidedShell = findIfElementIonised(e, ionisationProbs, elementRND);
        if (collidedShell >= 0) {
          collidedElement = e;
          break;
        }
      }
    }
    else { //outer shell
      for (Element e : gosOuterIonisationProbs.keySet()) {
        int[] electrons = coefCalc.getNumValenceElectronsSubshells(e);
        int numInnerShells = electrons[1];
        collidedShell = numInnerShells;
        if (findIfOuterShellIonised(e, gosOuterIonisationProbs, elementRND) == true){
          collidedElement = e;
          break;
        }
      } 
    }
    
    if (collidedShell == -1) {
      //then this is a collision with the conduction band 
      plasmon = true;
    }
    else {
      //shellBindingEnergy = getShellBindingEnergyGOS(collidedElement, collidedShell);
      shellBindingEnergy = getShellBindingEnergy(collidedElement, collidedShell);
    }
    //get the type of collision
    int type = 0;
    if (plasmon == false) {
      type = getGOSInelasticType(coefCalc.getGOSVariable(surrounding).get(collidedElement), collidedShell);
    }
    else {
      type = getGOSInelasticTypePlasmon(coefCalc.getPlasmonVariable(surrounding));
    }
  //get energy loss
    double a = coefCalc.returnAdjustment();
    double Uk = 0;
   // double Uk = shellBindingEnergy*1000;
    double Wk = 0, Qak = 0, Q = 0;
    if (plasmon == false) {
      Uk = shellBindingEnergy*1000;
      Wk = coefCalc.getWkMolecule(a, collidedElement, collidedShell, surrounding);
      Qak = getQak(electronEnergy, Wk, Uk);
    }
    else {
      Uk = 0;
      Wk = coefCalc.getWcbAll(surrounding);
      Qak = Wk;
    }
    double Wak = WkToWak(electronEnergy, Wk, Uk); //eV
    double Wdis = 3*Wak - 2*Uk;
    if (type == 0 || type == 1) {
      //then this was a distant collision
       
      //get recoil energy
      if (plasmon == true) {
        W = Wk/1000;
      }
      else {
        W = getEnergyLossDistant(Wdis, Uk)/1000; //in keV  
      }
      Q = coefCalc.getRecoilEnergyDistant(electronEnergy, Wak, Qak); //J
      //get theta (new to add on to previous)
      if (type == 1) {
        //transverse
       // theta = previousTheta;
        theta = 0;
      }
      else {
        //longitudinal
       theta = getGOSPrimaryThetaLong(electronEnergy, Q, Wak, previousTheta);
      }
    }
    else {
      //a close collision
      if (plasmon == true) {
        W = Wk/1000;
      }
      else {
   //     double k = samplek(electronEnergy, Qak);
        double k = samplek(electronEnergy, Uk);
        W = k*(electronEnergy+Uk/1000); //keV
      }
      theta = getGOSPrimaryThetaClose(electronEnergy, W, previousTheta);
    }
    //now I need to send out the secondary electron
    //get an angle and energy then recursively call ofc
    double minTrackEnergy = 0.05;
    double SEPreviousTheta = previousTheta;
    double SEPreviousPhi = previousPhi;
    double SEEnergy = W - Uk/1000;
    double SETheta = 0, SEPhi = 0, SExNorm = 0, SEyNorm = 0, SEzNorm = 0;
    if (SEEnergy > 0) {
    //  totalIonisationEvents[doseTime] += 1; //is this right??? need to sort this as well!!!
      //don't do this above if calling recursively as done at the top
      if (SEEnergy > minTrackEnergy) {
     //   gosElectronDose[doseTime] += Uk/1000;
        //get theta
        if (type == 0 || type == 1) { //distant
          SETheta = secondaryThetaDistant(electronEnergy, Wak, Q, previousTheta);
        }
        else { //close
          SETheta = secondaryThetaClose(electronEnergy, W, SEPreviousTheta);
        }
        /*
        //get phi
        SEPhi = 2 * Math.PI * Math.random();
        SEPhi = SEPreviousPhi + SEPhi;
        if (SEPhi >= (2 * Math.PI)) {
          SEPhi -= 2*Math.PI;
        }
        //now get normals
        SExNorm = Math.sin(SETheta) * Math.cos(SEPhi);
        SEyNorm = Math.sin(SETheta) * Math.sin(SEPhi);
        
        SEzNorm = Math.cos(SETheta);
        */
        double scatterPhi = getScatteringPhi();
        double[] newDirectionVector = getNewDirectionVector(xNorm, yNorm, zNorm, SETheta, scatterPhi);
        SExNorm = newDirectionVector[0];
        SEyNorm = newDirectionVector[1];
        SEzNorm = newDirectionVector[2];
        SETheta = Math.acos(SEzNorm);
        SEPhi = Math.acos(SExNorm / Math.sin(SETheta));
        //send it out with the correct timestamp
        if (surrounding == false) {
          MonteCarloGOSDose += W;
          if (entered == true) {
            gosSurroundingElastic += W;
          }
          if (Math.abs(previousX)/1000 <= beam.getImageX()/2 && Math.abs(previousY)/1000 <= beam.getImageY()/2) { //then in imaged region
            GOSImageDose += W;
            if (entered == true) {
              gosSurroundingElasticImage += W;
            }
          }
          avgW += W;
          Wcount += 1;
          avgShell += shellBindingEnergy;
        }
        if (Math.abs(previousX)/1000 <= beam.getImageX()/2 && Math.abs(previousY)/1000 <= beam.getImageY()/2) { //then in imaged region
        totImageSecEnergy += SEEnergy;
        }
        MonteCarloSecondaryElastic(coefCalc, SEEnergy, previousX, previousY, previousZ, SETheta, SEPhi, surrounding, beam, i);
          
      }
      else { //too low energy to track - work out what exactly I'm doing with dose! - need an SP way and a W way
        if (surrounding == false) {
          MonteCarloGOSDose += W;
          if (entered == true) {
            gosSurroundingElastic += W;
          }
          if (Math.abs(previousX)/1000 <= beam.getImageX()/2 && Math.abs(previousY)/1000 <= beam.getImageY()/2) { //then in imaged region
            GOSImageDose += W;
            if (entered == true) {
              gosSurroundingElasticImage += W;
            }
          }
          avgW += W;
          Wcount += 1;
          avgShell += shellBindingEnergy;
        }
        if (collidedElement.getAtomicNumber() > 2 && collidedShell < 4) { //only do fl or Auger if K or L shell and not H or He K shells
          FlAugerMonteCarlo(collidedElement, previousX, previousY, previousZ, collidedShell, coefCalc, surrounding, beam);
        }
      }
    //  electronEnergy -= W; 
    //  lossSinceLastUpdate += W;
      //produce Auger electon should only be if it is from an inner shell of an element more than 2
    //  produceAugerElectron(coefCalc, timeStamp, collidedShell, collidedElement, xn, yn, zn, surrounding);
      
      //need to do an Auger here in some way

    }
    
    } //end if GOS == true
    
    
    
    /*
   // shellBindingEnergy = getShellBindingEnergy(collidedElement, collidedShell);
    double FSEtheta = 0, FSEphi = 0, FSEpreviousTheta = 0, FSEpreviousPhi = 0, FSExNorm = 0, FSEyNorm = 0, FSEzNorm = 0;
    FSEpreviousTheta = previousTheta;
    FSEpreviousPhi = previousPhi;
    //firstly calculate the FSE energy
    double epsilon = getFSEEnergy(electronEnergy, shellBindingEnergy);
    double FSEEnergy = epsilon * electronEnergy;// - shellBindingEnergy;
    if (FSEEnergy > 0) { //so only if it happened
      totFSEEnergy += FSEEnergy; //tot energy of all  
      totShellEnergy += shellBindingEnergy;
    }
    */
    /*
  if (collidedElement.getAtomicNumber() > 2 && collidedShell < 4 && FSEEnergy > 0) { //only do fl or Auger if K or L shell and not H or He K shells
    FlAugerMonteCarlo(collidedElement, previousX, previousY, previousZ, collidedShell, coefCalc, surrounding, beam);
  }
  */
    
 //   theta = 0;
//    }
//    else {
  //Track the secondary electron
  //I'm going to take t as the energy of that particular electron
  // This could be two values for the primary, with stopping power or with inel removal
    /*
  double sinSquaredAlpha = 0;
  double sinSquaredGamma = 0;
  double escapeDist = 0, maxDist = 0;
  double minTrackEnergy = 0.05;  //this needs to be tested 
  if (FSEEnergy > minTrackEnergy) { // so I only care about the FSE if it is more than x 
//   if (FSEEnergy > 0) { // so I only care about the FSE if it is more than x 
    // determine the angles of the FSE and the primary electron
    double tPrimary = (electronEnergy-FSEEnergy)/511; //t is in rest mass units. Need to change to stopping power en
    double tFSE = FSEEnergy/511;
    //alpha = angle of primary electron
    sinSquaredAlpha = (2 * epsilon) / (2 + tPrimary - tPrimary*epsilon);
    //gamma - angle of secondary electron
    sinSquaredGamma = 2*(1-epsilon) / (2 + tFSE*epsilon); 
  
  FSEtheta = Math.asin(Math.pow(sinSquaredGamma, 0.5));
  FSEphi = 2 * Math.PI * Math.random();
  
  
  FSEtheta = FSEpreviousTheta + FSEtheta;
  if (FSEtheta >= (2 * Math.PI)) {
    FSEtheta -= 2*Math.PI;
  }
  FSEphi = FSEpreviousPhi + FSEphi;
  if (FSEphi >= (2 * Math.PI)) {
    FSEphi -= 2*Math.PI;
  }
  */
 /*   
  FSExNorm = Math.sin(FSEtheta) * Math.cos(FSEphi);
  FSEyNorm = Math.sin(FSEtheta) * Math.sin(FSEphi);
  FSEzNorm = Math.cos(FSEtheta);
  */
        //now I have a vector need to find where it will intersect point and the distance
  //If doing Monte Carlo of FSE would start tracking it here
//  totFSEEnergy += FSEEnergy;
  
  
  
//  MonteCarloSecondaryElastic(coefCalc, FSEEnergy, previousX, previousY, previousZ, FSEtheta, FSEphi, surrounding, beam, i);
  
  
  /*
  escapeDist = 1000 * getIntersectionDistance(previousX, previousY, previousZ, FSExNorm, FSEyNorm, FSEzNorm); //nm
  double FSEStoppingPower = coefCalc.getStoppingPower(FSEEnergy, false);
  double energyToEdge = FSEStoppingPower * escapeDist;
  maxDist = FSEEnergy / FSEStoppingPower;
  totFSEEnergy += FSEEnergy;
  if (energyToEdge < FSEEnergy){
  //  MonteCarloFSEEscape += FSEEnergy - energyToEdge;
    double test = FSEEnergy - energyToEdge;
    //I'm thinking about multislicing this secondary electron and seeing if that makes a difference!!!
    //I think it will and it should reduce escape so increase dose - and it did
    
    //I should possibly also think about actually Monte Carlo tracking these at these low energies as they
    //will scatter and change direction
    double energyLostStep = 0;
    double newEnergy = FSEEnergy;
    int numSlices = 1;
    for (int j = 0; j < numSlices; j++) { //I will need to play around with the amount of slicing when I am writing up
      energyLostStep = (escapeDist/numSlices) * FSEStoppingPower;
      newEnergy -= energyLostStep;
      FSEStoppingPower = coefCalc.getStoppingPower(newEnergy, false);
    }
    if (newEnergy > 0) {
      MonteCarloFSEEscape += newEnergy;
    }
    
  }
  */
  
 // }
  
  
  /*
  //Track Fl and Auger from this secondary electron
  //Need to think about the distance it is going and stuff like that though before I plough on
  double distTravelled = Math.min(maxDist, escapeDist);
  MonteCarloSecondaryElectronInnerShell(coefCalc, FSEEnergy, previousX, previousY, previousZ, FSExNorm, FSEyNorm, FSEzNorm, distTravelled);
  */
  //track the primary electron 
  /*
  phi = Math.asin(Math.pow(sinSquaredAlpha, 0.5));
  cosPhi = Math.cos(phi);
  */
 // theta = Math.asin(Math.pow(sinSquaredAlpha, 0.5));
  /* not needed as done later
  phi = 2 * Math.PI * Math.random();
  
  
  theta = previousTheta + theta;
  if (theta >= (2 * Math.PI)) {
    theta -= 2*Math.PI;
  }
  phi = previousPhi + phi;
  if (phi >= (2 * Math.PI)) {
    phi -= 2*Math.PI;
  }
  */
//  } 
    if (GOS == true) {
      energyLostGOS = W;
    }
  return theta;
}

private double doPrimaryElastic(double electronEnergy, Map<ElementEM, Double> elasticProbs, boolean surrounding, CoefCalc coefCalc) {
//now start the loop - clean up the first iteration into this later 
  //Determine what element elastically scattered the electron so can choose an alpha correctly

  double elasticElementRND = Math.random();
  ElementEM elasticElement = null;
  for (ElementEM e : elasticProbs.keySet()) {
    if (elasticProbs.get(e) > elasticElementRND) { //Then this element is the one that was ionised
      elasticElement = e;
      break;
    }
  }
  
  //get the angles
  double alpha = getRutherfordScreeningElement(elasticElement, electronEnergy);
  double RND = Math.random();
  /*
  cosPhi = 1 - ((2*alpha * Math.pow(RND, 2))/(1+alpha-RND));
  phi = Math.acos(cosPhi);
  */
  double theta = Math.acos(1 - ((2*alpha * Math.pow(RND, 2))/(1+alpha-RND)));
  
  //get angle by ELSEPA stuff
  
  if ((electronEnergy <= 300) && (electronEnergy >= 0.05)) {
    theta = getPrimaryElasticScatteringAngle(electronEnergy, elasticElement.getAtomicNumber());
  }

  
  double thisTheta = theta;
  
  //Impart elastic knock-on energy???
  if (surrounding == false) {
    double Emax = electronEnergy * (1 + electronEnergy/1022)/(456*elasticElement.getAtomicWeight());
    double m = 9.10938356E-28; //kg
    double u = 1.660539040E-27; //kg/(g/mol)
    double restEnergy = 511;
    Emax = (2/elasticElement.getAtomicWeight())*(m/u)*electronEnergy*(2+electronEnergy/(restEnergy));
    double Ed = 35;
  /*
  double nuclearMass = elasticElement.getAtomicWeight() * 1.660539040E-27;
  double c = 299792458;
  double csquared = c*c;
  double Mcsquared = nuclearMass * csquared;
  double KinE = electronEnergy * Beam.KEVTOJOULES;
  double sintheta = Math.pow(Math.sin(theta/2), 2);
  double energyTransmitted = (Math.pow(KinE, 2) / (Mcsquared))
                              * ((2 * sintheta) / (1 + sintheta *(2*(KinE)/Mcsquared) ));
  energyTransmitted /= Beam.KEVTOJOULES;
  elasticEnergyTot += energyTransmitted;
  */
  
    double sintheta = Math.pow(Math.sin(thisTheta/2), 2);
    double en = (Emax/1000) * sintheta;
    elasticEnergyTot += en;
    if (Emax > Ed) {
      displacementEnergy += en;
    }
    
    // I need to determine of elastic from solvent or macromolecule
    //to do this get number of element in macromolecule and number in solvent and see which one it is from 
    double atomSolvent = coefCalc.getSolventAtoms(elasticElement);
    double atomProtein = coefCalc.getProteinAtoms(elasticElement);
    double proteinProb = atomProtein / (atomProtein+atomSolvent);
    double typeRND = Math.random();
    if (typeRND < proteinProb) {
      scatteredSolvent = false;
    }
    else {
      scatteredSolvent = true;
    }
  }
  else {
    scatteredSolvent = true;
  }
  return theta;
}

private void MonteCarloSecondaryElastic(CoefCalc coefCalc, double FSEenergy, double previousX, double previousY, double previousZ, 
    double FSEtheta, double FSEphi, boolean surrounding, Beam beam, int numSimSoFar) { //Will need to combine this with the inner shell stuff as well - means re-updating the inner shell x sections after I mess with them
  if (surrounding == true) {
    numFSEFromSurr += 1;
  }
  else {
    numFSEFromSample += 1;
  }
  boolean outsideImage = true;
  if (Math.abs(previousX)/1000 <= beam.getImageX()/2 && Math.abs(previousY)/1000 <= beam.getImageY()/2) { //then in imaged region
    outsideImage = false;
  }
  //get the exposure up to this point for charge
  double exposure = beam.getExposure();
  double exposedArea = getExposedArea(beam);
  double electronNumberAtEnd = exposure * (exposedArea * 1E08);
  double simSoFar = (electronNumberAtEnd/numSimulatedElectrons)*simNumber;
  double exposureSoFar = simSoFar / (exposedArea * 1E08);
  if (considerCharge == true) {
    if (exposureSoFar > 0.001) {
      //calc the charge equivalent to 1V
      double r = 0;
      if (beam.getIsCircular()) {
        r = XDimension/2; //nm
      }
      else {
        r = Math.pow(Math.pow(XDimension/2, 2)+Math.pow(YDimension/2, 2), 0.5); //nm
      }
      r = r*1E-9; //m
      double vacuume = 8.854187817E-12; //C.V^-1.m^-1
      double V = 1;
      MonteCarloCharge = 4*Math.PI*vacuume*V*r;
    }
    else {
      MonteCarloCharge = 0;
    }
  }
  else {
    MonteCarloCharge = 0;
  }
    
  
  //find the pixel that the electron is staring in
  int[] startingPixel = convertToPixelCoordinates(previousX, previousY, previousZ);
  int[] thisPixel = startingPixel;
  
  double energyLost = 0;
  double KELostByChargeInSample = 0;
  double theta = FSEtheta;
  double phi = FSEphi;
  double electronEnergy = FSEenergy;
  double startingEnergy = FSEenergy;
  double startingStoppingPower = coefCalc.getStoppingPower(startingEnergy, surrounding);
  double stoppingPower = startingStoppingPower;
  
  //remove the starting dose from the original voxel
  /*
  if (surrounding == false) {
    voxelDose[startingPixel[0]][startingPixel[1]][startingPixel[2]] -= FSEenergy;
  }
  */
  double startingLambda_el = coefCalc.getElectronElasticMFPL(startingEnergy, surrounding);
  Map<ElementEM, Double> elasticProbs = coefCalc.getElasticProbs(surrounding);
  
  double startingInnerShellLambda = coefCalc.betheIonisationxSection(startingEnergy, surrounding);
 // Map<Element, Double> ionisationProbs = coefCalc.getInnerShellProbs(); 
  Map<Element, double[]> ionisationProbs = coefCalc.getAllShellProbs(surrounding); 

  //Just do elastic for now and then incorporate inner shell
//  double lambdaT = startingLambda_el;
  double lambdaT = 0;
  if (startingInnerShellLambda > 0) {
    lambdaT = 1/((1/startingLambda_el) + (1/startingInnerShellLambda));
  }
  else{
    lambdaT = startingLambda_el;   //should probably change the whole thing to the FSe model and just not track the extra electrons
  }
  double testRND = Math.random();
  double s = -lambdaT*Math.log(testRND);
  double Pinel = 1 - (lambdaT / startingLambda_el);
  double xNorm = Math.sin(theta) * Math.cos(phi);
  double yNorm = Math.sin(theta) * Math.sin(phi);
  double zNorm = Math.cos(theta);
  if (Double.isNaN(xNorm)){
    System.out.println("test");
  }
  

  boolean track = true;
  boolean entered = false;
  double entryEnergy = 0;
  //determine if it crosses into the crystal before s
  // I need to check if it's going to intersect and what the distance is
  if (surrounding == true) {
    
    // this could be a potential source of error as it could deflect in so need to test this to make sure it is valid
    double intersectionDistance = 1000*getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm);
    Double distanceObject = Double.valueOf(intersectionDistance);
    if (intersectionDistance < 0 || distanceObject.isNaN()
        || distanceObject.isInfinite()) {
        track = false;
    }
    if (track == true) {
      double[] intersectionPoint = getIntersectionPoint(intersectionDistance, previousX, previousY, previousZ, xNorm, yNorm, zNorm);
      boolean pointInCrystal = isIntersectionInCrystal(intersectionPoint);
      if (intersectionDistance < s && pointInCrystal == true) { //then need to change region here and reset stuff
        surrounding = false;
        entered = true;
        electronEnergy -= intersectionDistance * stoppingPower;
        entryEnergy = electronEnergy;
        previousX = intersectionPoint[0]*1000;
        previousY = intersectionPoint[1]*1000;
        previousZ = intersectionPoint[2]*1000;
        //update the stopping power and stuff
        stoppingPower = coefCalc.getStoppingPower(startingEnergy, surrounding);

        startingLambda_el = coefCalc.getElectronElasticMFPL(startingEnergy, surrounding);
        elasticProbs = coefCalc.getElasticProbs(surrounding);
        startingInnerShellLambda = coefCalc.betheIonisationxSection(startingEnergy, surrounding);
       // Map<Element, Double> ionisationProbs = coefCalc.getInnerShellProbs(); 
        ionisationProbs = coefCalc.getAllShellProbs(surrounding); 
        //Just do elastic for now and then incorporate inner shell
  //      double lambdaT = startingLambda_el;
        if (startingInnerShellLambda > 0) {
          lambdaT = 1/((1/startingLambda_el) + (1/startingInnerShellLambda));
        }
        else{
          lambdaT = startingLambda_el;   //should probably change the whole thing to the FSe model and just not track the extra electrons
        }
        testRND = Math.random();
        s = -lambdaT*Math.log(testRND);
        Pinel = 1 - (lambdaT / startingLambda_el);
      }
    }
  }
  
  //Coulomb's law stuff will need to happen before here 
  double electronNumber = beam.getExposure() * (beam.getBeamArea()*1E8);
  
  double[] electronPosition = {previousX, previousY, previousZ};
  double[] chargePosition = {0, 0, 0};
  double csquared = Math.pow(c, 2);
  double gamma = 0, newKineticEnergy = 0, kineticEnergyLossByCharge = 0, newVelocityMagnitude = 0;
  double[] newVelocityVector = new double[3];
  double[] newVelocityUnitVector = new double[3];
 // MonteCarloCharge = (MonteCarloElectronsExited - MonteCarloElectronsEntered) * (electronNumber / numSimulatedElectrons)  * ((double)numSimSoFar/numSimulatedElectrons) * Beam.ELEMENTARYCHARGE;
  if (considerCharge == true) {
  //  MonteCarloCharge = 0;
  
  
  if (MonteCarloCharge != 0) {
    newVelocityVector = adjustVelocityVectorByCharge(electronPosition, chargePosition, s, electronEnergy, xNorm, yNorm, zNorm, coefCalc);
    newVelocityMagnitude = Vector.vectorMagnitude(newVelocityVector) /1E9; //m/s
    newVelocityUnitVector = Vector.normaliseVector(newVelocityVector);
    //update new xNorm. yNorm, zNorm
    xNorm = newVelocityUnitVector[0];
    yNorm = newVelocityUnitVector[1];
    zNorm = newVelocityUnitVector[2];
    //update theta and phi
    theta = Math.acos(zNorm);
    phi = Math.asin(yNorm/Math.sin(theta));
    
    if (Double.isNaN(xNorm)){
      System.out.println("test");
    }
  
    //work out the new kinetic energy
  
    gamma = 1 / Math.pow(1 - (Math.pow(newVelocityMagnitude, 2)/Math.pow(c, 2)), 0.5);
    //so if the electron is really close to the charge, the velocity becomes more than the speed of light and this break...

    
    newKineticEnergy = (gamma - 1) * m * Math.pow(c, 2)/Beam.KEVTOJOULES; // in keV
    kineticEnergyLossByCharge = electronEnergy - newKineticEnergy; //in keV
  }
  
  }
  double[] newTotalVelocityVector = new double[3];
  if (considerCharge == true) {
  /*
  //now do the voxel charge here
  
  for (int i = 0; i < maxX; i++) {
    for (int j = 0; j < maxY; j++) {
      for (int k = 0; k < maxZ; k++) {
        if (voxelCharge[i][j][k] != 0) {
          int[] voxCoord = {i, j, k};
          if (thisPixel != voxCoord) {
            //convert pixel coord to a cartesian coord
            chargePosition = convertToCartesianCoordinates(i, j, k);
            newVelocityVector = adjustVelocityVectorByCharge(electronPosition, chargePosition, s, electronEnergy, xNorm, yNorm, zNorm, coefCalc);
            for (int m = 0; m < 3; m++) {
              newTotalVelocityVector[m] += newVelocityVector[m]; //is this even right??? don't I want to some charge not velocity vectors
            }
          }
        }
      }
    }
  }
  newVelocityMagnitude = Vector.vectorMagnitude(newVelocityVector) /1E9; //m/s
  if (newVelocityMagnitude > 0) { //so there is a charge pulling
    newVelocityUnitVector = Vector.normaliseVector(newVelocityVector);
    //update new xNorm. yNorm, zNorm
    xNorm = newVelocityUnitVector[0];
    yNorm = newVelocityUnitVector[1];
    zNorm = newVelocityUnitVector[2];
    //update theta and phi
    theta = Math.acos(zNorm);
    phi = Math.asin(yNorm/Math.sin(theta));
    //work out the new kinetic energy
    gamma = 1 / Math.pow(1 - (Math.pow(newVelocityMagnitude, 2)/Math.pow(c, 2)), 0.5);
//   newKineticEnergy = ((gamma - 1) * m * Math.pow(c, 2)); // in Joules
    newKineticEnergy = ((gamma - 1) * m * Math.pow(c, 2))/Beam.KEVTOJOULES; // in keV
 //   kineticEnergyLossByCharge = ((electronEnergy*Beam.KEVTOJOULES) - newKineticEnergy)/Beam.KEVTOJOULES; //in keV
    kineticEnergyLossByCharge = electronEnergy - newKineticEnergy;
  
     KELostByChargeInSample += kineticEnergyLossByCharge;
   
  }
  */
  }
  double xn = previousX + s * xNorm;
  double yn = previousY + s * yNorm;
  double zn = previousZ + s * zNorm;
  boolean exited = false, scattered = false;
  double previousTheta = 0, previousPhi = 0;
  
  if (electronEnergy < 0.05) {
    exited = true;
    lowEnDose -= electronEnergy;
    if (Math.abs(previousX)/1000 <= beam.getImageX()/2 && Math.abs(previousY)/1000 <= beam.getImageY()/2) { //then in imaged region
      if (outsideImage == false) {
        imageSecDeposited += electronEnergy;
      }
      else {
        imageEntry += electronEnergy;
      }
    }
  }
  while (exited == false) {
    if (isMicrocrystalAt(xn, yn, zn) == true) {
      //reset
      if (surrounding == true) {
        entered = true;
        entryEnergy = electronEnergy;
        KELostByChargeInSample=0;
      }
      surrounding = false;
      scattered = true;
      
      //update dose and energy and stoppingPower
      energyLost = s * stoppingPower;
      //split the dose up into voxels
 //     addDoseToVoxels(s, xNorm, yNorm, zNorm, previousX, previousY, previousZ, energyLost, beam, coefCalc);
      addDoseToRegion(s, xNorm, yNorm, zNorm, previousX, previousY, previousZ, energyLost);
      addDoseToImagedRegion(s, xNorm, yNorm, zNorm, previousX, previousY, previousZ, energyLost, beam, outsideImage);
      
      //energy lost from charge - charge energy not appropriate to count towards dose or get negative dose
      energyLost += kineticEnergyLossByCharge;

      //update position and angle
      previousTheta = theta;
      previousPhi = phi;
      previousX = xn;
      previousY = yn;
      previousZ = zn;
      
      
    double RNDscatter = Math.random();
    if (RNDscatter < Pinel) { // If the scatter was an inner shell ionisation 
      double shellBindingEnergy = 0;
      Element collidedElement = null;
      int collidedShell = -1;
      
      double elementRND = Math.random();
      for (Element e : ionisationProbs.keySet()) {
        collidedShell = findIfElementIonised(e, ionisationProbs, elementRND);
        if (collidedShell >= 0) {
          collidedElement = e;
          break;
        }
      }
      
      shellBindingEnergy = getShellBindingEnergy(collidedElement, collidedShell);
      if (collidedElement.getAtomicNumber() > 2 && collidedShell < 4) {
        
      double shellFluorescenceYield = 0;
      double flauEnergy = 0;
      if (collidedShell == 0) {
        shellFluorescenceYield = collidedElement.getKShellFluorescenceYield();
        flauEnergy = collidedElement.getKFluorescenceAverage();
      }
      else if (collidedShell == 1) {
        shellFluorescenceYield = collidedElement.getL1ShellFluorescenceYield();
        flauEnergy = collidedElement.getLFluorescenceAverage();
      }
      else if (collidedShell == 2) {
        shellFluorescenceYield = collidedElement.getL2ShellFluorescenceYield();
        flauEnergy = collidedElement.getLFluorescenceAverage();
      }
      else if (collidedShell == 3){
        shellFluorescenceYield = collidedElement.getL3ShellFluorescenceYield();
        flauEnergy = collidedElement.getLFluorescenceAverage();
      }
      if (electronEnergy > shellBindingEnergy && flauEnergy > 0 && !Double.isNaN(flauEnergy)) { //only a collision if it is physically possible
        //Do Fl or Auger
        
        //remove the flauenergy from this pixel
        int[] getPixel = convertToPixelCoordinates(xn, yn, zn);
     //   voxelDose[getPixel[0]][getPixel[1]][getPixel[2]] -= flauEnergy;
        
      //RND for FL or Auger given it was that element
        double fluoresenceYieldKRND = Math.random();
    //    double KshellFluorescenceYield = collidedElement.getKShellFluorescenceYield();
        if (fluoresenceYieldKRND <= shellFluorescenceYield) { 
        //then it's fluorescence
          // get the absorption coefficient of the crystal
      //    double flEnergy = collidedElement.getKFluorescenceAverage();
          double absCoef = coefCalc.getEMFlAbsCoef(flauEnergy); //units um^-1
          //get a random direction vector
          double SExNorm = Math.random();
          double SEyNorm = Math.random();
          double SEzNorm = Math.random();
          //Draw the vector to the edge
          double flEscapeDist = getIntersectionDistance(previousX, previousY, previousZ, SExNorm, SEyNorm, SEzNorm); //um
          double escapeFraction = Math.exp(-absCoef * flEscapeDist);
          MonteCarloFlEscape += escapeFraction * flauEnergy;
          extraFlEscape += escapeFraction * flauEnergy;
          //add dose to voxels
          double energyRemained = 1- (escapeFraction * flauEnergy);
   //       addDoseToVoxels(flEscapeDist, SExNorm, SEyNorm, SEzNorm, previousX, previousY, previousZ, energyRemained, beam, coefCalc);
          addDoseToRegion(flEscapeDist, SExNorm, SEyNorm, SEzNorm, previousX, previousY, previousZ, energyRemained);
      //    addDoseToImagedRegion(flEscapeDist, SExNorm, SEyNorm, SEzNorm, previousX, previousY, previousZ, energyRemained, beam);
        }
        else {
          //need to do Auger electrons
          //Auger electron energy equals flEnergy - shell binding energy of Auger electron
          //for now ignore the shell binding energy so overestimating their significance
       //   double augerEnergy = collidedElement.getKFluorescenceAverage();
       //   totAugerEnergy += flauEnergy;
          //get a random direction vector
          double SExNorm = Math.random();
          double SEyNorm = Math.random();
          double SEzNorm = Math.random();
          //Draw the vector to the edge
          double augerEscapeDist = 1000 * getIntersectionDistance(previousX, previousY, previousZ, SExNorm, SEyNorm, SEzNorm); //um
          double augerStoppingPower = coefCalc.getStoppingPower(flauEnergy, false);
          double augerEnergyToEdge = augerStoppingPower * augerEscapeDist;
          if (augerEnergyToEdge < flauEnergy){
            MonteCarloAugerEscape += flauEnergy - augerEnergyToEdge;
            extraAugerEscape += flauEnergy - augerEnergyToEdge;
          }
          
          //redistribute the dose
          double distanceToStop = flauEnergy/augerStoppingPower; //nm
          double trackDistance = 0, augerEnergyLoss = 0; //Math.min(distanceToStop, augerEscapeDist);
          if (distanceToStop < augerEscapeDist) { //so loses all it's energy in the sample
            augerEnergyLoss = flauEnergy;
            trackDistance = distanceToStop;
          }
          else { //it escapes
            augerEnergyLoss = augerEnergyToEdge;
            trackDistance = augerEscapeDist;
          }
          if (Math.abs(previousX)/1000 <= beam.getImageX()/2 && Math.abs(previousY)/1000 <= beam.getImageY()/2) { //then in imaged region
          totImageSecEnergy += flauEnergy;
          }
  //        addDoseToVoxels(trackDistance, SExNorm, SEyNorm, SEzNorm, previousX, previousY, previousZ, augerEnergyLoss, beam, coefCalc);
          addDoseToRegion(trackDistance, SExNorm, SEyNorm, SEzNorm, previousX, previousY, previousZ, augerEnergyLoss);
          boolean augerOutsideImage = true;
          if (Math.abs(previousX)/1000 <= beam.getImageX()/2 && Math.abs(previousY)/1000 <= beam.getImageY()/2) { //then in imaged region
            augerOutsideImage = false;
          }
          addDoseToImagedRegion(trackDistance, SExNorm, SEyNorm, SEzNorm, previousX, previousY, previousZ, augerEnergyLoss, beam, augerOutsideImage);
        }
      }  
      }
    }
    else {
      double elasticElementRND = Math.random();
      ElementEM elasticElement = null;
      for (ElementEM e : elasticProbs.keySet()) {
        if (elasticProbs.get(e) > elasticElementRND) { //Then this element is the one that was ionised
          elasticElement = e;
          break;
        }
      }
      
      //get the angles
 //     double phi = 0, cosPhi = 1, psi = 0, AN = 0, AM = 0, V1 = 0, V2 = 0, V3 = 0, V4 = 0;
 //     double alpha = getRutherfordScreeningElement(elasticElement, electronEnergy);
      double RND = Math.random();
      /*
      cosPhi = 1 - ((2*alpha * Math.pow(RND, 2))/(1+alpha-RND));
      phi = Math.acos(cosPhi);
    */
 //     theta = Math.acos(1 - ((2*alpha * Math.pow(RND, 2))/(1+alpha-RND)));
      
      //ELSEPA stuff

      theta = getPrimaryElasticScatteringAngle(electronEnergy, elasticElement.getAtomicNumber());

      /*
      theta = previousTheta + theta;
      if (theta >= (2 * Math.PI)) {
        theta -= 2*Math.PI;
      }
      phi = 2 * Math.PI * Math.random();
      phi = previousPhi + phi;
      if (phi >= (2 * Math.PI)) {
        phi -= 2*Math.PI;
      }
    //now further update the primary
//      psi = 2 * Math.PI * Math.random();
      
      xNorm = Math.sin(theta) * Math.cos(phi);
      yNorm = Math.sin(theta) * Math.sin(phi);
      zNorm = Math.cos(theta);
      */
      double scatterPhi = getScatteringPhi();
      double[] newDirectionVector = getNewDirectionVector(xNorm, yNorm, zNorm, theta, scatterPhi);
      xNorm = newDirectionVector[0];
      yNorm = newDirectionVector[1];
      zNorm = newDirectionVector[2];
      theta = Math.acos(zNorm);
      phi = Math.acos(xNorm / Math.sin(theta));
      
      if (Double.isNaN(xNorm)){
        System.out.println("test");
      }
    }
      //update stopping powers
      //get new stoppingPower
      electronEnergy -= energyLost; 
      stoppingPower = coefCalc.getStoppingPower(electronEnergy, false);
      //get new lambdaT
      double lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, false);
      double innerShellLambda = coefCalc.betheIonisationxSection(electronEnergy, false);
      if (innerShellLambda > 0) {
        lambdaT =  1 / ((1/lambdaEl)+(1/innerShellLambda));
      }
      else {
        lambdaT = lambdaEl;
      }
      s = -lambdaT*Math.log(Math.random());
      elasticProbs = coefCalc.getElasticProbs(false);
      ionisationProbs = coefCalc.getAllShellProbs(false);
      Pinel = 1 - (lambdaT / lambdaEl); 
      
      //update the position and kinetic energy from the charge 
      if (electronEnergy >= 0.05) {
        
   //   MonteCarloCharge = (MonteCarloElectronsExited - MonteCarloElectronsEntered) * (electronNumber / numSimulatedElectrons)  * ((double)numSimSoFar/numSimulatedElectrons) * Beam.ELEMENTARYCHARGE;
   //   if (considerCharge == false) {
   //     MonteCarloCharge = 0;
   //   }
      if (considerCharge == true) {
        
      if (MonteCarloCharge != 0) {
        electronPosition[0] = previousX;
        electronPosition[1] = previousY;
        electronPosition[2] = previousZ;
        //chargePosition = {0, 0, 0};
        newVelocityVector = adjustVelocityVectorByCharge(electronPosition, chargePosition, s, electronEnergy, xNorm, yNorm, zNorm, coefCalc);
        newVelocityMagnitude = Vector.vectorMagnitude(newVelocityVector) /1E9;
        newVelocityUnitVector = Vector.normaliseVector(newVelocityVector);
      
        //update new xNorm. yNorm, zNorm
        xNorm = newVelocityUnitVector[0];
        yNorm = newVelocityUnitVector[1];
        zNorm = newVelocityUnitVector[2];
        //update theta and phi
        theta = Math.acos(zNorm);
        phi = Math.asin(yNorm/Math.sin(theta));
        
        if (Double.isNaN(xNorm)){
          System.out.println("test");
        }
      
        //work out the new kinetic energy
        gamma = 1 / Math.pow(1 - (Math.pow(newVelocityMagnitude, 2)/Math.pow(c, 2)), 0.5);
        newKineticEnergy = (gamma - 1) * m * Math.pow(c, 2)/Beam.KEVTOJOULES; // in keV
        kineticEnergyLossByCharge = electronEnergy - newKineticEnergy; //in keV
      }
      else {
        kineticEnergyLossByCharge = 0;
      }
      
      }
      if (considerCharge == true) {
       /*
        for (int i = 0; i < maxX; i++) {
          for (int j = 0; j < maxY; j++) {
            for (int k = 0; k < maxZ; k++) {
              if (voxelCharge[i][j][k] != 0) {
                int[] voxCoord = {i, j, k};
                if (thisPixel != voxCoord) {
                  //convert pixel coord to a cartesian coord
                  chargePosition = convertToCartesianCoordinates(i, j, k);
                  newVelocityVector = adjustVelocityVectorByCharge(electronPosition, chargePosition, s, electronEnergy, xNorm, yNorm, zNorm, coefCalc);
                  for (int m = 0; m < 3; m++) {
                    newTotalVelocityVector[m] += newVelocityVector[m];
                  }
                }
              }
            }
          }
        }
        newVelocityMagnitude = Vector.vectorMagnitude(newVelocityVector) /1E9; //m/s
        if (newVelocityMagnitude > 0) { //so there is a charge pulling
          newVelocityUnitVector = Vector.normaliseVector(newVelocityVector);
          //update new xNorm. yNorm, zNorm
          xNorm = newVelocityUnitVector[0];
          yNorm = newVelocityUnitVector[1];
          zNorm = newVelocityUnitVector[2];
          //update theta and phi
          theta = Math.acos(zNorm);
          phi = Math.asin(yNorm/Math.sin(theta));
          //work out the new kinetic energy
          gamma = 1 / Math.pow(1 - (Math.pow(newVelocityMagnitude, 2)/Math.pow(c, 2)), 0.5);
//         newKineticEnergy = ((gamma - 1) * m * Math.pow(c, 2)); // in Joules
          newKineticEnergy = ((gamma - 1) * m * Math.pow(c, 2))/Beam.KEVTOJOULES; // in keV
       //   kineticEnergyLossByCharge = ((electronEnergy*Beam.KEVTOJOULES) - newKineticEnergy)/Beam.KEVTOJOULES; //in keV
          kineticEnergyLossByCharge = electronEnergy - newKineticEnergy;
          
            KELostByChargeInSample += kineticEnergyLossByCharge;
          
        }
        else {
          kineticEnergyLossByCharge = 0;
        }
       */
      }
      
      }
      
      //update to new position
      xn = previousX + s * xNorm;
      yn = previousY + s * yNorm;
      zn = previousZ + s * zNorm;
    }
    else {
      if (surrounding == false) {
      exited = true;
          //I need to add the distance bit here - multislice 
      

        double escapeDist = 1000 * getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm); //nm
        double FSEStoppingPower = coefCalc.getStoppingPower(electronEnergy, false);
        double energyToEdge = FSEStoppingPower * escapeDist;
        if (energyToEdge < electronEnergy){ //the FSE has escaped
          double energyLostStep = 0, totFSEenLostLastStep = 0;
          double newEnergy = electronEnergy;
          for (int j = 0; j < 10; j++) { //I will need to play around with the amount of slicing when I am writing up
            energyLostStep = (escapeDist/10) * FSEStoppingPower;
            newEnergy -= energyLostStep;
            totFSEenLostLastStep += energyLostStep;
            FSEStoppingPower = coefCalc.getStoppingPower(newEnergy, false);
            if (newEnergy < 0.05) {
              if (newEnergy > 0) {
                totFSEenLostLastStep += newEnergy;
                lowEnDose -= newEnergy;
                if (Math.abs(previousX)/1000 <= beam.getImageX()/2 && Math.abs(previousY)/1000 <= beam.getImageY()/2) { //then in imaged region
                  if (outsideImage == false) {
                    imageSecDeposited += electronEnergy-newEnergy; //n ope
                  }
                  else {
                    imageEntry += electronEnergy-newEnergy; //nope
                  }
                }
              }
              break;
            }
          }
          if (newEnergy > 0) {
           // MonteCarloFSEEscape += newEnergy;
            if (entered == false) { //it started here
              newMonteCarloFSEEscape += newEnergy+KELostByChargeInSample;
              MonteCarloElectronsExited += 1;
              //add this charge to the pixel it came from
              voxelCharge[startingPixel[0]][startingPixel[1]][startingPixel[2]] += Beam.ELEMENTARYCHARGE * (electronNumber / numSimulatedElectrons);
            //split the dose up into voxels
         //     addDoseToVoxels(escapeDist, xNorm, yNorm, zNorm, previousX, previousY, previousZ, totFSEenLostLastStep, beam, coefCalc);
              addDoseToRegion(escapeDist, xNorm, yNorm, zNorm, previousX, previousY, previousZ, totFSEenLostLastStep);
              addDoseToImagedRegion(escapeDist, xNorm, yNorm, zNorm, previousX, previousY, previousZ, totFSEenLostLastStep, beam, outsideImage);
            }
            else {
              double energyDep = entryEnergy - (newEnergy+KELostByChargeInSample);
              if (energyDep > 0) {
                MonteCarloFSEEntry += entryEnergy - (newEnergy+KELostByChargeInSample);  //here the entered FSE has escaped again
              }
              //split the dose up into voxels
        //      addDoseToVoxels(escapeDist, xNorm, yNorm, zNorm, previousX, previousY, previousZ, totFSEenLostLastStep, beam, coefCalc);
              addDoseToRegion(escapeDist, xNorm, yNorm, zNorm, previousX, previousY, previousZ, totFSEenLostLastStep);
              addDoseToImagedRegion(escapeDist, xNorm, yNorm, zNorm, previousX, previousY, previousZ, totFSEenLostLastStep, beam, outsideImage);
            }
          }
        }
        else { //FSE has stopped in the sample...just
          /*
          if (entered == true && electronEnergy > 0.05) {  // here the entered FSE has stopped in the sample so all energy stays in sample
            MonteCarloFSEEntry += entryEnergy;
            MonteCarloElectronsEntered += 1;
            //add negative charge to this pixel
            thisPixel = convertToPixelCoordinates(xn, yn, zn);
            voxelCharge[thisPixel[0]][thisPixel[1]][thisPixel[2]] += Beam.ELEMENTARYCHARGE * (electronNumber / numSimulatedElectrons);
            addDoseToVoxels(escapeDist, xNorm, yNorm, zNorm, previousX, previousY, previousZ, electronEnergy, beam, coefCalc);
          }
          */
          
        }
     
      }
      else { //surrounding = true
        double distanceFrom = Math.pow(Math.pow(Math.abs(xn) - (XDimension/2), 2) + Math.pow(Math.abs(yn) - (YDimension/2), 2), 0.5);
        double distanceOf = Math.pow(Math.pow(XDimension, 2) + Math.pow(YDimension, 2), 0.5);
        if (distanceFrom > distanceOf || zn > ZDimension/2 || zn < -ZDimension/2) {
          track = false;
        }
        if (track == true) {
          previousTheta = theta;
          previousPhi = phi;
          previousX = xn;
          previousY = yn;
          previousZ = zn;
          //update dose and energy and stoppingPower
          energyLost = s * stoppingPower;
          energyLost += kineticEnergyLossByCharge;
          
          
        double RNDscatter = Math.random();
        if (RNDscatter < Pinel) { // If the scatter was an inner shell ionisation 
            //do nothing
          
        } //end if inelastic scatter
        else { //else it stays false and the collision will be elastic
            //elastic just want to get the angle
          double elasticElementRND = Math.random();
          ElementEM elasticElement = null;
          for (ElementEM e : elasticProbs.keySet()) {
            if (elasticProbs.get(e) > elasticElementRND) { //Then this element is the one that was ionised
              elasticElement = e;
              break;
            }
          }
          
          //get the angles
     //     double phi = 0, cosPhi = 1, psi = 0, AN = 0, AM = 0, V1 = 0, V2 = 0, V3 = 0, V4 = 0;
          double alpha = getRutherfordScreeningElement(elasticElement, electronEnergy);
          double RND = Math.random();
          /*
          cosPhi = 1 - ((2*alpha * Math.pow(RND, 2))/(1+alpha-RND));
          phi = Math.acos(cosPhi);
        */
          theta = Math.acos(1 - ((2*alpha * Math.pow(RND, 2))/(1+alpha-RND)));
          
          //ELSEPA stuff

          theta = getPrimaryElasticScatteringAngle(electronEnergy, elasticElement.getAtomicNumber());

          /*
          theta = previousTheta + theta;
          if (theta >= (2 * Math.PI)) {
            theta -= 2*Math.PI;
          }
          phi = 2 * Math.PI * Math.random();
          phi = previousPhi + phi;
          if (phi >= (2 * Math.PI)) {
            phi -= 2*Math.PI;
          }
          
          xNorm = Math.sin(theta) * Math.cos(phi);
          yNorm = Math.sin(theta) * Math.sin(phi);
          zNorm = Math.cos(theta);
          */
          double scatterPhi = getScatteringPhi();
          double[] newDirectionVector = getNewDirectionVector(xNorm, yNorm, zNorm, theta, scatterPhi);
          xNorm = newDirectionVector[0];
          yNorm = newDirectionVector[1];
          zNorm = newDirectionVector[2];
          theta = Math.acos(zNorm);
          phi = Math.acos(xNorm / Math.sin(theta));
          
          if (Double.isNaN(xNorm)){
            System.out.println("test");
          }
        }
        
          //update stopping powers
          //get new stoppingPower
          electronEnergy -= energyLost; 
          stoppingPower = coefCalc.getStoppingPower(electronEnergy, true);
          //get new lambdaT
          double FSExSection = getFSEXSection(electronEnergy);
          double lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, true);
          double innerShellLambda = coefCalc.betheIonisationxSection(electronEnergy, true);
          if (innerShellLambda > 0) {
            lambdaT = 1 / (1/lambdaEl + 1/innerShellLambda);
          }
          else {
            lambdaT = 1 / (1/lambdaEl);
          }
          s = -lambdaT*Math.log(Math.random());
   
          ionisationProbs = coefCalc.getAllShellProbs(true);
          elasticProbs = coefCalc.getElasticProbs(true);
          
          //need to check if it crosses before it reaches s again and if it does update to this point
          double intersectionDistance = 1000*getIntersectionDistance(previousX, previousY, previousZ, xNorm, yNorm, zNorm);
          double[] intersectionPoint = getIntersectionPoint(intersectionDistance, previousX, previousY, previousZ, xNorm, yNorm, zNorm);
          boolean pointInCrystal = isIntersectionInCrystal(intersectionPoint);
          if (intersectionDistance < s && pointInCrystal == true) { //then need to change region here and reset stuff
            surrounding = false;
            entered = true;
            electronEnergy -= intersectionDistance * stoppingPower;
            entryEnergy = electronEnergy;
            previousX = intersectionPoint[0]*1000;
            previousY = intersectionPoint[1]*1000;
            previousZ = intersectionPoint[2]*1000;
            stoppingPower = coefCalc.getStoppingPower(electronEnergy, false);
            FSExSection = getFSEXSection(electronEnergy);
            lambdaEl = coefCalc.getElectronElasticMFPL(electronEnergy, false);
            innerShellLambda = coefCalc.betheIonisationxSection(electronEnergy, false);
            if (innerShellLambda > 0) {
              lambdaT = 1 / (1/lambdaEl + 1/innerShellLambda);
            }
            else {
              lambdaT = 1 / (1/lambdaEl);
            }
            s = -lambdaT*Math.log(Math.random());
            elasticProbs = coefCalc.getElasticProbs(surrounding);
            ionisationProbs = coefCalc.getAllShellProbs(surrounding);
            
          }
          
          Pinel = 1 - (lambdaT / lambdaEl);
          
          //Charge stuff
          if (electronEnergy >= 0.05) {
            
     //     MonteCarloCharge = (MonteCarloElectronsExited - MonteCarloElectronsEntered) * (electronNumber / numSimulatedElectrons)  * ((double)numSimSoFar/numSimulatedElectrons) * Beam.ELEMENTARYCHARGE;
     //     if (considerCharge == false) {
     //       MonteCarloCharge = 0;
     //     }
          if (considerCharge == true) {
            
          if (MonteCarloCharge != 0) {
            electronPosition[0] = previousX;
            electronPosition[1] = previousY;
            electronPosition[2] = previousZ;
            //chargePosition = {0, 0, 0};
            newVelocityVector = adjustVelocityVectorByCharge(electronPosition, chargePosition, s, electronEnergy, xNorm, yNorm, zNorm, coefCalc);
            newVelocityMagnitude = Vector.vectorMagnitude(newVelocityVector)/1E9;
            newVelocityUnitVector = Vector.normaliseVector(newVelocityVector);
          
            //update new xNorm. yNorm, zNorm
            xNorm = newVelocityUnitVector[0];
            yNorm = newVelocityUnitVector[1];
            zNorm = newVelocityUnitVector[2];
            //update theta and phi
            theta = Math.acos(zNorm);
            phi = Math.asin(yNorm/Math.sin(theta));
            
            if (Double.isNaN(xNorm)){
              System.out.println("test");
            }
          
            //work out the new kinetic energy
            gamma = 1 / Math.pow(1 - (Math.pow(newVelocityMagnitude, 2)/Math.pow(c, 2)), 0.5);
            newKineticEnergy = (gamma - 1) * m * Math.pow(c, 2)/Beam.KEVTOJOULES; // in keV
            kineticEnergyLossByCharge = electronEnergy - newKineticEnergy; //in keV
          }
          else {
            kineticEnergyLossByCharge = 0;
          }
          
          }
          if (considerCharge == true) {
            /*
            for (int i = 0; i < maxX; i++) {
              for (int j = 0; j < maxY; j++) {
                for (int k = 0; k < maxZ; k++) {
                  if (voxelCharge[i][j][k] != 0) {
                    int[] voxCoord = {i, j, k};
                    if (thisPixel != voxCoord) {
                      //convert pixel coord to a cartesian coord
                      chargePosition = convertToCartesianCoordinates(i, j, k);
                      newVelocityVector = adjustVelocityVectorByCharge(electronPosition, chargePosition, s, electronEnergy, xNorm, yNorm, zNorm, coefCalc);
                      for (int m = 0; m < 3; m++) {
                        newTotalVelocityVector[m] += newVelocityVector[m];
                      }
                    }
                  }
                }
              }
            }
            newVelocityMagnitude = Vector.vectorMagnitude(newVelocityVector) /1E9; //m/s
            if (newVelocityMagnitude > 0) { //so there is a charge pulling
              newVelocityUnitVector = Vector.normaliseVector(newVelocityVector);
              //update new xNorm. yNorm, zNorm
              xNorm = newVelocityUnitVector[0];
              yNorm = newVelocityUnitVector[1];
              zNorm = newVelocityUnitVector[2];
              //update theta and phi
              theta = Math.acos(zNorm);
              phi = Math.asin(yNorm/Math.sin(theta));
              //work out the new kinetic energy
              gamma = 1 / Math.pow(1 - (Math.pow(newVelocityMagnitude, 2)/Math.pow(c, 2)), 0.5);
//             newKineticEnergy = ((gamma - 1) * m * Math.pow(c, 2)); // in Joules
              newKineticEnergy = ((gamma - 1) * m * Math.pow(c, 2))/Beam.KEVTOJOULES; // in keV
           //   kineticEnergyLossByCharge = ((electronEnergy*Beam.KEVTOJOULES) - newKineticEnergy)/Beam.KEVTOJOULES; //in keV
              kineticEnergyLossByCharge = electronEnergy - newKineticEnergy;
            }
            else {
              kineticEnergyLossByCharge = 0;
            }
            */
          }
          }
          //update to new position
          xn = previousX + s * xNorm;
          yn = previousY + s * yNorm;
          zn = previousZ + s * zNorm;
          
          if (Double.isNaN(xNorm)){
            System.out.println("test");
          }
          
          //need to also check whether to track the primary electron anymore or 
          
        }
        else {
          exited = true;
        }
      }
    }
    if (electronEnergy < 0.05) { // play with this and maybe graph it
      exited = true;
      thisPixel = convertToPixelCoordinates(previousX, previousY, previousZ);
      lowEnDose -= electronEnergy;
      if (Math.abs(previousX)/1000 <= beam.getImageX()/2 && Math.abs(previousY)/1000 <= beam.getImageY()/2) { //then in imaged region
        if (outsideImage == false) {
          imageSecDeposited += electronEnergy;
        }
        else {
          imageEntry += electronEnergy;
        }
      }
      
      if (surrounding == false && entered == false) { //the FSE was from the sample and never left
        //redistribute it's charge within the sample
        if (thisPixel != startingPixel) {
          //add negative charge to this pixel
          voxelCharge[thisPixel[0]][thisPixel[1]][thisPixel[2]] -= Beam.ELEMENTARYCHARGE * (electronNumber / numSimulatedElectrons);
          //add positive charge to the original pixel
          voxelCharge[startingPixel[0]][startingPixel[1]][startingPixel[2]] += Beam.ELEMENTARYCHARGE * (electronNumber / numSimulatedElectrons);
        }
        //add last bit of dose to voxel it stopped in
        if (Double.isNaN(electronEnergy)) {
          System.out.println("Test");
        }
        voxelDose[thisPixel[0]][thisPixel[1]][thisPixel[2]] += electronEnergy;
        if (Double.isNaN(voxelDose[thisPixel[0]][thisPixel[1]][thisPixel[2]])) {
          System.out.println("Test");
        }
      }
      if (surrounding == false && entered == true) {  // here the entered FSE has stopped in the sample so all energy stays in sample
        if (entryEnergy > 0) {
          MonteCarloFSEEntry += entryEnergy;
        }
        MonteCarloElectronsEntered += 1;
        
        //add negative charge to this pixel
        voxelCharge[thisPixel[0]][thisPixel[1]][thisPixel[2]] += Beam.ELEMENTARYCHARGE * (electronNumber / numSimulatedElectrons);
        if (Double.isNaN(electronEnergy)) {
          System.out.println("Test");
        }
        voxelDose[thisPixel[0]][thisPixel[1]][thisPixel[2]] += electronEnergy;
        if (Double.isNaN(voxelDose[thisPixel[0]][thisPixel[1]][thisPixel[2]])) {
          System.out.println("Test");
        }
      }
    }
  }
}

private double[] getElectronStartingDirection(Beam beam, double previousX, double previousY, double previousZ) {
  double beamSemiAngle = beam.getSemiAngle();  //in mrad
  if (beamSemiAngle == 0) {
 //   beamSemiAngle = 10;
  }
  double beamApertureRadius = beam.getApertureRadius();
  if (beamApertureRadius == 0) {
    beamApertureRadius = 1; // how many times bigger the aperture is than the beam
  }
    
  double beamRadius = Math.pow(Math.pow((beam.getBeamX()/2),2) + Math.pow(beam.getBeamY()/2, 2), 0.5);
  double extraLength = beamRadius * (beamApertureRadius - 1);
  //now get the aperture point
  double signX = (previousX >= 0) ? 1 : -1;
  double signY = (previousY >= 0) ? 1 : -1;
  double apertureX = signX*(Math.random() * beamApertureRadius) + previousX;
  double apertureY = signY*(Math.random() * beamApertureRadius) + previousY;
  double apertureZ = previousZ - (extraLength/Math.tan(beamSemiAngle/1000));
  double[] coordinateFrom = {apertureX, apertureY, apertureZ};
  double[] coordinateTo = {previousX, previousY, previousZ};
  double[] directionVector = Vector.vectorBetweenPoints(coordinateFrom, coordinateTo);
  double magnitude = Vector.vectorMagnitude(directionVector);
  for (int v = 0; v <= 2; v ++) {
    directionVector[v] /= magnitude;   //this is to normalise it 
  }
  directionVector[0] = 0;
  directionVector[1] = 0;
  directionVector[2] = 1;
  return directionVector;
}

private double[] getNewDirectionVector(double xNorm, double yNorm, double zNorm, 
    double scatterTheta, double scatterPhi) {
/*
  if (xNorm == 0) {
  xNorm += 0.000001;
}
if (yNorm == 0) {
  yNorm += 0.000001;
}
if (zNorm == 0) {
  zNorm += 0.000001;
}
*/
double AN = -(xNorm/yNorm);
if (yNorm == 0) {
  AN = 1;
}
double AM = 1/ Math.pow(1+AN*AN, 0.5);
double V1 = AN*Math.sin(scatterTheta);
double V2 = AN*AM*Math.sin(scatterTheta);
double V3 = Math.cos(scatterPhi);
double V4 = Math.sin(scatterPhi);
double ca = (xNorm*Math.cos(scatterTheta))+(V1*V3)+(yNorm*V2*V4);
double cb = (yNorm*Math.cos(scatterTheta))+(V4*(zNorm*V1-xNorm*V2));
double cc = (zNorm*Math.cos(scatterTheta))+(V2*V3)-(yNorm*V1*V4);
double[] newVector = {ca, cb, cc};
//normalise the vector
double[] normalVector = Vector.normaliseVector(newVector);
return normalVector;
}

private double getScatteringPhi() {
  double phi = 2 * Math.PI * Math.random();
  return phi;
}

private double getPrimaryElasticScatteringAngle(double electronEnergy, int atomicNumber){
  boolean highEnergy = false;
  if (electronEnergy > 20) {
    highEnergy = true;
  }
 
  //determine if need to get data from file or it's already loaded
  boolean getFile = mapPopulated(highEnergy, atomicNumber);
  
  //get the right file if I need to
  if (getFile == true) {
    
    TreeMap<Double, double[]> elementData = new TreeMap<Double, double[]>();
    try {
      elementData =  getAngleFileData(highEnergy, atomicNumber);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
    //now add the file data to the global array
    if (highEnergy == true) {
      highEnergyAngles[atomicNumber] = elementData;
    }
    else {
      lowEnergyAngles[atomicNumber] = elementData;
    }
  }
  
  //Now use the data in the global array to work out the angle
  //get nearest energy
  Double energyKey = returnNearestEnergy(highEnergy, atomicNumber, electronEnergy);
  
  //should probably interpolate the values here tbh.... will do at some point
  
  //get the differential cross sections for that energy of the element
  double[] energyAngleProbs = null;
  if (highEnergy == true) {
    energyAngleProbs = highEnergyAngles[atomicNumber].get(energyKey);
  }
  else {
    energyAngleProbs = lowEnergyAngles[atomicNumber].get(energyKey);
  }
  //get the angle from this 
  double deflectionAngle = returnDeflectionAngle(highEnergy, energyAngleProbs);
  
  if (Double.isNaN(deflectionAngle)){
    System.out.println("test");
  }
  return deflectionAngle;
}

private double getFSEEnergy(double electronEnergy, double shellBindingEnergy) {
  double RNDFSEEnergy = Math.random();
  double energyCutOff = (energyCUTOFF/1000.0)/electronEnergy;
  
  double tau = electronEnergy/511;
  double alphaParam = Math.pow(tau/(tau+1), 2);
  double betaParam = (2*tau + 1)/Math.pow(tau+1, 2);
  double gammaParam = (1/energyCutOff)-(1/(1-energyCutOff))-(alphaParam*energyCutOff)-(betaParam*Math.log((1-energyCutOff)/((electronEnergy*energyCutOff)/511)));
  double omegaParam = RNDFSEEnergy*(gammaParam + (alphaParam/2)) - gammaParam;
  double epsilon = (omegaParam-2-betaParam+Math.pow(Math.pow(omegaParam-2-betaParam, 2) + 4*(omegaParam+alphaParam-2*betaParam), 0.5)) /
                    (2*(omegaParam+alphaParam-2*betaParam));
  
  double omega = 1 / ((1/energyCutOff) - ((1/energyCutOff)-2)*RNDFSEEnergy);
//    double omega = 1 / (100 - 98*Math.random());
  
  FSEcount +=1;
  FSEsum += epsilon*electronEnergy;
  return epsilon;
}

private void FlAugerMonteCarlo(Element collidedElement, double previousX, double previousY, double previousZ, 
                                int collidedShell, CoefCalc coefCalc, boolean surrounding, Beam beam) {
  boolean outsideImage = true;
  if (Math.abs(previousX)/1000 <= beam.getImageX()/2 && Math.abs(previousY)/1000 <= beam.getImageY()/2) { //then in imaged region
    outsideImage = false;
  }
  double shellFluorescenceYield = 0;
  double flauEnergy = 0;
  if (collidedShell == 0) {
    shellFluorescenceYield = collidedElement.getKShellFluorescenceYield();
    flauEnergy = collidedElement.getKFluorescenceAverage();
  }
  else if (collidedShell == 1) {
    shellFluorescenceYield = collidedElement.getL1ShellFluorescenceYield();
    flauEnergy = collidedElement.getLFluorescenceAverage();
  }
  else if (collidedShell == 2) {
    shellFluorescenceYield = collidedElement.getL2ShellFluorescenceYield();
    flauEnergy = collidedElement.getLFluorescenceAverage();
  }
  else if (collidedShell == 3){
    shellFluorescenceYield = collidedElement.getL3ShellFluorescenceYield();
    flauEnergy = collidedElement.getLFluorescenceAverage();
  }
  
  //Do Fl or Auger
//RND for FL or Auger given it was that element
  double fluoresenceYieldKRND = Math.random();
//  double KshellFluorescenceYield = collidedElement.getKShellFluorescenceYield();
  if(flauEnergy > 0 && !Double.isNaN(flauEnergy)) {
    //subtract dose from the voxel that's this is in
    if (surrounding == false) {
      int[] getPixel = convertToPixelCoordinates(previousX, previousY, previousZ);
   //   voxelDose[getPixel[0]][getPixel[1]][getPixel[2]] -= flauEnergy;
    }
    if (fluoresenceYieldKRND <= shellFluorescenceYield) { 
    //then it's fluorescence
      // get the absorption coefficient of the crystal
  //    double flEnergy = collidedElement.getKFluorescenceAverage();
      if (surrounding == false) {
        double absCoef = coefCalc.getEMFlAbsCoef(flauEnergy); //units um^-1
        //get a random direction vector
        double SExNorm = Math.random();
        double SEyNorm = Math.random();
        double SEzNorm = Math.random();
        //Draw the vector to the edge
        double flEscapeDist = getIntersectionDistance(previousX, previousY, previousZ, SExNorm, SEyNorm, SEzNorm); //um
        double escapeFraction = Math.exp(-absCoef * flEscapeDist);
        MonteCarloFlEscape += escapeFraction * flauEnergy;
        numFL += 1;
        //add dose to voxels
        double energyRemained = flauEnergy- (escapeFraction * flauEnergy);
    //    addDoseToVoxels(flEscapeDist, SExNorm, SEyNorm, SEzNorm, previousX, previousY, previousZ, energyRemained, beam, coefCalc);
        addDoseToRegion(flEscapeDist, SExNorm, SEyNorm, SEzNorm, previousX, previousY, previousZ, energyRemained);
  //      addDoseToImagedRegion(flEscapeDist, SExNorm, SEyNorm, SEzNorm, previousX, previousY, previousZ, energyRemained, beam);
      }
      //if it's in the surrounding don't bother with fluorescence
    }
    else {
      //need to do Auger electrons
      //Auger electron energy equals flEnergy - shell binding energy of Auger electron
      //for now ignore the shell binding energy so overestimating their significance
   //   double augerEnergy = collidedElement.getKFluorescenceAverage();
      totAugerEnergy += flauEnergy;
      if (Math.abs(previousX)/1000 <= beam.getImageX()/2 && Math.abs(previousY)/1000 <= beam.getImageY()/2) { //then in imaged region
      totImageSecEnergy += flauEnergy;
      }
      numAuger += 1;
      //get a random direction vector
      double SExNorm = Math.random();
      double SEyNorm = Math.random();
      double SEzNorm = Math.random();
      //Draw the vector to the edge
      if (surrounding == false) {
        double augerEscapeDist = 1000* getIntersectionDistance(previousX, previousY, previousZ, SExNorm, SEyNorm, SEzNorm); //um
        double augerStoppingPower = coefCalc.getStoppingPower(flauEnergy, false);
        double augerEnergyToEdge = augerStoppingPower * augerEscapeDist;
        if (augerEnergyToEdge < flauEnergy){
          MonteCarloAugerEscape += flauEnergy - augerEnergyToEdge;
          MonteCarloElectronsExited += 1;
        }
        //redistribute the dose
        double distanceToStop = flauEnergy/augerStoppingPower; //nm
        double trackDistance = 0, augerEnergyLoss = 0; //Math.min(distanceToStop, augerEscapeDist);
        if (distanceToStop < augerEscapeDist) { //so loses all it's energy in the sample
          augerEnergyLoss = flauEnergy;
          trackDistance = distanceToStop;
        }
        else { //it escapes
          augerEnergyLoss = augerEnergyToEdge;
          trackDistance = augerEscapeDist;
        }
    //    addDoseToVoxels(trackDistance, SExNorm, SEyNorm, SEzNorm, previousX, previousY, previousZ, augerEnergyLoss, beam, coefCalc);
        addDoseToRegion(trackDistance, SExNorm, SEyNorm, SEzNorm, previousX, previousY, previousZ, augerEnergyLoss);
        addDoseToImagedRegion(trackDistance, SExNorm, SEyNorm, SEzNorm, previousX, previousY, previousZ, augerEnergyLoss, beam, outsideImage);
      }
      else { //surrounding = true
        Double augerEntryDist = 1000* getIntersectionDistance(previousX, previousY, previousZ, SExNorm, SEyNorm, SEzNorm); //um
        boolean pointInCrystal = false;
        if (augerEntryDist <= 0 || augerEntryDist.isNaN()
            || augerEntryDist.isInfinite()){
              //do nothing
            }
        else {
          //see if in crystal
          double[] interSectionPoint = getIntersectionPoint(augerEntryDist, previousX, previousY, previousZ, SExNorm, SEyNorm, SEzNorm);
        }
        if (pointInCrystal == true) {
          double augerStoppingPower = coefCalc.getStoppingPower(flauEnergy, true);
          double augerEnergyToEdge = augerStoppingPower * augerEntryDist;
          if (augerEnergyToEdge < flauEnergy){
            MonteCarloAugerEntry += flauEnergy - augerEnergyToEdge;
            MonteCarloElectronsEntered += 1;
          }
        }
        //I am overestimating here a bit because the Auger can't enter and come back out again, 
        //given the small significance of Auger this doesn't matter much
        
        //in the voxel model I'm not going to have auger electrons enter at all for now!
      }
    }
  }
}

private InputStreamReader locateFile(String filePath) 
          throws UnsupportedEncodingException, FileNotFoundException{
  InputStream is = getClass().getResourceAsStream("/" + filePath);
  
  if (is == null) {
    is = new FileInputStream(filePath);
  }
  
  return new InputStreamReader(is, "US-ASCII");
}

private boolean mapPopulated(boolean highEnergy, int atomicNumber) {
  if (highEnergy == true) {
    if (highEnergyAngles[atomicNumber] == null) {
      return true;
    }
    else {
      return false;
    }
  }
  else {
    if (lowEnergyAngles[atomicNumber] == null) {
      return true;
    }
    else {
      return false;
    }
  }
}

  //--put it in here when I have copy and paste back
private TreeMap<Double, double[]> getAngleFileData(boolean highEnergy, int atomicNum) throws IOException{
  String elementNum = String.valueOf(atomicNum) + ".csv";
  String filePath = "";
  if (highEnergy == true) {
    filePath = "constants/above_20000/" + elementNum;
  }
  else {
    filePath = "constants/below_20000/" + elementNum;
  }
  
  InputStreamReader isr = locateFile(filePath);
  BufferedReader br = new BufferedReader(isr);
  TreeMap<Double, double[]> elementData = new TreeMap<Double, double[]>();
  String line;
  String[] components;
  int count = -1;
  while ((line = br.readLine()) != null) {
    count +=1 ;
    components = line.split(",");
    if (count > 0) { //if this is not the first line
      Double energy = Double.valueOf(components[0]);
      String[] angleProbsString = Arrays.copyOfRange(components, 1, components.length);
      double[] angleProbs = new double[angleProbsString.length];
      for (int i = 0; i < angleProbsString.length; i++) {
        angleProbs[i] = Double.parseDouble(angleProbsString[i]);
      }
      //Now add this to the local treemap
      elementData.put(energy, angleProbs);
    }
  }
  return elementData;
}


private Double returnNearestEnergy(boolean highEnergy, int atomicNumber, double electronEnergy) {
  Double nearestEnergy = 0.;
  if (electronEnergy >= 0.05 && electronEnergy <= 300) {
    Double beforeKey = 0.;
    Double afterKey = 0.;
    if (highEnergy == true) {
      beforeKey = highEnergyAngles[atomicNumber].floorKey(electronEnergy);
      afterKey = highEnergyAngles[atomicNumber].ceilingKey(electronEnergy);
      
    }
    else {
      beforeKey = lowEnergyAngles[atomicNumber].floorKey(electronEnergy);
      afterKey = lowEnergyAngles[atomicNumber].ceilingKey(electronEnergy);
    }
    if (beforeKey == null) {
      beforeKey = 0.;
    }
    if (afterKey == null) {
      afterKey = 0.;
    }
    beforeKey = (beforeKey == 0.) ? afterKey: beforeKey;
    afterKey = (afterKey == 0.) ? beforeKey: afterKey;
    if (Math.abs(electronEnergy - beforeKey) <= Math.abs(electronEnergy-afterKey)) {
      nearestEnergy = beforeKey;
    }
    else {
      nearestEnergy = afterKey;
    }
    
  }
  if (electronEnergy > 300) {
    nearestEnergy = 300.0;
  }
  
  return nearestEnergy;
}

private double returnDeflectionAngle(boolean highEnergy, double[] energyAngleProbs) {
  double totalProb = 0;
  for (int i = 0; i < energyAngleProbs.length; i++) {
    totalProb += energyAngleProbs[i];
  }
  double[] probPerAngle = new double[energyAngleProbs.length];
  double sumProb = 0;
  for (int j = 0; j < energyAngleProbs.length; j++) {
    sumProb += energyAngleProbs[j];
    probPerAngle[j] = sumProb/totalProb;
  }
  
  double RND = Math.random();
  double index = 0;
  for (int k = 0; k < probPerAngle.length; k++) {
    if (probPerAngle[k] >= RND) {
      index = k;
      break;
    }
  }
  //convert the index to an angle
  double angleDegrees = 0;
  if (highEnergy == true) {
    double startFactor = 0.;
    int factor = 0;
    double divideFactor = 4;
    double minusFactor = 0;
    double modFactor = 0;
    if (index >=1 && index < 146) {
      minusFactor = 1;
      modFactor = 36;
      factor = (int) ((int) (index - minusFactor)/modFactor);
      startFactor = Math.pow(10, factor) * 0.0001;
      divideFactor = 4;
    }
    else if (index >= 146 && index < 236) {
   //   factor = (int) (index-146)/100;
      startFactor = 1;
      divideFactor = 10;
      minusFactor = 146;
      modFactor = 90;
    }
    else if (index >= 236 && index <= 296) {
      startFactor = 10;  //go until 25
      divideFactor = 40;
      minusFactor = 236;
      modFactor = 60;
    }
    else if (index > 296) {
      startFactor = 25;
      divideFactor = 50;
      minusFactor = 296;
      modFactor = 1000000; //just anything super high as all but first one
    }
    angleDegrees = startFactor + (((index-minusFactor)%modFactor)*(startFactor/divideFactor));
    if (Double.isNaN(angleDegrees)){
   //   System.out.println("test");
      angleDegrees = 0;
    }
  }
  else {
    angleDegrees = 1.0 * index;
  }
  double angleRadians = angleDegrees * Math.PI/180;
  /*
  if (index > 296 && highEnergy == true) {
    System.out.println("test");
  }
  */

  return angleRadians;
}

private boolean findIfOuterShellIonised(Element e, Map<Element, Double> ionisationProbs, double elementRND) {
  boolean hit = false;
  double elementShellProbs = ionisationProbs.get(e);
    if (elementShellProbs > elementRND) { //Then this element is the one that was ionised
      hit = true;
    }
  return hit;
}

private int getGOSInelasticType(double[][] shellProbs, int shellIndex) {
  double runningSum = 0;
  double RND = Math.random();
  int type = 0;
  for (int i = 0; i < 3; i++) {
    runningSum += shellProbs[shellIndex][i]/shellProbs[shellIndex][3];
    if (runningSum > RND) { //then this type of collision
      type = i;
      break;
    }
  }
  return type;
}

private int getGOSInelasticTypePlasmon(double[] plasmonProbs) {
  double runningSum = 0;
  double RND = Math.random();
  int type = 0;
  for (int i = 0; i < 3; i++) {
    runningSum += plasmonProbs[i]/plasmonProbs[3];
    if (runningSum > RND) { //then this type of collision
      type = i;
      break;
    }
  }
  return type;
}

public double WkToWak(double E, double Wk, double Uk) {
  if (E*1000 > 3*Wk - 2*Uk) {
    return Wk;
  }
  else {
    return (E*1000+2*Uk)/3;
  }
}

public double getQak(double E, double Wk, double Uk) {
  if (E*1000 > 3*Wk - 2*Uk) {
    return Uk;
  }
  else {
    return Uk * (E*1000/(3*Wk-2*Uk));
  }
}

public double getEnergyLossDistant(double Wdis, double Uk){ 
  double RND = Math.random();
  double W = Wdis - Math.pow(RND*Math.pow(Wdis-Uk, 2), 0.5);
  return W; //returning eV
}

public double getClosea(double E) { // E in keV
  double m = 9.10938356E-31; // in Kg
  double c = 299792458;
  double csquared = c*c;  // (m/s)^2
  double Vo = (E) * Beam.KEVTOJOULES;
  double a = Math.pow(Vo/(Vo+m*csquared), 2);
  return a;
}

public double getGOSPrimaryThetaLong(double EkeV, double Q, double WakeV, double previousTheta) {
  double m = 9.10938356E-31; // in Kg
  double c = 299792458;
  double csquared = c*c;  // (m/s)^2
  double theta = 0;
  //again make sure I sort out units in here
  double E = EkeV * Beam.KEVTOJOULES;
  double Wak = (WakeV/1000)*Beam.KEVTOJOULES;
  double numerator = E*(E+2*m*csquared) + (E-Wak)*(E-Wak+2*m*csquared) - Q*(Q+2*m*csquared);
  double denominator = 2*Math.pow(E*(E+2*m*csquared)*(E-Wak)*(E-Wak+2*m*csquared), 0.5);
  double cosTheta = numerator/denominator;
  theta = Math.acos(cosTheta);
  /*
  theta = previousTheta + theta;
  if (theta >= (2 * Math.PI)) {
    theta -= 2*Math.PI;
  }
  */
  return theta;
}

public double getRandomk(double E, double Qk) { //E in keV and Qk in eV
  double kc = Math.max(Qk, Wcc) / (E*1000 + Qk);  //get units right ofc
  double k = 0;
  double a = getClosea(E);
  double RND = Math.random();
  double zeta = RND * (1.0+5.0*a*kc/2.0);
  if (zeta < 1) {
    k = kc / (1-zeta*(1-2*kc));
  }
  else {
    k = kc + (zeta-1)*(1-2*kc)/(5*a*kc);
  }
  return k; //dimensionless
}

public double getPDFk(double E, double k, double Qk) {
  double kc = Math.max(Qk, Wcc) / (E*1000 + Qk);  //get units right ofc
  double a = getClosea(E);   //assume this is the gamma one not sturnheimer one
  double PDF = (Math.pow(k, -2) + Math.pow(1-k, -2) - 1/(k*(1-k)) + a*(1+1/(k*(1-k))))
                * heavisideStepFunction(k-kc) * heavisideStepFunction(0.5-k);
  return PDF;
}

//now do the rejection algorithm
public double samplek(double E, double Qk) {
  double a = getClosea(E);
  boolean exit = false;
  double k = 0;
  int count = 0;
  while (exit == false) {
    k = getRandomk(E, Qk);
    double RND = Math.random();
    double LHS = RND * (1 + 5*a*Math.pow(k, 2));
    double RHS = Math.pow(k, 2) * getPDFk(E, k, Qk);
    if (LHS < RHS) {
      exit = true;
    }
    // testing clause
    count += 1;
    if (count > 10000) {

      System.out.println("the random sampling of k is always being rejected");
      break;
    }
  }
  return k;
}

private int heavisideStepFunction(double x) {
  if (x >= 0) {
    return 1;
  }
  else {
    return 0;
  }
}

private double getGOSPrimaryThetaClose(double EkeV, double WkeV, double previousTheta) {
  double m = 9.10938356E-31; // in Kg
  double c = 299792458;
  double csquared = c*c;  // (m/s)^2
  double E = EkeV*Beam.KEVTOJOULES;
  double W = WkeV*Beam.KEVTOJOULES;
  
  double cosTheta = Math.pow(((E-W)/E) * ((E+ 2*m*csquared)/ (E-W+2*m*csquared)),0.5);
  double theta = Math.acos(cosTheta);
  /*
  theta = previousTheta + theta;
  if (theta >= (2 * Math.PI)) {
    theta -= 2*Math.PI;
  }
  */
  return theta;
}

public double secondaryThetaClose(double EkeV, double WkeV, double previousTheta) {
  double m = 9.10938356E-31; // in Kg
  double c = 299792458;
  double csquared = c*c;  // (m/s)^2
  double W = WkeV * Beam.KEVTOJOULES;
  double E = EkeV * Beam.KEVTOJOULES;
  double cosTheta = Math.pow((W/E)*((E+2*m*csquared)/(W+2*m*csquared)), 0.5);
  double theta = Math.acos(cosTheta);
  /*
  theta = previousTheta + theta;
  if (theta >= (2 * Math.PI)) {
    theta -= 2*Math.PI;
  }
  */
  return theta;
}

public double secondaryThetaDistant(double E, double WakeV, double Q, double previousTheta) {
  double m = 9.10938356E-31; // in Kg
  double c = 299792458;
  double csquared = c*c;  // (m/s)^2
  double Vo = E * Beam.KEVTOJOULES;
  double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
  
  double Wak = (WakeV/1000)*Beam.KEVTOJOULES;
  double cosTheta = Math.pow(((Math.pow(Wak, 2)/betaSquared)/(Q*(Q+2*m*csquared)))
                    *Math.pow(1+(Q*(Q+2*m*csquared)-Math.pow(Wak, 2))/(2*Wak*(Vo+m*csquared)), 2),0.5);
  double theta = Math.acos(cosTheta);
  /*
  theta = previousTheta + theta;
  if (theta >= (2 * Math.PI)) {
    theta -= 2*Math.PI;
  }
  */
  return theta;
}

private boolean isMicrocrystalAt(final double x, final double y, final double z) {
  //Note that this is absolutely only right for a cuboid at the moment
  //This can stay as a quick test
  //this quick test actually messes with the program and it's imperfect placing of pixels
  
  if ((x > XDimension/2) || (x < -XDimension/2)) {
    return false;
  }
  if ((y > YDimension/2) || (y < -YDimension/2)) {
    return false;
  }
  if ((z > ZDimension/2) || (z < -ZDimension/2)) {
    return false;
  }
   
  //now do the crystal occupancy stuff
  //convert xyz to ijk
  
  int[] pixelCoords = convertToPixelCoordinates(x, y, z); 
  
  boolean[] occ = crystOccEM[pixelCoords[0]][pixelCoords[1]][pixelCoords[2]];  //This means that if has already been done don't do it again
                                        // Really needed to speed up Monte Carlo

  if (!occ[0]) {
    occ[1] = calculateCrystalOccupancy(x, y, z);
    occ[0] = true;
  }

  return occ[1];
}

private int[] convertToPixelCoordinates(final double x, final double y, final double z) {
  double[] xMinMax = this.minMaxVertices(0, verticesEM);
  double[] yMinMax = this.minMaxVertices(1, verticesEM);
  double[] zMinMax = this.minMaxVertices(2, verticesEM);
  int i = (int) StrictMath.round(((x/1000) - xMinMax[0]) * crystalPixPerUMEM);
  int j = (int) StrictMath.round(((y/1000) - yMinMax[0]) * crystalPixPerUMEM);
  int k = (int) StrictMath.round(((z/1000) - zMinMax[0]) * crystalPixPerUMEM);
  int[] pixelCoords = {i, j, k};
  return pixelCoords;
}

private double[] convertToCartesianCoordinates(final int i, final int j, final int k) {
  double[] xMinMax = this.minMaxVertices(0, verticesEM);
  double[] yMinMax = this.minMaxVertices(1, verticesEM);
  double[] zMinMax = this.minMaxVertices(2, verticesEM);
  double x = ((i/crystalPixPerUMEM) + xMinMax[0])*1000;
  double y = ((j/crystalPixPerUMEM) + yMinMax[0])*1000;
  double z = ((k/crystalPixPerUMEM) + zMinMax[0])*1000;
  double[] cartesianCoords = {x, y, z};
  return cartesianCoords;
}

private int[] getMaxPixelCoordinates() {
  double[] xMinMax = this.minMaxVertices(0, verticesEM);
  double[] yMinMax = this.minMaxVertices(1, verticesEM);
  double[] zMinMax = this.minMaxVertices(2, verticesEM);
  Double xdim = xMinMax[1] - xMinMax[0];
  Double ydim = yMinMax[1] - yMinMax[0];
  Double zdim = zMinMax[1] - zMinMax[0];
  int nx = (int) StrictMath.round(xdim * crystalPixPerUMEM) + 1;
  int ny = (int) StrictMath.round(ydim * crystalPixPerUMEM) + 1;
  int nz = (int) StrictMath.round(zdim * crystalPixPerUMEM) + 1;
  int[] maxCoord = {nx, ny, nz};
  return maxCoord;
}

private void addDoseToVoxels(double s, double xNorm, double yNorm, double zNorm, double previousX, double previousY, double previousZ
                             , double energyLost, Beam beam, CoefCalc coefCalc) {
  int numberBins = getNumberOfBins(s);
  double binLength = s / numberBins;
  double energyDivision = energyLost/numberBins;
  double xPos, yPos, zPos = 0;
  for (int j = 1; j <= numberBins; j++) {
    xPos = previousX + (binLength *j) * xNorm;
    yPos = previousY + (binLength *j) * yNorm;
    zPos = previousZ + (binLength *j) * zNorm;
    if (isMicrocrystalAt(xPos, yPos, zPos) == true) { // needed for electrons that enter from the surrounding 
      addDoseToPosition(xPos, yPos, zPos, energyDivision, beam, coefCalc);
    }
  }
}

private void addDoseToRegion(double s, double xNorm, double yNorm, double zNorm, double previousX, double previousY, double previousZ
                            , double energyLost) {
  if (energyLost > 0) {
  double regionBinDistance = Math.min((XDimension/2)/10, (YDimension/2)/10);
  int numBins = (int) Math.ceil(s/regionBinDistance);
  double binLength = s/numBins;
  double energyDivision = energyLost/numBins;
  double xPos, yPos, zPos = 0;
  for (int j = 1; j <= numBins; j++) {
    xPos = previousX + (binLength *j) * xNorm;
    yPos = previousY + (binLength *j) * yNorm;
    zPos = previousZ + (binLength *j) * zNorm;
    if (isMicrocrystalAt(xPos, yPos, zPos) == true) { // needed for electrons that enter from the surrounding 
      //find region
      int indexX = (int) ((((XDimension/2)-Math.abs(xPos)))/(0.5*XDimension/NUM_REGIONS));
      int indexY = (int) ((((YDimension/2)-Math.abs(yPos)))/(0.5*YDimension/NUM_REGIONS));
      int index = Math.min(indexX, indexY);
      if (index == 10) { //stops it breaking if it's exactly in 0,0,0
        index -= 1;
      }
      //add energy to this region
      regionDose[index] += energyDivision;
    }
  }
  }
  else {
 //   System.out.println("Test");
  }
}

private void addDoseToImagedRegion(double s, double xNorm, double yNorm, double zNorm, double previousX, double previousY, double previousZ
                                    , double energyLost, Beam beam, boolean entered) {
  int numBins = 10;
  double xPos, yPos;
  if (energyLost > 0) {
    //split up in the track to a certain number of bins and split up the energy into that number of bins
    double binDistance = s/numBins;
    double energyLostBin = energyLost / numBins;
    for (int i = 1; i <= numBins; i++) {
      xPos = previousX + (binDistance *i) * xNorm;
      yPos = previousY + (binDistance *i) * yNorm;
      //if this bin is located in the imaged region, then add the dose to this region, if it isn't don't do anything
      //the imaged region is assumed to be centered on 0 0, like the beam is assumed to be centred on 0 0
      //test
      double x = beam.getImageX();
      double y = beam.getImageY();
      if (Math.abs(xPos)/1000 <= beam.getImageX()/2 && Math.abs(yPos)/1000 <= beam.getImageY()/2) { //then in imaged region
        MonteCarloImageDose += energyLostBin;
        if (entered == true) {
          imageEntry += energyLostBin;
        }
        else {
          imageSecDeposited += energyLostBin;
        }
      }
    }
  }
}

private double PosOrNeg() {
  double sign = 1;
  if (Math.random() < 0.5) {
    sign = -1;
  }
  return sign;
}

private void addDoseToPosition(double x, double y, double z, double keV, Beam beam, CoefCalc coefCalc) {
  int[] voxCoord = convertToPixelCoordinates(x, y, z);
  if (Double.isNaN(keV)) {
    System.out.println("Test");
  }
  voxelDose[voxCoord[0]][voxCoord[1]][voxCoord[2]] += keV; //probably easier to do keV and process at the end
  if (Double.isNaN(voxelDose[voxCoord[0]][voxCoord[1]][voxCoord[2]])) {
    System.out.println("Test");
  }
  /*
  double electronNumber = beam.getExposure() * (beam.getBeamArea()*1E8);
  double totalJ = (keV * (electronNumber/numSimulatedElectrons))*Beam.KEVTOJOULES;
  
  double voxelVolume = Math.pow((1/crystalPixPerUMEM) /1E4,3); // voxel volume in cm^3
  double voxelMass = (coefCalc.getDensity() * voxelVolume)/1000; //voxel mass in Kg
  double dose = (totalJ/voxelMass) / 1E6; //dose in MGy
  voxelDose[voxCoord[0]][voxCoord[1]][voxCoord[2]] += dose;
  */
}

private double convertVoxEnergyToDose(double energy, Beam beam, CoefCalc coefCalc) {
  double electronNumber = beam.getExposure() * (beam.getBeamArea()*1E8);
  double totalJ = (energy * (electronNumber/numSimulatedElectrons))*Beam.KEVTOJOULES;
  
  double voxelVolume = Math.pow((1/crystalPixPerUMEM) /1E4,3); // voxel volume in cm^3
  double voxelMass = (coefCalc.getDensity() * voxelVolume)/1000; //voxel mass in Kg
  double dose = (totalJ/voxelMass) / 1E6; //dose in MGy
  if (Double.isNaN(dose)) {
    System.out.println("Test");
  }
  return dose;
}

private double convertRegionEnergyToDose(double energy, int index,Beam beam, CoefCalc coefCalc) {
  //find total energy in the region
  double electronNumber = beam.getExposure() * (beam.getBeamArea()*1E8);
  double totalJ = (energy * (electronNumber/numSimulatedElectrons))*Beam.KEVTOJOULES;
  
  double volume = regionVolume[index] / 1E21; //in cm^3
  double regionMass = (coefCalc.getDensity()*volume) / 1000;
  double dose = (totalJ/regionMass) / 1E6; //MGy
  return dose;
}

private void populateRegionVolumes() {
  //find the volume of the region
  //for now I'm just going to deal with cubes but will need to change this later
  double totalVolume = XDimension*YDimension*ZDimension;
  double sumVolume = 0;
  for (int i=0; i < NUM_REGIONS; i++) {
    double innerVolume = (XDimension - (i+1)*(XDimension/NUM_REGIONS)) * (YDimension-(i+1)*(YDimension/NUM_REGIONS)) * ZDimension;
    regionVolume[i] = totalVolume - (innerVolume + sumVolume);
    sumVolume += regionVolume[i];
  }
}

private boolean isIntersectionInCrystal(double[] intersectionPoint) {
  //fudge the point
  for (int j = 0; j < 3; j++) {
    if (intersectionPoint[j] < 0) {
      intersectionPoint[j] += 0.000001;
    }
    else {
      intersectionPoint[j] -= 0.000001;
    }
  }
  boolean pointInCrystal = isMicrocrystalAt(intersectionPoint[0]*1000, intersectionPoint[1]*1000, intersectionPoint[2]*1000);
  return pointInCrystal;
}

private double getIntersectionDistance(double x, double y, double z, double ca, double cb, double cc) {
  if (normals == null) {
    calculateNormals(false);
  }

  double[] directionVector = {ca, cb, cc}; //the actual direction vector
  double minIntersect = 0;
  double[] origin = new double[3];
  origin[0] = x/1000;
  origin[1] = y/1000;
  origin[2] = z/1000;
  
  double intersectionDistance = 0;
  for (int l = 0; l < indicesEM.length; l++) {
    intersectionDistance = Vector.rayTraceDistance(normals[l],
        directionVector, origin, originDistances[l]);

    Double distanceObject = Double.valueOf(intersectionDistance);

    if (intersectionDistance < 0 || distanceObject.isNaN()
        || distanceObject.isInfinite()) {
        //do nothing
    }
    else {
  //    break; //maybe should just be closest, or an issue with the rayTRace
      if (minIntersect == 0) {
        minIntersect = intersectionDistance;
      }
      else {
        double min = Math.min(minIntersect, intersectionDistance);
        minIntersect = min;
      }
    }

  }
  return minIntersect;
}

private double[] getIntersectionPoint(double intersectionDistance, double x, double y, double z,
                                    double ca, double cb, double cc) {
  double[] directionVector = {ca, cb, cc}; //the actual direction vector
  double[] origin = new double[3];
  origin[0] = x/1000;
  origin[1] = y/1000;
  origin[2] = z/1000;
  double distance = intersectionDistance / 1000;
  double[] intersectionPoint = Vector.rayTraceToPointWithDistance(
      directionVector, origin, distance);
  return intersectionPoint;
}


/**
 * Returns the minimum and maximum values of a vertex array
 * given chosen dimension (0 = x, 1 = y, 2 = z).
 *
 * @param dimension 0 = x, 1 = y, 2 = z
 * @param vertices vertices to be examined
 * @return double array, first element minimum, second element maximum
 */
public double[] minMaxVertices(final int dimension, final double[][] vertices) {

  double min = java.lang.Double.POSITIVE_INFINITY;
  double max = java.lang.Double.NEGATIVE_INFINITY;

  for (int i = 0; i < vertices.length; i++) {
    if (vertices[i][dimension] < min) {
      min = vertices[i][dimension];
    }

    if (vertices[i][dimension] > max) {
      max = vertices[i][dimension];
    }
  }

  double[] result = { min, max };

  return result;
}

/**
 * Calculates normal array from index and vertex arrays.
 * Also calculates signed distances of each triangle
 * from the origin.
 */
public void calculateNormals(final boolean rotated) {

  double[][] verticesUsed = verticesEM;
  double[] originDistancesUsed = new double[verticesEM.length];
  double[][] normalsUsed = new double[verticesEM.length][3];

  normalsUsed = new double[indicesEM.length][3];
  originDistancesUsed = new double[indicesEM.length];

  for (int i = 0; i < indicesEM.length; i++) {
    // get the three vertices which this triangle corresponds to.
    double[] point1 = verticesUsed[indicesEM[i][0] - 1];
    double[] point2 = verticesUsed[indicesEM[i][1] - 1];
    double[] point3 = verticesUsed[indicesEM[i][2] - 1];

    // get two vectors which can be used to define our plane.

    double[] vector1 = Vector.vectorBetweenPoints(point1, point2);
    double[] vector2 = Vector.vectorBetweenPoints(point1, point3);

    // get the normal vector between these two vectors.

    double[] normalVector = Vector.normalisedCrossProduct(vector1, vector2);

    // copy this vector into the normals array at the given point.
    System.arraycopy(normalVector, 0, normalsUsed[i], 0, 3);

    double distanceFromOrigin = -(normalVector[0] * point1[0]
        + normalVector[1] * point1[1] + normalVector[2] * point1[2]);

    originDistancesUsed[i] = distanceFromOrigin;
  }

    originDistances = new double[indicesEM.length];
    normals = new double[indicesEM.length][3];

    for (int i = 0; i < normalsUsed.length; i++) {
      System.arraycopy(normalsUsed[i], 0, normals[i], 0, 3);
    }

    System.arraycopy(originDistancesUsed, 0, originDistances, 0,
        indicesEM.length);
  
}


public boolean calculateCrystalOccupancy(final double x, final double y, final double z)
{
  if (normals == null) {
    calculateNormals(false);
  }

  boolean inside = false;

  double[] directionVector = { 0, 0, 1 };
  double[] origin = new double[3];
  origin[0] = x/1000;
  origin[1] = y/1000;
  origin[2] = z/1000;
  //It doesn't work if x = y so need a fudge here... this is horrible.
  if (origin[0] == origin[1]) {
    origin[0] += 0.00001;
  }

  for (int l = 0; l < indicesEM.length; l++) {
    double intersectionDistance = Vector.rayTraceDistance(normals[l],
        directionVector, origin, originDistances[l]);

    Double distanceObject = Double.valueOf(intersectionDistance);

    if (intersectionDistance < 0 || distanceObject.isNaN()
        || distanceObject.isInfinite()) {
      continue;
    }

    double[] intersectionPoint = Vector.rayTraceToPointWithDistance(
        directionVector, origin, intersectionDistance);

    double[][] triangleVertices = new double[3][3];

    // copy vertices referenced by indices into single array for
    // passing onto the polygon inclusion test.
    for (int m = 0; m < 3; m++) {
      System.arraycopy(verticesEM[indicesEM[l][m] - 1], 0, triangleVertices[m],
          0, 3);
    }

    boolean crosses = Vector.polygonInclusionTest(triangleVertices,
        intersectionPoint);

    if (crosses) {
      inside = !inside;
    }
  }
  return inside;
}

//coulombs law function
//I pass in a point from (electron) and to (centre of sample/pixel)
//Calculate the vector and then the unit vector
//Calculate coulomb law (the vector version) - and convert to acceleration with F = ma
//after s is determined I need to work out how long it will take to get there based on the velocity vector (direction + magnitude as speed)
//This time can be used to convert the acceleration vector to a velocity vector
//Combine the two velocity vectors to get a new one 
//this is what I need to return

//Use this velocity to work back to the kinetic energy. This is extra kinetic energy change by the end of the step.
//This will happen in the main void

private double[] adjustVelocityVectorByCharge(double[] electronPosition, double[] chargePosition, double s, double electronEnergy,
                                              double xNorm, double yNorm, double zNorm, CoefCalc coefCalc) {
  double Ke = 8.987551787E+27; // N nm^2 C^-2
  
  //calculate time taken for electron to travel distance s
  
  double csquared = c*c;  // (m/s)^2
  double Vo = electronEnergy * Beam.KEVTOJOULES;
  double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
  double v = Math.pow(betaSquared*csquared, 0.5) *1E9; // nm/s
  double seconds = (1/v) * s;  //seconds to move s nm
  
  //calculate the electron velocity vector (in nm/s)
  double[] electronVelocityVector = new double[3];
  double[] unitVector = {xNorm, yNorm, zNorm};
  for (int i = 0; i < 3; i++) {
    electronVelocityVector[i] = v * unitVector[i]; //nm/s
  }
  
  //calculate the force/acceleration/velocity vector to the charge
  double[] vectorToCharge = Vector.vectorBetweenPoints(electronPosition, chargePosition);  //nm
  double vectorToChargeMagnitude = Vector.vectorMagnitude(vectorToCharge); //nm
  double[] normalisedVectorToCharge = Vector.normaliseVector(vectorToCharge);
  
  //estimate relative permittivity of the medium
  double solventFraction = coefCalc.getSolventFraction();
  if (solventFraction == 0) {
    solventFraction = 0.5;
  }
  double relativeEpsilon = (80*solventFraction) + ((1-solventFraction)*4);
  //vacuum
 // relativeEpsilon = 1;
  
  double forceVectorConstant = Ke *  ((MonteCarloCharge*Beam.ELEMENTARYCHARGE)/(Math.pow(vectorToChargeMagnitude, 2)*relativeEpsilon)); //N or J/m
  double[] forceVector = new double[3];
  double[] accelerationVector = new double[3];
  double[] chargeVelocityVector = new double[3];
  double[] totalVelocityVector = new double[3];
  int bins  = 100; //need this to update mass
  double interval = seconds/bins;
  double relativisticMass = (electronEnergy * Beam.KEVTOJOULES + m*csquared) / csquared;
  totalVelocityVector = electronVelocityVector;
  for (int j = 0; j < 3; j++) {
    forceVector[j] = forceVectorConstant * normalisedVectorToCharge[j]; //N or J/m
    for (int i = 1; i <= bins; i++) {
      accelerationVector[j] = forceVector[j] / relativisticMass; // m/s^2 
      //i think I need an increase in mass here //yeah this mass should be (kinetic energy of electron + m0c^2)/c^2 
    
      chargeVelocityVector[j] = accelerationVector[j] * interval * 1E9; // nm/s
      totalVelocityVector[j] += chargeVelocityVector[j];
      //update mass
      betaSquared = Math.pow(Vector.vectorMagnitude(totalVelocityVector)/1E9,2)/csquared;
      double gamma = 1/Math.pow((1-betaSquared), 0.5);
      electronEnergy = (gamma-1)*m*csquared;
      relativisticMass = (electronEnergy + m*csquared) / csquared;
    }
  }
  /*
  if (MonteCarloCharge > 1E-14  && vectorToChargeMagnitude < 150) {
    System.out.println("test");
  }
  */
  
  //error fixer
  double velocity = Vector.vectorMagnitude(totalVelocityVector)/1E9;
  if(velocity > c) {
    double multiple = velocity/c;
    for (int j = 0; j < 3; j++) {
      totalVelocityVector[j] = (totalVelocityVector[j]/multiple)-1;
    }
  }
  return totalVelocityVector;
}


/**
 * Vector class containing magical vector methods
 * like cross products and magnitudes.
 *
 * @author magd3052
 */
private static class Vector {
  /**
   * Returns magnitude of 3D vector.
   *
   * @param vector 3d coordinates of vector
   * @return magnitude scalar.
   */
  public static double vectorMagnitude(final double[] vector) {
    double squaredDistance = Math.pow(vector[0], 2) + Math.pow(vector[1], 2)
        + Math.pow(vector[2], 2);

    double distance = Math.sqrt(squaredDistance);

    return distance;
  }

  /**
   * returns 3D vector between FROM and TO points.
   *
   * @param from from point
   * @param to to point
   * @return vector between points.
   */
  public static double[] vectorBetweenPoints(final double[] from,
      final double[] to) {
    double[] newVector = new double[3];

    for (int i = 0; i < 3; i++) {
      newVector[i] = to[i] - from[i];
    }

    return newVector;
  }
  
  public static double[] normaliseVector(final double[] vector) {
    double[] newVector = new double[3];
    double magnitude = vectorMagnitude(vector);
    
    for (int i = 0; i < 3; i++) {
      newVector[i] = vector[i]/magnitude;
    }
    
    return newVector;
  }

  /**
   * returns 3D cross-product between two vectors.
   *
   * @param vector1 vector1
   * @param vector2 vector2
   * @return cross product
   */
  public static double[] crossProduct(final double[] vector1,
      final double[] vector2) {
    double[] newVector = new double[3];

    newVector[0] = vector1[1] * vector2[2] - vector1[2] * vector2[1];
    newVector[1] = vector1[2] * vector2[0] - vector1[0] * vector2[2];
    newVector[2] = vector1[0] * vector2[1] - vector1[1] * vector2[0];

    return newVector;
  }

  /**
   * returns 3D cross product with magnitude set to 1 between
   * two vectors.
   *
   * @param vector1 vector1
   * @param vector2 vector2
   * @return normalised cross product
   */
  public static double[] normalisedCrossProduct(final double[] vector1,
      final double[] vector2) {
    double[] newVector = crossProduct(vector1, vector2);
    double magnitude = vectorMagnitude(newVector);

    for (int i = 0; i < 3; i++) {
      newVector[i] /= magnitude;
    }

    return newVector;
  }

  /**
   * returns dot product between two 3D vectors.
   *
   * @param vector1 vector1
   * @param vector2 vector2
   * @return dot product
   */
  public static double dotProduct(final double[] vector1,
      final double[] vector2) {
    double dotProduct = 0;

    for (int i = 0; i < 3; i++) {
      dotProduct += vector1[i] * vector2[i];
    }

    return dotProduct;
  }

  /**
   * Ray trace from a point to a plane via a direction vector,
   * find the intersection between the direction vector and the
   * plane and return this point.
   *
   * @param normalUnitVector normal vector with magnitude 1
   * @param directionVector direction vector of any magnitude
   * @param origin point from which ray is traced (i.e. voxel coordinate)
   * @param planeDistance distance of plane from true origin (0, 0, 0)
   * @return intersection point between plane and direction vector
   */
  @SuppressWarnings("unused")
  public static double[] rayTraceToPoint(final double[] normalUnitVector,
      final double[] directionVector, final double[] origin,
      final double planeDistance) {
    double t = rayTraceDistance(normalUnitVector, directionVector, origin,
        planeDistance);

    double[] point = new double[3];

    for (int i = 0; i < 3; i++) {
      point[i] = origin[i] + t * directionVector[i];
    }

    return point;
  }

  /**
   * Ray trace - find intersection of direction vector from point
   * with plane from already-known distance t.
   *
   * @param directionVector direction vector
   * @param origin point from which ray is traced
   * @param t distance of origin to plane along direction vector
   * @return point of intersection
   */
  public static double[] rayTraceToPointWithDistance(
      final double[] directionVector,
      final double[] origin,
      final double t) {
    double[] point = new double[3];

    for (int i = 0; i < 3; i++) {
      point[i] = origin[i] + t * directionVector[i];
    }

    return point;
  }

  /**
   * Ray trace from a point to a plane via a direction vector,
   * find the signed distance between the direction vector and
   * the plane and return this point.
   *
   * @param normalUnitVector normal vector with magnitude 1
   * @param directionVector direction vector of any magnitude
   * @param origin point from which ray is traced (i.e. voxel coordinate)
   * @param planeDistance distance of plane from true origin (0, 0, 0)
   * @return signed distance between direction vector and plane
   */
  public static double rayTraceDistance(final double[] normalUnitVector,
      final double[] directionVector, final double[] origin,
      final double planeDistance) {

    double originNormalDotProduct = dotProduct(origin, normalUnitVector);
    double directionNormalDotProduct = dotProduct(directionVector,
        normalUnitVector);

    double t = -(originNormalDotProduct + planeDistance)
        / directionNormalDotProduct;

    return t;
  }

  /**
   * Original C code
   * http://www.ecse.rpi.edu/~wrf/Research/Short_Notes/pnpoly.html
   * Takes an array of vertices of a polygon and determines whether a point
   * is contained within the polygon or not. Ignores the z axis at the
   * moment.
   *
   * @param vertices array of 3D vertices
   * @param point point to test inclusion - must be in same plane
   *          as vertices
   * @return boolean value - in polygon or not in polygon.
   */
  public static boolean polygonInclusionTest(final double[][] vertices,
      final double[] point) {
    boolean c = false;

    for (int i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
      if (((vertices[i][1] > point[1]) != (vertices[j][1] > point[1]))
          && (point[0] < (vertices[j][0] - vertices[i][0])
              * (point[1] - vertices[i][1])
              / (vertices[j][1] - vertices[i][1]) + vertices[i][0])) {
        c = !c;
      }
    }

    return c;
  }
}

private void testingXFELQuick(Beam beam, CoefCalc coefcalc) {
  double m = 9.10938356E-31; // in Kg
  double c = 299792458;
  double csquared = c*c;  // (m/s)^2
  
  double onefsTotDose = 193.7; //this would be 1E11 photons in 10fs
  double beamEnergy = beam.getPhotonEnergy();
  double peBinding = 0.48;
  double electronEnergy = beamEnergy - peBinding;
  double photonDosePerfs = (peBinding/beamEnergy)*onefsTotDose;
  
  //just a test
  //pulse energy of 1.5mJ 
  double pulseEn = 2.11E-3; //J
  double energyPerPhoton = beam.getPhotonEnergy()*Beam.KEVTOJOULES;
  double numberOfPhotons = pulseEn/energyPerPhoton;
  
  //so for 2fs
  int time = 20;  // fs
  double stoppingPower = 0;
  double photonDose = photonDosePerfs * time;
  double electronDose = 0;
  for (int i = 1; i < time; i++) {  //so i is fs since first pe produced (time - 1)
    stoppingPower = coefcalc.getStoppingPower(electronEnergy, false);
    //get the speed
    double Vo = electronEnergy * Beam.KEVTOJOULES;
    double betaSquared = 1- Math.pow(m*csquared/(Vo + m*csquared), 2);
    double v = Math.pow(betaSquared*csquared, 0.5) * 1E9 / 1E15; //nm/fs
    double distanceMoved = (v*1);
    double energyDeposited = stoppingPower * distanceMoved;
    double doseDeposited = (energyDeposited/beamEnergy)*onefsTotDose;
    
    electronDose += doseDeposited * (time-i);
    electronEnergy -= energyDeposited;
    if (electronEnergy < 0.05) {
      break;
    }
  }
  double totDose = photonDose + electronDose;
  System.out.println(totDose);
  
  //so the photon dose is contentious because of outrunning Auger, need to consider this
  //The elctron dose is largely not inner shell but fraction that is and outrunning Auger should be considered
  //Need to consider photoelectron escape as well as this could be huge
  //I think a Monte Carlo simulation and multiplying up might work, need Monte Carlo because of the timescale I think 
  
  //I should be able to predict pulse lengths at which damage is seen for a given beam energy, sample composition and stuff
  //Should also be able to use elastic scattering cross sections to advise on beam energy and stuff 
}

private void MonteCarloXFEL() {
  //this will be where my Monte Carlo simulation for XFELs is set up and I can see what I can learn from this
}

}
