/** "Here's an initializer, here's an input file. Good luck and God's Speed." */
grammar Inputfile;

@header {
package se.raddo.raddose3D.parser;
import se.raddo.raddose3D.*;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
}

@lexer::header {
package se.raddo.raddose3D.parser;
}

@members {
    private BeamFactory beamFactory = null;
    private CrystalFactory crystalFactory = null;
    private Initializer raddoseInitializer = null;
    private Vector<String> parsingErrors = new Vector<String>();

	public void setInitializer(Initializer i) {
		this.raddoseInitializer = i;
	}
	public void setBeamFactory(BeamFactory bf) {
		this.beamFactory = bf;
	}
	public void setCrystalFactory(CrystalFactory cf) {
		this.crystalFactory = cf;
	}
    public Vector<String> getErrors() {
        Vector<String> fetchedErrors = parsingErrors;
        parsingErrors = new Vector<String>();
        return fetchedErrors;
    }
    public void emitErrorMessage(String msg) {
        parsingErrors.add(msg);
    }
}
	
configfile:
          ( a=crystal { raddoseInitializer.setCrystal($a.cObj); }
          | b=wedge   { raddoseInitializer.exposeWedge($b.wObj); }
          | c=beam    { raddoseInitializer.setBeam($c.bObj); }
          )* EOF;

// ------------------------------------------------------------------

crystal returns [Crystal cObj]
scope {
	String 			crystalType;
	int 			crystalCoefCalc;
	CoefCalc		crystalCoefCalcClass;
	int			crystalDdm;
	DDM			crystalDdmClass;	
	int			crystalContainerMaterial;
	Container		crystalContainerMaterialClass;
	Double			gammaParam;
	Double			b0Param;
	Double			betaParam;
	String			containerMixture;
	Double			containerThickness;
	Double			containerDensity;
	List<String>	containerElementNames;
	List<Double>	containerElementNums;		
	String			pdb;
	String                  cif;
	String			seqFile;
	Double			proteinConc;
	Double			cellA;
	Double			cellB;
	Double			cellC;
	Double			cellAl;
	Double			cellBe;
	Double			cellGa;
	int 			numMon;
	int 			numRes; 
	int 			numRNA;
	int 			numDNA;
	int                     numCarb;
	List<String>    smallMoleAtomNames;
	List<Double>    smallMoleAtomNums; 
	List<String>	heavyProteinAtomNames;
	List<Double>	heavyProteinAtomNums;
	List<String>	heavySolutionConcNames;
	List<Double>	heavySolutionConcNums; 
	List<String>	cryoSolutionMolecule;
	List<Double>	cryoSolutionConc;
	Double 			solFrac;
	String                 oilBased;
	List<String>         oilNames;
	List<Double>          oilNums;
	Double 		      oilDensity;
	String 	           calcSurrounding;
	long              simElectrons;
    HashMap<Object, Object> crystalProperties;
	}
@init { 
$crystal::crystalCoefCalc = 2; // 0 = error, 1 = Simple, 2 = DEFAULT, 3 = RDV2, 4 = PDB, 5 = SAXS
		$crystal::crystalProperties = new HashMap<Object, Object>();
}
@after { 
if ($crystal::crystalCoefCalc == 1) {
  $crystal::crystalCoefCalcClass = new CoefCalcAverage();
}
if ($crystal::crystalCoefCalc == 2)
{
   if ($crystal::oilDensity == null){
      $crystal::oilDensity = 0.0 ;
   }
  $crystal::crystalCoefCalcClass = new CoefCalcFromParams($crystal::cellA, $crystal::cellB, $crystal::cellC, $crystal::cellAl, $crystal::cellBe, $crystal::cellGa,
  													$crystal::numMon, $crystal::numRes, $crystal::numRNA, $crystal::numDNA,
  													$crystal::heavyProteinAtomNames, $crystal::heavyProteinAtomNums,
  													$crystal::heavySolutionConcNames, $crystal::heavySolutionConcNums,
  													$crystal::cryoSolutionMolecule, $crystal::cryoSolutionConc,
  													$crystal::solFrac, $crystal::oilBased, 	$crystal::calcSurrounding,
  													$crystal::numCarb, $crystal::oilNames, $crystal::oilNums,  $crystal::oilDensity, 
  													$crystal::simElectrons);
}

if ($crystal::crystalCoefCalc == 3) {
  $crystal::crystalCoefCalcClass = new CoefCalcRaddose($crystal::cellA, $crystal::cellB, $crystal::cellC, $crystal::cellAl, $crystal::cellBe, $crystal::cellGa,
  													$crystal::numMon, $crystal::numRes, $crystal::numRNA, $crystal::numDNA,
  													$crystal::heavyProteinAtomNames, $crystal::heavyProteinAtomNums,
  													$crystal::heavySolutionConcNames, $crystal::heavySolutionConcNums,
  													$crystal::solFrac);
}

if ($crystal::crystalCoefCalc == 4)
{
   if ($crystal::oilDensity == null){
      $crystal::oilDensity = 0.0 ;
   }
  if ($crystal::heavySolutionConcNames != null)
  	$crystal::crystalCoefCalcClass = new CoefCalcFromPDB($crystal::pdb, $crystal::heavySolutionConcNames, $crystal::heavySolutionConcNums, $crystal::cryoSolutionMolecule, $crystal::cryoSolutionConc, $crystal::oilBased, 	$crystal::calcSurrounding, $crystal::oilNames, $crystal::oilNums, $crystal::oilDensity, $crystal::simElectrons);
  else
	$crystal::crystalCoefCalcClass = new CoefCalcFromPDB($crystal::pdb, $crystal::cryoSolutionMolecule, $crystal::cryoSolutionConc, $crystal::oilBased, 	$crystal::calcSurrounding, $crystal::oilNames, $crystal::oilNums, $crystal::oilDensity, $crystal::simElectrons);
  													  													
}

if ($crystal::crystalCoefCalc == 5)
{
      if ($crystal::containerDensity != null){
         $crystal::oilDensity = $crystal::containerDensity ;
      }
      else{
         $crystal::oilDensity = 0.0 ;
      }
      if ($crystal::oilNames == null){
         if ($crystal::containerElementNames != null){
            $crystal::oilNames = $crystal::containerElementNames;
            $crystal::oilNums = $crystal::containerElementNums;
      }
   }
  $crystal::crystalCoefCalcClass = new CoefCalcSAXS($crystal::cellA, $crystal::cellB, $crystal::cellC, $crystal::cellAl, $crystal::cellBe, $crystal::cellGa,
  													$crystal::numRes, $crystal::numRNA, $crystal::numDNA,
  													$crystal::heavyProteinAtomNames, $crystal::heavyProteinAtomNums,
  													$crystal::heavySolutionConcNames, $crystal::heavySolutionConcNums,
  													$crystal::solFrac, $crystal::proteinConc, 
  													$crystal::cryoSolutionMolecule, $crystal::cryoSolutionConc,
  													$crystal::oilBased, 	$crystal::calcSurrounding,
  													$crystal::numCarb, $crystal::oilNames, $crystal::oilNums, $crystal::oilDensity);
}

if ($crystal::crystalCoefCalc == 6)
{
   if ($crystal::oilDensity == null){
      $crystal::oilDensity = 0.0 ;
   }
  $crystal::crystalCoefCalcClass = new CoefCalcFromSequence($crystal::cellA, $crystal::cellB, $crystal::cellC, $crystal::cellAl, $crystal::cellBe, $crystal::cellGa,
  													$crystal::numMon,
  													$crystal::heavyProteinAtomNames, $crystal::heavyProteinAtomNums,
  													$crystal::heavySolutionConcNames, $crystal::heavySolutionConcNums,
  													$crystal::solFrac, $crystal::seqFile,
  													$crystal::cryoSolutionMolecule, $crystal::cryoSolutionConc,
  													$crystal::oilBased, 	$crystal::calcSurrounding,
  													$crystal::numCarb, $crystal::oilNames, $crystal::oilNums, $crystal::oilDensity);
}

if ($crystal::crystalCoefCalc == 7)
{
   if ($crystal::oilDensity == null){
      if ($crystal::containerDensity != null){
         $crystal::oilDensity = $crystal::containerDensity ;
      }
      else{
         $crystal::oilDensity = 0.0 ;
      }
   }
   if ($crystal::oilNames == null){
      if ($crystal::containerElementNames != null){
         $crystal::oilNames = $crystal::containerElementNames;
         $crystal::oilNums = $crystal::containerElementNums;
      }
   }
   
  $crystal::crystalCoefCalcClass = new CoefCalcFromSequenceSAXS($crystal::cellA, $crystal::cellB, $crystal::cellC, $crystal::cellAl, $crystal::cellBe, $crystal::cellGa,
  													$crystal::heavyProteinAtomNames, $crystal::heavyProteinAtomNums,
  													$crystal::heavySolutionConcNames, $crystal::heavySolutionConcNums,
  													$crystal::solFrac, $crystal::proteinConc, $crystal::seqFile, 		
  													$crystal::cryoSolutionMolecule, $crystal::cryoSolutionConc,					
  													$crystal::oilBased, 	$crystal::calcSurrounding,
  													$crystal::numCarb, $crystal::oilNames, $crystal::oilNums, $crystal::oilDensity);
}

if ($crystal::crystalCoefCalc == 8)
{
   if ($crystal::oilDensity == null){
      $crystal::oilDensity = 0.0 ;
   }
  $crystal::crystalCoefCalcClass = new CoefCalcSmallMolecules($crystal::cellA, $crystal::cellB, $crystal::cellC, $crystal::cellAl, $crystal::cellBe, $crystal::cellGa,
   													$crystal::numMon,
  													$crystal::smallMoleAtomNames, $crystal::smallMoleAtomNums,
  													$crystal::heavySolutionConcNames, $crystal::heavySolutionConcNums,
  													$crystal::solFrac,
  													$crystal::cryoSolutionMolecule, $crystal::cryoSolutionConc,
  													$crystal::oilBased, 	$crystal::calcSurrounding,
  													$crystal::oilNames, $crystal::oilNums, $crystal::oilDensity);
}

if ($crystal::crystalCoefCalc == 9)
{
   $crystal::crystalCoefCalcClass = new CoefCalcFromCIF($crystal::cif);												  													
}



$crystal::crystalProperties.put(Crystal.CRYSTAL_COEFCALC, $crystal::crystalCoefCalcClass);

if ($crystal::crystalDdm == 1)
{
	$crystal::crystalDdmClass = new DDMSimple();
}

if ($crystal::crystalDdm == 2)
{
	$crystal::crystalDdmClass = new DDMLinear();
}

if ($crystal::crystalDdm == 3)
{
	$crystal::crystalDdmClass = new DDMLeal($crystal::gammaParam, $crystal::b0Param, $crystal::betaParam);
}

if ($crystal::crystalDdm == 4)
{
	$crystal::crystalDdmClass = new DDMBfactor($crystal::gammaParam, $crystal::b0Param, $crystal::betaParam);
}

$crystal::crystalProperties.put(Crystal.CRYSTAL_DDM, $crystal::crystalDdmClass);

if ($crystal::crystalContainerMaterial == 1)
{
	$crystal::crystalContainerMaterialClass = new ContainerTransparent();
}

if ($crystal::crystalContainerMaterial == 2)
{
	$crystal::crystalContainerMaterialClass = new ContainerMixture($crystal::containerThickness, $crystal::containerDensity, $crystal::containerMixture);
}

if ($crystal::crystalContainerMaterial == 3)
{
	$crystal::crystalContainerMaterialClass = new ContainerElemental($crystal::containerThickness, $crystal::containerDensity, $crystal::containerElementNames,
													$crystal::containerElementNums);
}

$crystal::crystalProperties.put(Crystal.CRYSTAL_CONTAINER, $crystal::crystalContainerMaterialClass);


$cObj = crystalFactory.createCrystal($crystal::crystalType, $crystal::crystalProperties);
}
	: CRYSTAL crystalLine+ ;
CRYSTAL	: ('C'|'c')('R'|'r')('Y'|'y')('S'|'s')('T'|'t')('A'|'a')('L'|'l') ;

crystalLine 
	: a=crystalType			{ $crystal::crystalType 			= $a.crystalType; }
	| b=crystalDDM 			{ $crystal::crystalDdm 				= $b.value; }
	| c=crystalCoefcalc		{ $crystal::crystalCoefCalc			= $c.value; }
	| d=crystalDim			{ if ($d.properties != null) {
							   $crystal::crystalProperties.putAll($d.properties);
							  }; }
	| e=crystalPPM			{ $crystal::crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, $e.ppm); }
	| f=crystalAngP 		{ $crystal::crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, $f.value); }
	| g=crystalAngL			{ $crystal::crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, $g.value); }
	| h=crystalDecayParam		{ $crystal::gammaParam 					= $h.gammaParam; 
	                           			  $crystal::b0Param 					= $h.b0Param; 
	                           			  $crystal::betaParam 					= $h.betaParam; }
	| i=containerThickness		{ $crystal::containerThickness 				= $i.value; }
	| j=containerDensity		{ $crystal::containerDensity				= $j.value; }
	| k=crystalContainerMaterial	{ $crystal::crystalContainerMaterial			= $k.value; }
	| l=containerMaterialMixture	{ $crystal::containerMixture 				= $l.value; }
	| m=unitcell			{ $crystal::cellA					= $m.dimA; 
   							  $crystal::cellB 					= $m.dimB; 	
							  $crystal::cellC 					= $m.dimC;	
							  $crystal::cellAl					= $m.angA;
   							  $crystal::cellBe					= $m.angB; 	
							  $crystal::cellGa 					= $m.angC;	}
	| n=nummonomers 		{ $crystal::numMon					= $n.value;	}
	| o=numresidues 		{ $crystal::numRes					= $o.value;	}
	| p=numRNA 				{ $crystal::numRNA					= $p.value;	}
	| q=numDNA 				{ $crystal::numDNA					= $q.value;	}
	| qa=numcarb 				{ $crystal::numCarb					= $qa.value;	}
	| r=heavyProteinAtoms	{ $crystal::heavyProteinAtomNames	= $r.names;
							  $crystal::heavyProteinAtomNums	= $r.num;	}
	| s=heavySolutionConc	{ $crystal::heavySolutionConcNames	= $s.names;
							  $crystal::heavySolutionConcNums	= $s.num;	}
	| t=solventFraction		{ $crystal::solFrac					= $t.solFrac; }
	| u=pdb					{ $crystal::pdb						= $u.pdb; }
	| v=wireframeType			{ $crystal::crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_TYPE, $v.value); }
	| w=modelFile				{ $crystal::crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_FILE, $w.value); }
	| x=calculatePEEscape		{ $crystal::crystalProperties.put(Crystal.CRYSTAL_ELECTRON_ESCAPE, $x.value); }
	| y=proteinConcentration	{ $crystal::proteinConc					= $y.proteinConc;}
	| z=containerMaterialElements	{ $crystal::containerElementNames	= $z.names;
							  $crystal::containerElementNums	= $z.num;	}
	| aa=sequenceFile 		{ $crystal::seqFile 		= $aa.value; }
	| ab=smallMoleAtoms             {$crystal::smallMoleAtomNames   = $ab.names;
							$crystal::smallMoleAtomNums		= $ab.num;	}
	| ac=cif					{ $crystal::cif						= $ac.cif; }

	| bb=calculateFLEscape		{ $crystal::crystalProperties.put(Crystal.CRYSTAL_FLUORESCENT_ESCAPE, $bb.value); }
	| cc=flResolution 		{ $crystal::crystalProperties.put(Crystal.CRYSTAL_FLUORESCENT_RESOLUTION, $cc.value);}
	| dd=peResolution 		{ $crystal::crystalProperties.put(Crystal.CRYSTAL_PHOTOELECTRON_RESOLUTION, $dd.value);}

	| ee=surroundingHeavyConc	        { $crystal::cryoSolutionMolecule	= $ee.names;
							  $crystal::cryoSolutionConc	= $ee.num;	}
	| ff=oilBased	                { $crystal::oilBased	= $ff.value;  }
	| gg=goniometerAxis		{ $crystal::crystalProperties.put(Crystal.CRYSTAL_GONIOMETER_AXIS, $gg.value); }
	| hh=calcSurrounding                { $crystal::calcSurrounding	= $hh.value;  }
	| ii=oilElements                { $crystal::oilNames    = $ii.names;  
		                	         $crystal::oilNums	= $ii.num;  }
	| jj=oilDensity	                { $crystal::oilDensity			= $jj.oildens;  }
	| kk=program	                { $crystal::crystalProperties.put(Crystal.CRYSTAL_PROGRAM, $kk.value); }
	| ll=simElectrons		{ $crystal::simElectrons		= $ll.simel; }
	| mm=runs	                { $crystal::crystalProperties.put(Crystal.CRYSTAL_RUNS, $mm.value); }
	| nn=surroundingThickness			{ if ($nn.properties != null) {
							   $crystal::crystalProperties.putAll($nn.properties);
							  }; }
        | pp=polarisationDirection	{ $crystal::crystalProperties.put(Crystal.CRYSTAL_POLARISATION_DIRECTION, $pp.value); }					 
							
	;

	
crystalType returns [String crystalType]
	: TYPE e=STRING {$crystalType = $e.text;};
TYPE : ('T'|'t')('Y'|'y')('P'|'p')('E'|'e') ;

crystalDDM returns [int value]
	: ( DIFFRACTIONDECAYMODEL | DDM ) e=crystalDDMKeyword { $value = $e.value; };
DIFFRACTIONDECAYMODEL : ('D'|'d')('I'|'i')('F'|'f')('F'|'f')('R'|'r')('A'|'a')('C'|'c')('T'|'t')('I'|'i')('O'|'o')('N'|'n')('D'|'d')('E'|'e')('C'|'c')('A'|'a')('Y'|'y')('M'|'m')('O'|'o')('D'|'d')('E'|'e')('L'|'l') ;
DDM : ('D'|'d')('D'|'d')('M'|'m') ;
crystalDDMKeyword returns [int value]
	: SIMPLE { $value = 1; }
	| LINEAR { $value = 2; }
	| LEAL   { $value = 3; }
	| BFACTOR   { $value = 4; }
	;
SIMPLE : ('S'|'s')('I'|'i')('M'|'m')('P'|'p')('L'|'l')('E'|'e') ;
LINEAR : ('L'|'l')('I'|'i')('N'|'n')('E'|'e')('A'|'a')('R'|'r') ;
LEAL : ('L'|'l')('E'|'e')('A'|'a')('L'|'l') ;
BFACTOR : ('B'|'b')('F'|'f')('A'|'a')('C'|'c')('T'|'t')('O'|'o')('R'|'r') ;

crystalDecayParam returns [Double gammaParam, Double b0Param, Double betaParam]
	: DECAYPARAM a=FLOAT b=FLOAT c=FLOAT {$gammaParam = Double.parseDouble($a.text); $b0Param = Double.parseDouble($b.text); $betaParam = Double.parseDouble($c.text);};
DECAYPARAM  : ('D'|'d')('E'|'e')('C'|'c')('A'|'a')('Y'|'y')('P'|'p')('A'|'a')('R'|'r')('A'|'a')('M'|'m');

crystalCoefcalc returns [int value]
	: ABSCOEFCALC a=crystalCoefcalcKeyword  { $value = $a.value; };
ABSCOEFCALC : (('A'|'a')('B'|'b')('S'|'s'))?('C'|'c')('O'|'o')('E'|'e')('F'|'f')('C'|'c')('A'|'a')('L'|'l')('C'|'c');
crystalCoefcalcKeyword returns [int value]
	: DUMMY   	{ $value = 1;}
	| AVERAGE 	{ $value = 1;}
	| DEFAULT 	{ $value = 2;}
	| RDJAVA	{ $value = 2;}
	| RDFORTAN	{ $value = 3;}
	| PDB	  	{ $value = 4;}
	| SAXS		{ $value = 5;}
	| SEQUENCE	{ $value = 6;}
	| SAXSSEQ	{ $value = 7;}
	| SMALLMOLE     { $value = 8;}
	| CIF	  	{ $value = 9;}
	;
DUMMY : ('D'|'d')('U'|'u')('M'|'m')('M'|'m')('Y'|'y') ;
DEFAULT	: ('D'|'d')('E'|'e')('F'|'f')('A'|'a')('U'|'u')('L'|'l')('T'|'t');
AVERAGE : ('A'|'a')('V'|'v')('E'|'e')('R'|'r')('A'|'a')('G'|'g')('E'|'e') ;
RDFORTAN : ('R'|'r')('D'|'d')('V'|'v')('2'|'3')? ;
RDJAVA : ('R'|'r')('D'|'d')('3')('D'|'d')? ;
PDB : ('E'|'e')('X'|'x')('P'|'p');
SAXS : ('S'|'s')('A'|'a')('X'|'x')('S'|'s');
SEQUENCE : ('S'|'s')('E'|'e')('Q'|'q')('U'|'u')('E'|'e')('N'|'n')('C'|'c')('E'|'e');
SAXSSEQ : ('S'|'s')('A'|'a')('X'|'x')('S'|'s')('S'|'s')('E'|'e')('Q'|'q');
SMALLMOLE : ('S'|'s')('M'|'m')('A'|'a')('L'|'l')('L'|'l')('M'|'m')('O'|'o')('L'|'l')('E'|'e');
CIF : ('E'|'e')('X'|'x')('P'|'p')('S'|'s')('M'|'m');

crystalDim returns [Map<Object, Object> properties]
@init { 
		$properties = new HashMap<Object, Object>();
}	: DIMENSION
	(
      a=FLOAT b=FLOAT c=FLOAT { $properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble($a.text));
                                $properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble($b.text));
                                $properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble($c.text)); }
    | e=FLOAT f=FLOAT{ $properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble($e.text));
    		       $properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble($f.text)); }	
    | d=FLOAT { $properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble($d.text)); }
	) ;
DIMENSION : ('D'|'d')('I'|'i')('M'|'m')('E'|'e')('N'|'n')('S'|'s')('I'|'i')('O'|'o')('N'|'n')('S'|'s')? ;

crystalAngP returns [double value]
	: ANGLEP a=FLOAT {$value = Double.parseDouble($a.text);}
		;
ANGLEP : ('A'|'a')('N'|'n')('G'|'g')('L'|'l')('E'|'e')('P'|'p') ;

crystalAngL returns [double value]
	: ANGLEL a=FLOAT {$value = Double.parseDouble($a.text);}
	;
ANGLEL : ('A'|'a')('N'|'n')('G'|'g')('L'|'l')('E'|'e')('L'|'l') ;

crystalPPM returns [double ppm]
	: PIXELSPERMICRON FLOAT {$ppm = Double.parseDouble($FLOAT.text);};
PIXELSPERMICRON : ('P'|'p')('I'|'i')('X'|'x')('E'|'e')('L'|'l')('S'|'s')('P'|'p')('E'|'e')('R'|'r')('M'|'m')('I'|'i')('C'|'c')('R'|'r')('O'|'o')('N'|'n') ;
	
unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC]
	: UNITCELL a=FLOAT b=FLOAT c=FLOAT
		{$dimA = Double.parseDouble($a.text);
		 $dimB = Double.parseDouble($b.text);
		 $dimC = Double.parseDouble($c.text); }
      (al=FLOAT be=FLOAT ga=FLOAT
       {$angA = Double.parseDouble($al.text);
	 	$angB = Double.parseDouble($be.text);
		$angC = Double.parseDouble($ga.text); }
		)? 
	;
UNITCELL : ('U'|'u')('N'|'n')('I'|'i')('T'|'t')('C'|'c')('E'|'e')('L'|'l')('L'|'l') ;


surroundingThickness returns [Map<Object, Object> properties]
@init { 
		$properties = new HashMap<Object, Object>();
}	: SURROUNDINGTHICKNESS 
	(	
	a=FLOAT b=FLOAT c=FLOAT { $properties.put(Crystal.SURROUNDING_X, Double.parseDouble($a.text));
                                $properties.put(Crystal.SURROUNDING_Y, Double.parseDouble($b.text));
                                $properties.put(Crystal.SURROUNDING_Z, Double.parseDouble($c.text)); }
	);
SURROUNDINGTHICKNESS : ('S'|'s')('U'|'u')('R'|'r')('R'|'r')('O'|'o')('U'|'u')('N'|'n')('D'|'d')('I'|'i')('N'|'n')('G'|'g')('T'|'t')('H'|'h')('I'|'i')('C'|'c')('K'|'k')('N'|'n')('E'|'e')('S'|'s')('S'|'s');

	
proteinConcentration returns [Double proteinConc]
	: (PROTEINCONCENTRATION | PROTEINCONC) a=FLOAT {$proteinConc = Double.parseDouble($a.text);};
PROTEINCONC: ('P'|'p')('R'|'r')('O'|'o')('T'|'t')('E'|'e')('I'|'i')('N'|'n')('C'|'c')('O'|'o')('N'|'n')('C'|'c') ;
PROTEINCONCENTRATION: ('P'|'p')('R'|'r')('O'|'o')('T'|'t')('E'|'e')('I'|'i')('N'|'n')('C'|'c')('O'|'o')('N'|'n')('C'|'c')('E'|'e')('N'|'n')('T'|'t')('R'|'r')('A'|'a')('T'|'t')('I'|'i')('O'|'o')('N'|'n') ;

nummonomers returns [int value]
	: NUMMONOMERS a=FLOAT {$value = Integer.parseInt($a.text);};
NUMMONOMERS: ('N'|'n')('U'|'u')('M'|'m')('M'|'m')('O'|'o')('N'|'n')('O'|'o')('M'|'m')('E'|'e')('R'|'r')('S'|'s') ;
	
numresidues returns [int value]
	: NUMRESIDUES a=FLOAT {$value = Integer.parseInt($a.text);};
NUMRESIDUES : ('N'|'n')('U'|'u')('M'|'m')('R'|'r')('E'|'e')('S'|'s')('I'|'i')('D'|'d')('U'|'u')('E'|'e')('S'|'s') ;
	
numRNA returns [int value]
	: NUMRNA a=FLOAT {$value = Integer.parseInt($a.text);};
NUMRNA : ('N'|'n')('U'|'u')('M'|'m')('R'|'r')('N'|'n')('A'|'a') ;
	
numDNA returns [int value]
	: NUMDNA a=FLOAT {$value = Integer.parseInt($a.text);};
NUMDNA : ('N'|'n')('U'|'u')('M'|'m')('D'|'d')('N'|'n')('A'|'a') ;

numcarb returns [int value]
	: NUMCARB a=FLOAT {$value = Integer.parseInt($a.text);};
NUMCARB : ('N'|'n')('U'|'u')('M'|'m')('C'|'c')('A'|'a')('R'|'r')('B'|'b') ;

heavyProteinAtoms returns [List<String> names, List<Double> num;]
@init{
$names 	= new ArrayList<String>();
$num	= new ArrayList<Double>();
}
	: PROTEINHEAVYATOMS (a=ELEMENT b=FLOAT {$names.add($a.text); $num.add(Double.parseDouble($b.text)); } )+ ; 	
PROTEINHEAVYATOMS : ('P'|'p')('R'|'r')('O'|'o')('T'|'t')('E'|'e')('I'|'i')('N'|'n')('H'|'h')('E'|'e')('A'|'a')('V'|'v')('Y'|'y')('A'|'a')('T'|'t')('O'|'o')('M'|'m')('S'|'s') ;
ELEMENT : ('A'..'Z' | 'a'..'z')('A'..'Z' | 'a'..'z')? ;

smallMoleAtoms returns [List<String> names, List<Double> num;]
@init{
$names 	= new ArrayList<String>();
$num	= new ArrayList<Double>();
}
	: SMALLMOLEATOMS (a=ELEMENT b=FLOAT {$names.add($a.text); $num.add(Double.parseDouble($b.text)); } )+ ; 	
SMALLMOLEATOMS : ('S'|'s')('M'|'m')('A'|'a')('L'|'l')('L'|'l')('M'|'m')('O'|'o')('L'|'l')('E'|'e')('A'|'a')('T'|'t')('O'|'o')('M'|'m')('S'|'s') ;

heavySolutionConc returns [List<String> names, List<Double> num;]
@init{
$names 	= new ArrayList<String>();
$num	= new ArrayList<Double>();
}
	: SOLVENTHEAVYCONC (a=ELEMENT b=FLOAT {$names.add($a.text); $num.add(Double.parseDouble($b.text)); } )+ ;
SOLVENTHEAVYCONC : ('S'|'s')('O'|'o')('L'|'l')('V'|'v')('E'|'e')('N'|'n')('T'|'t')('H'|'h')('E'|'e')('A'|'a')('V'|'v')('Y'|'y')('C'|'c')('O'|'o')('N'|'n')('C'|'c') ;

solventFraction returns [double solFrac]
	: SOLVENTFRACTION a=FLOAT {$solFrac = Double.parseDouble($a.text);};
SOLVENTFRACTION : ('S'|'s')('O'|'o')('L'|'l')('V'|'v')('E'|'e')('N'|'n')('T'|'t')('F'|'f')('R'|'r')('A'|'a')('C'|'c')('T'|'t')('I'|'i')('O'|'o')('N'|'n') ;

pdb returns [String pdb]
	: PDBNAME (a=STRING|a=FLOAT) {$pdb = $a.text;};
PDBNAME : ('P'|'p')('D'|'d')('B'|'b') ;

cif returns [String cif]
	: CIFNAME a=STRING {$cif = $a.text;};
CIFNAME : ('C'|'c')('I'|'i')('F'|'f') ;

wireframeType returns [String value]
	: WIREFRAMETYPE a=STRING {$value = $a.text;};
WIREFRAMETYPE : ('W'|'w')('I'|'i')('R'|'r')('E'|'e')('F'|'f')('R'|'r')('A'|'a')('M'|'m')('E'|'e')('T'|'t')('Y'|'y'|)('P'|'p')('E'|'e') ;

modelFile returns [String value]
	: MODELFILE a=STRING {$value = $a.text;};
MODELFILE : ('M'|'m')('O'|'o')('D'|'d')('E'|'e')('L'|'l')('F'|'f')('I'|'i')('L'|'l')('E'|'e') ;

calculatePEEscape returns [String value]
	: CALCULATEPEESCAPE a=STRING {$value = $a.text;};
CALCULATEPEESCAPE  
	:	 ('C'|'c')('A'|'a')('L'|'l')('C'|'c')('U'|'u')('L'|'l')('A'|'a')('T'|'t')('E'|'e')('P'|'p')('E'|'e')('E'|'e')('S'|'s')('C'|'c')('A'|'a')('P'|'p')('E'|'e') ;
	
goniometerAxis returns [double value]
	: GONIOMETERAXIS a=FLOAT {$value = Double.parseDouble($a.text);};
GONIOMETERAXIS  
	:	 ('G'|'g')('O'|'o')('N'|'n')('I'|'i')('O'|'o')('M'|'m')('E'|'e')('T'|'t')('E'|'e')('R'|'r')('A'|'a')('X'|'x')('I'|'i')('S'|'s') ;
	
polarisationDirection returns [double value]
	: POLARISATIONDIRECTION a=FLOAT {$value = Double.parseDouble($a.text);};
POLARISATIONDIRECTION  
	:	 ('P'|'p')('O'|'o')('L'|'l')('A'|'a')('R'|'r')('I'|'i')('S'|'s')('A'|'a')('T'|'t')('I'|'i')('O'|'o')('N'|'n')('D'|'d')('I'|'i')('R'|'r')('E'|'e')('C'|'c')('T'|'t')('I'|'i')('O'|'o')('N'|'n') ;
	
crystalContainerMaterial returns [int value]
	: ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e=crystalContainerKeyword { $value = $e.value; };
CONTAINERMATERIALTYPE : ('C'|'c')('O'|'o')('N'|'n')('T'|'t')('A'|'a')('I'|'i')('N'|'n')('E'|'e')('R'|'r')('M'|'m')('A'|'a')('T'|'t')('E'|'e')('R'|'r')('I'|'i')('A'|'a')('L'|'l')('T'|'t')('Y'|'y')('P'|'p')('E'|'e') ;
MATERIALTYPE : ('M'|'m')('A'|'a')('T'|'t')('E'|'e')('R'|'r')('I'|'i')('A'|'a')('L'|'l')('T'|'t')('Y'|'y')('P'|'p')('E'|'e') ;
crystalContainerKeyword returns [int value]
	: NONE 		{ $value = 1; }
	| MIXTURE 	{ $value = 2; }
	| ELEMENTAL 	{ $value = 3; }
	;
NONE 	: ('N'|'n')('O'|'o')('N'|'n')('E'|'e') ;
MIXTURE : ('M'|'m')('I'|'i')('X'|'x')('T'|'t')('U'|'u')('R'|'r')('E'|'e') ;
ELEMENTAL : ('E'|'e')('L'|'l')('E'|'e')('M'|'m')('E'|'e')('N'|'n')('T'|'t')('A'|'a')('L'|'l') ;
	
containerThickness returns[double value]
	: CONTAINERTHICKNESS a=FLOAT {$value = Double.parseDouble($a.text);};
CONTAINERTHICKNESS: ('C'|'c')('O'|'o')('N'|'n')('T'|'t')('A'|'a')('I'|'i')('N'|'n')('E'|'e')('R'|'r')('T'|'t')('H'|'h')('I'|'i')('C'|'c')('K'|'k')('N'|'n')('E'|'e')('S'|'s')('S'|'s') ;

containerMaterialMixture returns [String value]
	: (CONTAINERMATERIALMIXTURE | MATERIALMIXTURE) a=STRING {$value = $a.text;};
CONTAINERMATERIALMIXTURE: ('C'|'c')('O'|'o')('N'|'n')('T'|'t')('A'|'a')('I'|'i')('N'|'n')('E'|'e')('R'|'r')('M'|'m')('A'|'a')('T'|'t')('E'|'e')('R'|'r')('I'|'i')('A'|'a')('L'|'l')('M'|'m')('I'|'i')('X'|'x')('T'|'t')('U'|'u')('R'|'r')('E'|'e') ;
MATERIALMIXTURE: ('M'|'m')('A'|'a')('T'|'t')('E'|'e')('R'|'r')('I'|'i')('A'|'a')('L'|'l')('M'|'m')('I'|'i')('X'|'x')('T'|'t')('U'|'u')('R'|'r')('E'|'e') ;

containerMaterialElements returns[List<String> names, List<Double> num;]
@init{
$names 	= new ArrayList<String>();
$num	= new ArrayList<Double>();
}
	: (CONTAINERMATERIALELEMENTS | MATERIALELEMENTS) (a=ELEMENT b=FLOAT {$names.add($a.text); $num.add(Double.parseDouble($b.text)); } )+ ;
CONTAINERMATERIALELEMENTS: ('C'|'c')('O'|'o')('N'|'n')('T'|'t')('A'|'a')('I'|'i')('N'|'n')('E'|'e')('R'|'r')('M'|'m')('A'|'a')('T'|'t')('E'|'e')('R'|'r')('I'|'i')('A'|'a')('L'|'l')('E'|'e')('L'|'l')('E'|'e')('M'|'m')('E'|'e')('N'|'n')('T'|'t')('S'|'s') ;
MATERIALELEMENTS: ('M'|'m')('A'|'a')('T'|'t')('E'|'e')('R'|'r')('I'|'i')('A'|'a')('L'|'l')('E'|'e')('L'|'l')('E'|'e')('M'|'m')('E'|'e')('N'|'n')('T'|'t')('S'|'s') ;

containerDensity returns[double value]
	: CONTAINERDENSITY a=FLOAT {$value = Double.parseDouble($a.text);};
CONTAINERDENSITY: ('C'|'c')('O'|'o')('N'|'n')('T'|'t')('A'|'a')('I'|'i')('N'|'n')('E'|'e')('R'|'r')('D'|'d')('E'|'e')('N'|'n')('S'|'s')('I'|'i')('T'|'t')('Y'|'y') ;

sequenceFile returns[String value] 
	: (SEQUENCEFILE | SEQFILE) a=STRING {$value = $a.text;};
SEQUENCEFILE : 	('S'|'s')('E'|'e')('Q'|'q')('U'|'u')('E'|'e')('N'|'n')('C'|'c')('E'|'e')('F'|'f')('I'|'i')('L'|'l')('E'|'e');
SEQFILE :	('S'|'s')('E'|'e')('Q'|'q')('F'|'f')('I'|'i')('L'|'l')('E'|'e');

calculateFLEscape returns [String value]
	: CALCULATEFLESCAPE a=STRING {$value = $a.text;};
CALCULATEFLESCAPE  
	:	 ('C'|'c')('A'|'a')('L'|'l')('C'|'c')('U'|'u')('L'|'l')('A'|'a')('T'|'t')('E'|'e')('F'|'f')('L'|'l')('E'|'e')('S'|'s')('C'|'c')('A'|'a')('P'|'p')('E'|'e') ;

flResolution returns [int value]
	: FLRESOLUTION a=FLOAT {$value = Integer.parseInt($a.text);};
FLRESOLUTION : ('F'|'f')('L'|'l')('R'|'r')('E'|'e')('S'|'s')('O'|'o')('L'|'l')('U'|'u')('T'|'t')('I'|'i')('O'|'o')('N'|'n') ;

peResolution returns [int value]
	: PERESOLUTION a=FLOAT {$value = Integer.parseInt($a.text);};
PERESOLUTION : ('P'|'p')('E'|'e')('R'|'r')('E'|'e')('S'|'s')('O'|'o')('L'|'l')('U'|'u')('T'|'t')('I'|'i')('O'|'o')('N'|'n') ;

surroundingHeavyConc returns [List<String> names, List<Double> num;]
@init{
$names 	= new ArrayList<String>();
$num	= new ArrayList<Double>();
}
	: SURROUNDINGHEAVYCONC (a=ELEMENT b=FLOAT {$names.add($a.text); $num.add(Double.parseDouble($b.text)); } )+ ; 	
SURROUNDINGHEAVYCONC : ('S'|'s')('U'|'u')('R'|'r')('R'|'r')('O'|'o')('U'|'u')('N'|'n')('D'|'d')('I'|'i')('N'|'n')('G'|'g')('H'|'h')('E'|'e')('A'|'a')('V'|'v')('Y'|'y')('C'|'c')('O'|'o')('N'|'n')('C'|'c') ;

oilBased returns [String value]
	: DENSITYBASED a=STRING {$value = $a.text;};
DENSITYBASED : ('D'|'d')('E'|'e')('N'|'n')('S'|'s')('I'|'i')('T'|'t')('Y'|'y')('B'|'b')('A'|'a')('S'|'s')('E'|'e')('D'|'d') ;

calcSurrounding returns [String value]
	: CALCSURROUNDING a=STRING {$value = $a.text;};
CALCSURROUNDING : ('C'|'c')('A'|'a')('L'|'l')('C'|'c')('S'|'s')('U'|'u')('R'|'r')('R'|'r')('O'|'o')('U'|'u')('N'|'n')('D'|'d')('I'|'i')('N'|'n')('G'|'g') ;
	

oilElements returns [List<String> names, List<Double> num;]
@init{
$names 	= new ArrayList<String>();
$num	= new ArrayList<Double>();
}
	: SURROUNDINGELEMENTS (a=ELEMENT b=FLOAT {$names.add($a.text); $num.add(Double.parseDouble($b.text)); } )+ ; 	
SURROUNDINGELEMENTS : ('S'|'s')('U'|'u')('R'|'r')('R'|'r')('O'|'o')('U'|'u')('N'|'n')('D'|'d')('I'|'i')('N'|'n')('G'|'g')('E'|'e')('L'|'l')('E'|'e')('M'|'m')('E'|'e')('N'|'n')('T'|'t')('S'|'s') ;

oilDensity returns [double oildens]
	: SURROUNDINGDENSITY a=FLOAT {$oildens = Double.parseDouble($a.text);};
SURROUNDINGDENSITY : ('S'|'s')('U'|'u')('R'|'r')('R'|'r')('O'|'o')('U'|'u')('N'|'n')('D'|'d')('I'|'i')('N'|'n')('G'|'g')('D'|'d')('E'|'e')('N'|'n')('S'|'s')('I'|'i')('T'|'t')('Y'|'y') ;

simElectrons returns [long simel]
	: (SIMELECTRONS | SIMPHOTONS) a=FLOAT {$simel = Long.parseLong($a.text);};
SIMELECTRONS :	('S'|'s')('I'|'i')('M'|'m')('E'|'e')('L'|'l')('E'|'e')('C'|'c')('T'|'t')('R'|'r')('O'|'o')('N'|'n')('S'|'s');
SIMPHOTONS   :	('S'|'s')('I'|'i')('M'|'m')('P'|'p')('H'|'h')('O'|'o')('T'|'t')('O'|'o')('N'|'n')('S'|'s');

program returns [String value]
	: SUBPROGRAM a=STRING {$value = $a.text;};
SUBPROGRAM  
	:	 ('S'|'s')('U'|'u')('B'|'b')('P'|'p')('R'|'r')('O'|'o')('G'|'g')('R'|'r')('A'|'a')('M'|'m') ;

runs returns [int value]
	: RUNS a=FLOAT {$value = Integer.parseInt($a.text);};
RUNS  
	:	 ('R'|'r')('U'|'u')('N'|'n')('S'|'s') ;


// ------------------------------------------------------------------
beam returns [Beam bObj] 
scope {
		String beamType;
		HashMap<Object, Object> beamProperties;
}
@init { 
		$beam::beamProperties = new HashMap<Object, Object>();
}
@after { 
		$bObj = beamFactory.createBeam($beam::beamType, $beam::beamProperties);
}
	: BEAM beamLine+ ;
BEAM : ('B'|'b')('E'|'e')('A'|'a')('M'|'m') ;

beamLine 
	: TYPE a=STRING          { $beam::beamType = $a.text; }
	| b=beamFlux			 { $beam::beamProperties.put(Beam.BEAM_FLUX, $b.flux); }
	| c=beamFWHM			 { $beam::beamProperties.put(Beam.BEAM_FWHM_X, $c.x); 
	                           $beam::beamProperties.put(Beam.BEAM_FWHM_Y, $c.y); }
	| d=beamEnergy			 { $beam::beamProperties.put(Beam.BEAM_ENERGY, $d.energy); }
	| e=beamCollimation		 { if ($e.properties != null) {
							    $beam::beamProperties.putAll($e.properties);
							   } }
	| f=beamFile             { $beam::beamProperties.put(Beam.BEAM_EXTFILE, $f.filename); }
	| g=beamPixelSize        { $beam::beamProperties.putAll($g.properties); }

	| h=beamExposure			 { $beam::beamProperties.put(Beam.BEAM_EXPOSURE, $h.exposure); }
	| i=beamSemiAngle	{ $beam::beamProperties.put(Beam.BEAM_SEMIANGLE, $i.semiAngle);}
	| j=beamApertureRadius	{ $beam::beamProperties.put(Beam.BEAM_APERTURERADIUS, $j.apertureRadius);}
	| k=imageDimensions		{ $beam::beamProperties.put(Beam.IMAGE_X, $k.xImage); 
	                           $beam::beamProperties.put(Beam.IMAGE_Y, $k.yImage); }
	| l=pulseEnergy			 { $beam::beamProperties.put(Beam.PULSE_ENERGY, $l.pulse); }
	| m=energyFWHM             { $beam::beamProperties.put(Beam.ENERGY_FWHM, $m.eFWHM); }

	;

beamFlux returns [Double flux]
	: FLUX a=FLOAT {$flux = Double.parseDouble($a.text);};
FLUX : ('F'|'f')('L'|'l')('U'|'u')('X'|'x') ;

beamExposure returns [Double exposure]
	: EXPOSURE a=FLOAT {$exposure = Double.parseDouble($a.text);};
EXPOSURE : ('E'|'e')('X'|'x')('P'|'p')('O'|'o')('S'|'s')('U'|'u')('R'|'r')('E'|'e') ;

imageDimensions returns [Double xImage, Double yImage]
	: IMAGEDIM a=FLOAT b=FLOAT {$xImage = Double.parseDouble($a.text); $yImage = Double.parseDouble($b.text);};
IMAGEDIM 
	:	 ('I'|'i')('M'|'m')('A'|'a')('G'|'g')('E'|'e')('D'|'d')('I'|'i')('M'|'m') ;

beamFWHM returns [Double x, Double y]
	: FWHM a=FLOAT b=FLOAT {$x = Double.parseDouble($a.text); $y = Double.parseDouble($b.text);};
FWHM : ('F'|'f')('W'|'w')('H'|'h')('M'|'m') ;

beamEnergy returns [Double energy]
	: ENERGY a=FLOAT {$energy = Double.parseDouble($a.text);}
	( KEV )? 
	;
ENERGY : ('E'|'e')('N'|'n')('E'|'e')('R'|'r')('G'|'g')('Y'|'y') ;
KEV : ('K'|'k')('E'|'e')('V'|'v') ;

pulseEnergy returns [Double pulse]
	: PULSEENERGY a=FLOAT {$pulse = Double.parseDouble($a.text);};
PULSEENERGY : ('P'|'p')('U'|'u')('L'|'l')('S'|'s')('E'|'e')('E'|'e')('N'|'n')('E'|'e')('R'|'r')('G'|'g')('Y'|'y') ;


beamSemiAngle returns [Double semiAngle]
	: SEMIANGLE a=FLOAT {$semiAngle = Double.parseDouble($a.text);};
SEMIANGLE 
	:	 ('S'|'s')('E'|'e')('M'|'m')('I'|'i')('A'|'a')('N'|'n')('G'|'g')('L'|'l')('E'|'e');

beamApertureRadius returns [Double apertureRadius]
	: APERTURERADIUS a=FLOAT {$apertureRadius = Double.parseDouble($a.text);};
APERTURERADIUS
	:	('A'|'a')('P'|'p')('E'|'e')('R'|'r')('T'|'t')('U'|'u')('R'|'r')('E'|'e')('R'|'r')('A'|'a')('D'|'d')('I'|'i')('U'|'u')('S'|'s');

beamFile returns [String filename]
	: FILE a=STRING {$filename = $a.text;}
	;
FILE : ('F'|'f')('I'|'i')('L'|'l')('E'|'e') ;

beamPixelSize returns [Map<Object, Object> properties]
    : PIXELSIZE a=FLOAT b=FLOAT {$properties = new HashMap<Object, Object>();
		 $properties.put(Beam.BEAM_PIXSIZE_X, Double.parseDouble($a.text));
		 $properties.put(Beam.BEAM_PIXSIZE_Y, Double.parseDouble($b.text)); }
    ;
PIXELSIZE 
	:	 ('P'|'p')('I'|'i')('X'|'x')('E'|'e')('L'|'l')('S'|'s')('I'|'i')('Z'|'z')('E'|'e');
	
energyFWHM returns [Double eFWHM]
	: ENERGYFWHM a=FLOAT {$eFWHM = Double.parseDouble($a.text);};
ENERGYFWHM 
	: ('E'|'e')('N'|'n')('E'|'e')('R'|'r')('G'|'g')('Y'|'y')('F'|'f')('W'|'w')('H'|'h')('M'|'m'); 


beamCollimation returns [Map<Object, Object> properties]
@init { 
		$properties = new HashMap<Object, Object>();
}
	: COLLIMATION 
	| RECTANGULAR a=FLOAT b=FLOAT { $properties.put(Beam.BEAM_COLL_H, Double.parseDouble($a.text));
	                                $properties.put(Beam.BEAM_COLL_V, Double.parseDouble($b.text)); }
	| CIRCULAR c=FLOAT f=FLOAT { $properties.put(Beam.BEAM_COLL_H, Double.parseDouble($c.text));
	                                $properties.put(Beam.BEAM_COLL_V, Double.parseDouble($f.text)); 
	                                $properties.put(Beam.BEAM_CIRCULAR, "TRUE"); }
	| HORIZONTAL d=FLOAT { $properties.put(Beam.BEAM_COLL_H, Double.parseDouble($d.text)); }
	| VERTICAL e=FLOAT   { $properties.put(Beam.BEAM_COLL_V, Double.parseDouble($e.text)); }
	;
COLLIMATION : ('C'|'c')('O'|'o')('L'|'l')('L'|'l')('I'|'i')('M'|'m')('A'|'a')('T'|'t')('I'|'i')('O'|'o')('N'|'n') ;
RECTANGULAR : ('R'|'r')('E'|'e')('C'|'c')('T'|'t')('A'|'a')('N'|'n')('G'|'g')('U'|'u')('L'|'l')('A'|'a')('R'|'r') ;
CIRCULAR  : ('C'|'c')('I'|'i')('R'|'r')('C'|'c')('U'|'u')('L'|'l')('A'|'a')('R'|'r') ;
HORIZONTAL  : ('H'|'h')('O'|'o')('R'|'r')('I'|'i')('Z'|'z')('O'|'o')('N'|'n')('T'|'t')('A'|'a')('L'|'l') ;
VERTICAL  : ('V'|'v')('E'|'e')('R'|'r')('T'|'t')('I'|'i')('C'|'c')('A'|'a')('L'|'l') ;

// ------------------------------------------------------------------

wedge returns [Wedge wObj]
scope {	
			Double	angRes;
			Double	startAng;
			Double	endAng;
			Double	expTime;
			Double	offsetX;
			Double	offsetY;
			Double	offsetZ;
			Double	translateX;
			Double	translateY;
			Double	translateZ;
			Double	rotationOffset;	
			Double  maxRes
 }
@init { 
}
@after { 
wObj = new Wedge($wedge::angRes, $wedge::startAng, $wedge::endAng, $wedge::expTime, $wedge::offsetX, $wedge::offsetY, $wedge::offsetZ, $wedge::translateX, $wedge::translateY, $wedge::translateZ, $wedge::rotationOffset, $wedge::maxRes);
}
	: WEDGE a=FLOAT b=FLOAT {$wedge::startAng = Double.parseDouble($a.text);
							 $wedge::endAng = Double.parseDouble($b.text); }
	  wedgeLine+;
WEDGE : ('W'|'w')('E'|'e')('D'|'d')('G'|'g')('E'|'e') ;

wedgeLine
	: a=wedgeExposure		{$wedge::expTime	=$a.value; }
	| b=wedgeAngRes			{$wedge::angRes		=$b.res; }
	| c=wedgeStartOffset	{$wedge::offsetX	=$c.x;
						  	 $wedge::offsetY	=$c.y;
						  	 $wedge::offsetZ	=$c.z; } 
	| d=wedgeTranslate		{$wedge::translateX =$d.x;
						  	 $wedge::translateY =$d.y;
						  	 $wedge::translateZ =$d.z; }
	| e=wedgeRotAxBeamOffset {$wedge::rotationOffset =$e.delta; }
	| f=wedgeMaxRes {$wedge::maxRes =$f.res; }
	;

wedgeExposure returns [double value]
	: EXPOSURETIME a=FLOAT {$value = Double.parseDouble($a.text);};
EXPOSURETIME : ('E'|'e')('X'|'x')('P'|'p')('O'|'o')('S'|'s')('U'|'u')('R'|'r')('E'|'e')('T'|'t')('I'|'i')('M'|'m')('E'|'e') ;

wedgeAngRes returns [double res]
	: ANGULARRESOLUTION a=FLOAT {$res = Double.parseDouble($a.text);};
ANGULARRESOLUTION : ('A'|'a')('N'|'n')('G'|'g')('U'|'u')('L'|'l')('A'|'a')('R'|'r')('R'|'r')('E'|'e')('S'|'s')('O'|'o')('L'|'l')('U'|'u')('T'|'t')('I'|'i')('O'|'o')('N'|'n') ;

wedgeStartOffset returns [Double x, Double y, Double z]
	: STARTOFFSET a=FLOAT b=FLOAT {$x = Double.parseDouble($a.text); $y = Double.parseDouble($b.text);}
	              c=FLOAT? {$z = ($c.text == null) ? null : Double.parseDouble($c.text);}
	              ;
STARTOFFSET : ('S'|'s')('T'|'t')('A'|'a')('R'|'r')('T'|'t')('O'|'o')('F'|'f')('F'|'f')('S'|'s')('E'|'e')('T'|'t') ;

wedgeTranslate returns [Double x, Double y, Double z]
	: TRANSLATEPERDEGREE a=FLOAT b=FLOAT {$x = Double.parseDouble($a.text); $y = Double.parseDouble($b.text);}
	                     c=FLOAT? {$z = ($c.text == null) ? null : Double.parseDouble($c.text);}
	                     ;
TRANSLATEPERDEGREE : ('T'|'t')('R'|'r')('A'|'a')('N'|'n')('S'|'s')('L'|'l')('A'|'a')('T'|'t')('E'|'e')('P'|'p')('E'|'e')('R'|'r')('D'|'d')('E'|'e')('G'|'g')('R'|'r')('E'|'e')('E'|'e') ;

wedgeRotAxBeamOffset returns [double delta]
	: ROTAXBEAMOFFSET a=FLOAT {$delta = Double.parseDouble($a.text);};
ROTAXBEAMOFFSET : ('R'|'r')('O'|'o')('T'|'t')('A'|'a')('X'|'x')('B'|'b')('E'|'e')('A'|'a')('M'|'m')('O'|'o')('F'|'f')('F'|'f')('S'|'s')('E'|'e')('T'|'t') ;

wedgeMaxRes returns [double res]
	: MAXRESOLUTION a=FLOAT {$res = Double.parseDouble($a.text);};
MAXRESOLUTION : ('M'|'m')('A'|'a')('X'|'x')('R'|'r')('E'|'e')('S'|'s')('O'|'o')('L'|'l')('U'|'u')('T'|'t')('I'|'i')('O'|'o')('N'|'n') ;

/ ------------------------------------------------------------------

FLOAT 
    : ('+'|'-')?
    ( ('0'..'9')+ 
    | ('0'..'9')+ '.' ('0'..'9')*
    |             '.' ('0'..'9')+ 
    ) EXPONENT? 
    ;
fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

COMMENT
    :   ('#' | '//' | '!') ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

/*STRING
//    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    	: ( '"' ( ~('\\'|'"') )* '"' )
    	| ( '\'' ( ~('\''|'\\') )* '\'' )
    ;
*/
    
STRING
	: ('a'..'z' | 'A'..'Z' | '0'..'9' | '.' | '$' | '-' | '_' | ':' | '\\' | '/')+
	;

/*CHAR:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\''
//    ;
*/

