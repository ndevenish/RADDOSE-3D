// $ANTLR 3.4 C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g 2014-08-15 17:28:34

package se.raddo.raddose3D.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class InputfileLexer extends Lexer {
    public static final int EOF=-1;
    public static final int ABSCOEFCALC=4;
    public static final int ANGLEL=5;
    public static final int ANGLEP=6;
    public static final int ANGULARRESOLUTION=7;
    public static final int AVERAGE=8;
    public static final int BEAM=9;
    public static final int CIRCULAR=10;
    public static final int COLLIMATION=11;
    public static final int COMMENT=12;
    public static final int CRYSTAL=13;
    public static final int DDM=14;
    public static final int DECAYPARAM=15;
    public static final int DEFAULT=16;
    public static final int DIFFRACTIONDECAYMODEL=17;
    public static final int DIMENSION=18;
    public static final int DUMMY=19;
    public static final int ELEMENT=20;
    public static final int ENERGY=21;
    public static final int EXPONENT=22;
    public static final int EXPOSURETIME=23;
    public static final int FILE=24;
    public static final int FLOAT=25;
    public static final int FLUX=26;
    public static final int FWHM=27;
    public static final int HORIZONTAL=28;
    public static final int KEV=29;
    public static final int LEAL=30;
    public static final int LINEAR=31;
    public static final int MODELFILE=32;
    public static final int NUMDNA=33;
    public static final int NUMMONOMERS=34;
    public static final int NUMRESIDUES=35;
    public static final int NUMRNA=36;
    public static final int PDB=37;
    public static final int PDBNAME=38;
    public static final int PIXELSIZE=39;
    public static final int PIXELSPERMICRON=40;
    public static final int PROTEINHEAVYATOMS=41;
    public static final int RD=42;
    public static final int RECTANGULAR=43;
    public static final int ROTAXBEAMOFFSET=44;
    public static final int SIMPLE=45;
    public static final int SOLVENTFRACTION=46;
    public static final int SOLVENTHEAVYCONC=47;
    public static final int STARTOFFSET=48;
    public static final int STRING=49;
    public static final int TRANSLATEPERDEGREE=50;
    public static final int TYPE=51;
    public static final int UNITCELL=52;
    public static final int VERTICAL=53;
    public static final int WEDGE=54;
    public static final int WIREFRAMETYPE=55;
    public static final int WS=56;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public InputfileLexer() {} 
    public InputfileLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InputfileLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g"; }

    // $ANTLR start "CRYSTAL"
    public final void mCRYSTAL() throws RecognitionException {
        try {
            int _type = CRYSTAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:134:9: ( ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'Y' | 'y' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:134:11: ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'Y' | 'y' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CRYSTAL"

    // $ANTLR start "TYPE"
    public final void mTYPE() throws RecognitionException {
        try {
            int _type = TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:172:6: ( ( 'T' | 't' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'E' | 'e' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:172:8: ( 'T' | 't' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TYPE"

    // $ANTLR start "DIFFRACTIONDECAYMODEL"
    public final void mDIFFRACTIONDECAYMODEL() throws RecognitionException {
        try {
            int _type = DIFFRACTIONDECAYMODEL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:176:23: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'L' | 'l' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:176:25: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIFFRACTIONDECAYMODEL"

    // $ANTLR start "DDM"
    public final void mDDM() throws RecognitionException {
        try {
            int _type = DDM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:177:5: ( ( 'D' | 'd' ) ( 'D' | 'd' ) ( 'M' | 'm' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:177:7: ( 'D' | 'd' ) ( 'D' | 'd' ) ( 'M' | 'm' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DDM"

    // $ANTLR start "SIMPLE"
    public final void mSIMPLE() throws RecognitionException {
        try {
            int _type = SIMPLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:183:8: ( ( 'S' | 's' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'P' | 'p' ) ( 'L' | 'l' ) ( 'E' | 'e' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:183:10: ( 'S' | 's' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'P' | 'p' ) ( 'L' | 'l' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIMPLE"

    // $ANTLR start "LINEAR"
    public final void mLINEAR() throws RecognitionException {
        try {
            int _type = LINEAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:184:8: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:184:10: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LINEAR"

    // $ANTLR start "LEAL"
    public final void mLEAL() throws RecognitionException {
        try {
            int _type = LEAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:185:6: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:185:8: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LEAL"

    // $ANTLR start "DECAYPARAM"
    public final void mDECAYPARAM() throws RecognitionException {
        try {
            int _type = DECAYPARAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:189:13: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'M' | 'm' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:189:15: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'M' | 'm' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DECAYPARAM"

    // $ANTLR start "ABSCOEFCALC"
    public final void mABSCOEFCALC() throws RecognitionException {
        try {
            int _type = ABSCOEFCALC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:193:13: ( ( ( 'A' | 'a' ) ( 'B' | 'b' ) ( 'S' | 's' ) )? ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'C' | 'c' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:193:15: ( ( 'A' | 'a' ) ( 'B' | 'b' ) ( 'S' | 's' ) )? ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'C' | 'c' )
            {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:193:15: ( ( 'A' | 'a' ) ( 'B' | 'b' ) ( 'S' | 's' ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='A'||LA1_0=='a') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:193:16: ( 'A' | 'a' ) ( 'B' | 'b' ) ( 'S' | 's' )
                    {
                    if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ABSCOEFCALC"

    // $ANTLR start "DUMMY"
    public final void mDUMMY() throws RecognitionException {
        try {
            int _type = DUMMY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:201:7: ( ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'M' | 'm' ) ( 'Y' | 'y' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:201:9: ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'M' | 'm' ) ( 'Y' | 'y' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DUMMY"

    // $ANTLR start "DEFAULT"
    public final void mDEFAULT() throws RecognitionException {
        try {
            int _type = DEFAULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:202:9: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'A' | 'a' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'T' | 't' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:202:11: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'A' | 'a' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DEFAULT"

    // $ANTLR start "AVERAGE"
    public final void mAVERAGE() throws RecognitionException {
        try {
            int _type = AVERAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:203:9: ( ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'G' | 'g' ) ( 'E' | 'e' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:203:11: ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'G' | 'g' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AVERAGE"

    // $ANTLR start "RD"
    public final void mRD() throws RecognitionException {
        try {
            int _type = RD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:204:4: ( ( 'R' | 'r' ) ( 'D' | 'd' ) ( ( 'V' | 'v' ) ( '2' | '3' ) )? )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:204:6: ( 'R' | 'r' ) ( 'D' | 'd' ) ( ( 'V' | 'v' ) ( '2' | '3' ) )?
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:204:24: ( ( 'V' | 'v' ) ( '2' | '3' ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='V'||LA2_0=='v') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:204:25: ( 'V' | 'v' ) ( '2' | '3' )
                    {
                    if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '2' && input.LA(1) <= '3') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RD"

    // $ANTLR start "PDB"
    public final void mPDB() throws RecognitionException {
        try {
            int _type = PDB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:205:5: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:205:7: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PDB"

    // $ANTLR start "DIMENSION"
    public final void mDIMENSION() throws RecognitionException {
        try {
            int _type = DIMENSION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:217:11: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'S' | 's' )? )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:217:13: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'S' | 's' )?
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:217:94: ( 'S' | 's' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='S'||LA3_0=='s') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
                    {
                    if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIMENSION"

    // $ANTLR start "ANGLEP"
    public final void mANGLEP() throws RecognitionException {
        try {
            int _type = ANGLEP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:222:8: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'P' | 'p' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:222:10: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'P' | 'p' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ANGLEP"

    // $ANTLR start "ANGLEL"
    public final void mANGLEL() throws RecognitionException {
        try {
            int _type = ANGLEL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:227:8: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'L' | 'l' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:227:10: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ANGLEL"

    // $ANTLR start "PIXELSPERMICRON"
    public final void mPIXELSPERMICRON() throws RecognitionException {
        try {
            int _type = PIXELSPERMICRON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:231:17: ( ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:231:19: ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PIXELSPERMICRON"

    // $ANTLR start "UNITCELL"
    public final void mUNITCELL() throws RecognitionException {
        try {
            int _type = UNITCELL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:244:10: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:244:12: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNITCELL"

    // $ANTLR start "NUMMONOMERS"
    public final void mNUMMONOMERS() throws RecognitionException {
        try {
            int _type = NUMMONOMERS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:248:12: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:248:14: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMMONOMERS"

    // $ANTLR start "NUMRESIDUES"
    public final void mNUMRESIDUES() throws RecognitionException {
        try {
            int _type = NUMRESIDUES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:252:13: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'S' | 's' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:252:15: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMRESIDUES"

    // $ANTLR start "NUMRNA"
    public final void mNUMRNA() throws RecognitionException {
        try {
            int _type = NUMRNA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:256:8: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'R' | 'r' ) ( 'N' | 'n' ) ( 'A' | 'a' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:256:10: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'R' | 'r' ) ( 'N' | 'n' ) ( 'A' | 'a' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMRNA"

    // $ANTLR start "NUMDNA"
    public final void mNUMDNA() throws RecognitionException {
        try {
            int _type = NUMDNA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:260:8: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'D' | 'd' ) ( 'N' | 'n' ) ( 'A' | 'a' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:260:10: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'D' | 'd' ) ( 'N' | 'n' ) ( 'A' | 'a' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMDNA"

    // $ANTLR start "PROTEINHEAVYATOMS"
    public final void mPROTEINHEAVYATOMS() throws RecognitionException {
        try {
            int _type = PROTEINHEAVYATOMS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:268:19: ( ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'Y' | 'y' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'S' | 's' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:268:21: ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'Y' | 'y' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PROTEINHEAVYATOMS"

    // $ANTLR start "ELEMENT"
    public final void mELEMENT() throws RecognitionException {
        try {
            int _type = ELEMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:269:9: ( ( 'A' .. 'Z' | 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' )? )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:269:11: ( 'A' .. 'Z' | 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' )?
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:269:32: ( 'A' .. 'Z' | 'a' .. 'z' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0 >= 'A' && LA4_0 <= 'Z')||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
                    {
                    if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ELEMENT"

    // $ANTLR start "SOLVENTHEAVYCONC"
    public final void mSOLVENTHEAVYCONC() throws RecognitionException {
        try {
            int _type = SOLVENTHEAVYCONC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:277:18: ( ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'Y' | 'y' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'C' | 'c' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:277:20: ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'Y' | 'y' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'C' | 'c' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SOLVENTHEAVYCONC"

    // $ANTLR start "SOLVENTFRACTION"
    public final void mSOLVENTFRACTION() throws RecognitionException {
        try {
            int _type = SOLVENTFRACTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:281:17: ( ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:281:19: ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SOLVENTFRACTION"

    // $ANTLR start "PDBNAME"
    public final void mPDBNAME() throws RecognitionException {
        try {
            int _type = PDBNAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:285:9: ( ( 'P' | 'p' ) ( 'D' | 'd' ) ( 'B' | 'b' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:285:11: ( 'P' | 'p' ) ( 'D' | 'd' ) ( 'B' | 'b' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PDBNAME"

    // $ANTLR start "WIREFRAMETYPE"
    public final void mWIREFRAMETYPE() throws RecognitionException {
        try {
            int _type = WIREFRAMETYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:289:15: ( ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'Y' | 'y' |) ( 'P' | 'p' ) ( 'E' | 'e' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:289:17: ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'Y' | 'y' |) ( 'P' | 'p' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:289:107: ( 'Y' | 'y' |)
            int alt5=3;
            switch ( input.LA(1) ) {
            case 'Y':
                {
                alt5=1;
                }
                break;
            case 'y':
                {
                alt5=2;
                }
                break;
            case 'P':
            case 'p':
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:289:108: 'Y'
                    {
                    match('Y'); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:289:112: 'y'
                    {
                    match('y'); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:289:116: 
                    {
                    }
                    break;

            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WIREFRAMETYPE"

    // $ANTLR start "MODELFILE"
    public final void mMODELFILE() throws RecognitionException {
        try {
            int _type = MODELFILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:293:11: ( ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:293:13: ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MODELFILE"

    // $ANTLR start "BEAM"
    public final void mBEAM() throws RecognitionException {
        try {
            int _type = BEAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:309:6: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'M' | 'm' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:309:8: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'M' | 'm' )
            {
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BEAM"

    // $ANTLR start "FLUX"
    public final void mFLUX() throws RecognitionException {
        try {
            int _type = FLUX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:326:6: ( ( 'F' | 'f' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'X' | 'x' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:326:8: ( 'F' | 'f' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'X' | 'x' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLUX"

    // $ANTLR start "FWHM"
    public final void mFWHM() throws RecognitionException {
        try {
            int _type = FWHM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:330:6: ( ( 'F' | 'f' ) ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'M' | 'm' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:330:8: ( 'F' | 'f' ) ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'M' | 'm' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FWHM"

    // $ANTLR start "ENERGY"
    public final void mENERGY() throws RecognitionException {
        try {
            int _type = ENERGY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:336:8: ( ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'G' | 'g' ) ( 'Y' | 'y' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:336:10: ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'G' | 'g' ) ( 'Y' | 'y' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENERGY"

    // $ANTLR start "KEV"
    public final void mKEV() throws RecognitionException {
        try {
            int _type = KEV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:337:5: ( ( 'K' | 'k' ) ( 'E' | 'e' ) ( 'V' | 'v' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:337:7: ( 'K' | 'k' ) ( 'E' | 'e' ) ( 'V' | 'v' )
            {
            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEV"

    // $ANTLR start "FILE"
    public final void mFILE() throws RecognitionException {
        try {
            int _type = FILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:342:6: ( ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:342:8: ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FILE"

    // $ANTLR start "PIXELSIZE"
    public final void mPIXELSIZE() throws RecognitionException {
        try {
            int _type = PIXELSIZE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:350:2: ( ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'Z' | 'z' ) ( 'E' | 'e' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:350:5: ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'Z' | 'z' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Z'||input.LA(1)=='z' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PIXELSIZE"

    // $ANTLR start "COLLIMATION"
    public final void mCOLLIMATION() throws RecognitionException {
        try {
            int _type = COLLIMATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:363:13: ( ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:363:15: ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLLIMATION"

    // $ANTLR start "RECTANGULAR"
    public final void mRECTANGULAR() throws RecognitionException {
        try {
            int _type = RECTANGULAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:364:13: ( ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:364:15: ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' )
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RECTANGULAR"

    // $ANTLR start "CIRCULAR"
    public final void mCIRCULAR() throws RecognitionException {
        try {
            int _type = CIRCULAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:365:11: ( ( 'C' | 'c' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:365:13: ( 'C' | 'c' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CIRCULAR"

    // $ANTLR start "HORIZONTAL"
    public final void mHORIZONTAL() throws RecognitionException {
        try {
            int _type = HORIZONTAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:366:13: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'Z' | 'z' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:366:15: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'Z' | 'z' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Z'||input.LA(1)=='z' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HORIZONTAL"

    // $ANTLR start "VERTICAL"
    public final void mVERTICAL() throws RecognitionException {
        try {
            int _type = VERTICAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:367:11: ( ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:367:13: ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VERTICAL"

    // $ANTLR start "WEDGE"
    public final void mWEDGE() throws RecognitionException {
        try {
            int _type = WEDGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:393:7: ( ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'G' | 'g' ) ( 'E' | 'e' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:393:9: ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'G' | 'g' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WEDGE"

    // $ANTLR start "EXPOSURETIME"
    public final void mEXPOSURETIME() throws RecognitionException {
        try {
            int _type = EXPOSURETIME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:409:14: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:409:16: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPOSURETIME"

    // $ANTLR start "ANGULARRESOLUTION"
    public final void mANGULARRESOLUTION() throws RecognitionException {
        try {
            int _type = ANGULARRESOLUTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:413:19: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:413:21: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ANGULARRESOLUTION"

    // $ANTLR start "STARTOFFSET"
    public final void mSTARTOFFSET() throws RecognitionException {
        try {
            int _type = STARTOFFSET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:419:13: ( ( 'S' | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:419:15: ( 'S' | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STARTOFFSET"

    // $ANTLR start "TRANSLATEPERDEGREE"
    public final void mTRANSLATEPERDEGREE() throws RecognitionException {
        try {
            int _type = TRANSLATEPERDEGREE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:425:20: ( ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'E' | 'e' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:425:22: ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TRANSLATEPERDEGREE"

    // $ANTLR start "ROTAXBEAMOFFSET"
    public final void mROTAXBEAMOFFSET() throws RecognitionException {
        try {
            int _type = ROTAXBEAMOFFSET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:429:17: ( ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'X' | 'x' ) ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:429:19: ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'X' | 'x' ) ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ROTAXBEAMOFFSET"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:434:5: ( ( '+' | '-' )? ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ ) ( EXPONENT )? )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:434:7: ( '+' | '-' )? ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ ) ( EXPONENT )?
            {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:434:7: ( '+' | '-' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='+'||LA6_0=='-') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:435:5: ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ )
            int alt11=3;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:435:7: ( '0' .. '9' )+
                    {
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:435:7: ( '0' .. '9' )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0 >= '0' && LA7_0 <= '9')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt7 >= 1 ) break loop7;
                                EarlyExitException eee =
                                    new EarlyExitException(7, input);
                                throw eee;
                        }
                        cnt7++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:436:7: ( '0' .. '9' )+ '.' ( '0' .. '9' )*
                    {
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:436:7: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);


                    match('.'); 

                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:436:23: ( '0' .. '9' )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0 >= '0' && LA9_0 <= '9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:437:19: '.' ( '0' .. '9' )+
                    {
                    match('.'); 

                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:437:23: ( '0' .. '9' )+
                    int cnt10=0;
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0 >= '0' && LA10_0 <= '9')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt10 >= 1 ) break loop10;
                                EarlyExitException eee =
                                    new EarlyExitException(10, input);
                                throw eee;
                        }
                        cnt10++;
                    } while (true);


                    }
                    break;

            }


            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:438:7: ( EXPONENT )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='E'||LA12_0=='e') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:438:7: EXPONENT
                    {
                    mEXPONENT(); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:442:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:442:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:442:22: ( '+' | '-' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='+'||LA13_0=='-') ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:442:33: ( '0' .. '9' )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0 >= '0' && LA14_0 <= '9')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:444:5: ( ( '#' | '//' | '!' ) (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:444:9: ( '#' | '//' | '!' ) (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:444:9: ( '#' | '//' | '!' )
            int alt15=3;
            switch ( input.LA(1) ) {
            case '#':
                {
                alt15=1;
                }
                break;
            case '/':
                {
                alt15=2;
                }
                break;
            case '!':
                {
                alt15=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }

            switch (alt15) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:444:10: '#'
                    {
                    match('#'); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:444:16: '//'
                    {
                    match("//"); 



                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:444:23: '!'
                    {
                    match('!'); 

                    }
                    break;

            }


            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:444:28: (~ ( '\\n' | '\\r' ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0 >= '\u0000' && LA16_0 <= '\t')||(LA16_0 >= '\u000B' && LA16_0 <= '\f')||(LA16_0 >= '\u000E' && LA16_0 <= '\uFFFF')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:444:42: ( '\\r' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='\r') ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:444:42: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:447:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:447:9: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:462:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '$' | '-' | '_' )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:462:4: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '$' | '-' | '_' )+
            {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:462:4: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '$' | '-' | '_' )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0=='$'||(LA18_0 >= '-' && LA18_0 <= '.')||(LA18_0 >= '0' && LA18_0 <= '9')||(LA18_0 >= 'A' && LA18_0 <= 'Z')||LA18_0=='_'||(LA18_0 >= 'a' && LA18_0 <= 'z')) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING"

    public void mTokens() throws RecognitionException {
        // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:8: ( CRYSTAL | TYPE | DIFFRACTIONDECAYMODEL | DDM | SIMPLE | LINEAR | LEAL | DECAYPARAM | ABSCOEFCALC | DUMMY | DEFAULT | AVERAGE | RD | PDB | DIMENSION | ANGLEP | ANGLEL | PIXELSPERMICRON | UNITCELL | NUMMONOMERS | NUMRESIDUES | NUMRNA | NUMDNA | PROTEINHEAVYATOMS | ELEMENT | SOLVENTHEAVYCONC | SOLVENTFRACTION | PDBNAME | WIREFRAMETYPE | MODELFILE | BEAM | FLUX | FWHM | ENERGY | KEV | FILE | PIXELSIZE | COLLIMATION | RECTANGULAR | CIRCULAR | HORIZONTAL | VERTICAL | WEDGE | EXPOSURETIME | ANGULARRESOLUTION | STARTOFFSET | TRANSLATEPERDEGREE | ROTAXBEAMOFFSET | FLOAT | COMMENT | WS | STRING )
        int alt19=52;
        alt19 = dfa19.predict(input);
        switch (alt19) {
            case 1 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:10: CRYSTAL
                {
                mCRYSTAL(); 


                }
                break;
            case 2 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:18: TYPE
                {
                mTYPE(); 


                }
                break;
            case 3 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:23: DIFFRACTIONDECAYMODEL
                {
                mDIFFRACTIONDECAYMODEL(); 


                }
                break;
            case 4 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:45: DDM
                {
                mDDM(); 


                }
                break;
            case 5 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:49: SIMPLE
                {
                mSIMPLE(); 


                }
                break;
            case 6 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:56: LINEAR
                {
                mLINEAR(); 


                }
                break;
            case 7 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:63: LEAL
                {
                mLEAL(); 


                }
                break;
            case 8 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:68: DECAYPARAM
                {
                mDECAYPARAM(); 


                }
                break;
            case 9 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:79: ABSCOEFCALC
                {
                mABSCOEFCALC(); 


                }
                break;
            case 10 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:91: DUMMY
                {
                mDUMMY(); 


                }
                break;
            case 11 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:97: DEFAULT
                {
                mDEFAULT(); 


                }
                break;
            case 12 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:105: AVERAGE
                {
                mAVERAGE(); 


                }
                break;
            case 13 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:113: RD
                {
                mRD(); 


                }
                break;
            case 14 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:116: PDB
                {
                mPDB(); 


                }
                break;
            case 15 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:120: DIMENSION
                {
                mDIMENSION(); 


                }
                break;
            case 16 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:130: ANGLEP
                {
                mANGLEP(); 


                }
                break;
            case 17 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:137: ANGLEL
                {
                mANGLEL(); 


                }
                break;
            case 18 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:144: PIXELSPERMICRON
                {
                mPIXELSPERMICRON(); 


                }
                break;
            case 19 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:160: UNITCELL
                {
                mUNITCELL(); 


                }
                break;
            case 20 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:169: NUMMONOMERS
                {
                mNUMMONOMERS(); 


                }
                break;
            case 21 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:181: NUMRESIDUES
                {
                mNUMRESIDUES(); 


                }
                break;
            case 22 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:193: NUMRNA
                {
                mNUMRNA(); 


                }
                break;
            case 23 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:200: NUMDNA
                {
                mNUMDNA(); 


                }
                break;
            case 24 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:207: PROTEINHEAVYATOMS
                {
                mPROTEINHEAVYATOMS(); 


                }
                break;
            case 25 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:225: ELEMENT
                {
                mELEMENT(); 


                }
                break;
            case 26 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:233: SOLVENTHEAVYCONC
                {
                mSOLVENTHEAVYCONC(); 


                }
                break;
            case 27 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:250: SOLVENTFRACTION
                {
                mSOLVENTFRACTION(); 


                }
                break;
            case 28 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:266: PDBNAME
                {
                mPDBNAME(); 


                }
                break;
            case 29 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:274: WIREFRAMETYPE
                {
                mWIREFRAMETYPE(); 


                }
                break;
            case 30 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:288: MODELFILE
                {
                mMODELFILE(); 


                }
                break;
            case 31 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:298: BEAM
                {
                mBEAM(); 


                }
                break;
            case 32 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:303: FLUX
                {
                mFLUX(); 


                }
                break;
            case 33 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:308: FWHM
                {
                mFWHM(); 


                }
                break;
            case 34 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:313: ENERGY
                {
                mENERGY(); 


                }
                break;
            case 35 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:320: KEV
                {
                mKEV(); 


                }
                break;
            case 36 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:324: FILE
                {
                mFILE(); 


                }
                break;
            case 37 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:329: PIXELSIZE
                {
                mPIXELSIZE(); 


                }
                break;
            case 38 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:339: COLLIMATION
                {
                mCOLLIMATION(); 


                }
                break;
            case 39 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:351: RECTANGULAR
                {
                mRECTANGULAR(); 


                }
                break;
            case 40 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:363: CIRCULAR
                {
                mCIRCULAR(); 


                }
                break;
            case 41 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:372: HORIZONTAL
                {
                mHORIZONTAL(); 


                }
                break;
            case 42 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:383: VERTICAL
                {
                mVERTICAL(); 


                }
                break;
            case 43 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:392: WEDGE
                {
                mWEDGE(); 


                }
                break;
            case 44 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:398: EXPOSURETIME
                {
                mEXPOSURETIME(); 


                }
                break;
            case 45 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:411: ANGULARRESOLUTION
                {
                mANGULARRESOLUTION(); 


                }
                break;
            case 46 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:429: STARTOFFSET
                {
                mSTARTOFFSET(); 


                }
                break;
            case 47 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:441: TRANSLATEPERDEGREE
                {
                mTRANSLATEPERDEGREE(); 


                }
                break;
            case 48 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:460: ROTAXBEAMOFFSET
                {
                mROTAXBEAMOFFSET(); 


                }
                break;
            case 49 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:476: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 50 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:482: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 51 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:490: WS
                {
                mWS(); 


                }
                break;
            case 52 :
                // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:493: STRING
                {
                mSTRING(); 


                }
                break;

        }

    }


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA19 dfa19 = new DFA19(this);
    static final String DFA11_eotS =
        "\1\uffff\1\3\3\uffff";
    static final String DFA11_eofS =
        "\5\uffff";
    static final String DFA11_minS =
        "\2\56\3\uffff";
    static final String DFA11_maxS =
        "\2\71\3\uffff";
    static final String DFA11_acceptS =
        "\2\uffff\1\3\1\1\1\2";
    static final String DFA11_specialS =
        "\5\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\4\1\uffff\12\1",
            "",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "435:5: ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ )";
        }
    }
    static final String DFA19_eotS =
        "\1\uffff\23\37\1\32\1\31\1\32\4\uffff\4\37\1\uffff\16\37\1\132\23"+
        "\37\1\32\2\31\10\32\1\171\14\32\1\uffff\2\32\1\u008a\3\32\1\u008e"+
        "\11\32\1\u009a\3\32\2\31\4\32\1\u00a1\3\32\1\uffff\7\32\1\u00ac"+
        "\4\32\1\132\3\32\1\uffff\3\32\1\uffff\7\32\1\u00bf\1\u00c0\1\u00c1"+
        "\1\u00c2\1\uffff\6\32\1\uffff\5\32\1\u00ce\4\32\1\uffff\20\32\1"+
        "\u00e3\1\32\4\uffff\13\32\1\uffff\1\u00f0\2\32\1\u00f3\1\32\1\u00f5"+
        "\1\u00f6\4\32\1\u00fb\5\32\1\u0102\1\u0103\1\32\1\uffff\3\32\1\u0108"+
        "\7\32\1\u0110\1\uffff\2\32\1\uffff\1\u0114\2\uffff\4\32\1\uffff"+
        "\6\32\2\uffff\4\32\1\uffff\1\u0123\1\32\1\u0125\4\32\1\uffff\3\32"+
        "\1\uffff\7\32\1\u0134\5\32\1\u013a\1\uffff\1\32\1\uffff\2\32\1\u013f"+
        "\11\32\1\u0149\1\32\1\uffff\3\32\1\u014e\1\32\1\uffff\3\32\1\u013f"+
        "\1\uffff\1\u0153\10\32\1\uffff\4\32\1\uffff\1\u0162\1\u0163\2\32"+
        "\1\uffff\2\32\1\u0168\1\32\1\u016a\4\32\1\u016f\1\u0170\3\32\2\uffff"+
        "\4\32\1\uffff\1\32\1\uffff\1\32\1\u0178\2\32\2\uffff\1\u017b\6\32"+
        "\1\uffff\2\32\1\uffff\13\32\1\u018f\1\32\1\u0191\1\u0192\3\32\1"+
        "\u0196\1\uffff\1\32\2\uffff\3\32\1\uffff\1\u019b\1\u019c\1\u019d"+
        "\1\32\3\uffff\2\32\1\u01a1\1\uffff";
    static final String DFA19_eofS =
        "\u01a2\uffff";
    static final String DFA19_minS =
        "\1\11\23\44\1\56\1\44\1\60\4\uffff\4\44\1\uffff\42\44\1\53\2\44"+
        "\1\123\1\106\1\114\1\103\1\105\1\116\1\106\1\105\1\44\2\101\1\115"+
        "\1\120\1\126\1\122\1\105\1\114\1\103\1\122\1\114\1\62\1\uffff\1"+
        "\124\1\101\1\44\1\122\1\105\1\124\1\44\1\124\1\104\1\105\1\107\1"+
        "\105\1\115\1\130\1\115\1\105\1\44\1\111\1\124\1\60\2\44\1\124\1"+
        "\103\1\111\1\125\1\44\1\123\1\122\1\116\1\uffff\1\131\1\125\1\131"+
        "\1\114\1\105\1\124\1\101\1\44\1\117\1\101\1\105\1\114\1\44\1\101"+
        "\1\130\1\123\1\uffff\1\107\1\114\1\105\1\uffff\1\103\1\117\1\105"+
        "\1\116\1\106\1\105\1\114\4\44\1\uffff\1\132\1\111\2\101\1\115\1"+
        "\114\1\uffff\1\114\1\101\1\123\1\120\1\114\1\44\1\105\1\116\1\117"+
        "\1\122\1\uffff\1\105\1\107\1\114\1\101\1\116\1\102\1\125\1\131\1"+
        "\123\1\111\1\105\1\116\1\123\2\101\1\122\1\44\1\106\4\uffff\1\117"+
        "\1\103\2\114\3\101\1\103\1\111\1\101\1\124\1\uffff\1\44\1\124\1"+
        "\106\1\44\1\105\2\44\1\122\1\107\1\105\1\122\1\44\1\111\1\116\1"+
        "\114\1\117\1\111\2\44\1\101\1\uffff\1\111\1\116\1\101\1\44\1\103"+
        "\1\124\1\122\2\124\1\117\1\122\1\44\1\uffff\2\106\1\uffff\1\44\2"+
        "\uffff\1\122\1\125\1\101\1\105\1\uffff\1\105\1\132\1\110\1\114\1"+
        "\115\1\104\2\uffff\1\115\1\114\1\124\1\114\1\uffff\1\44\1\111\1"+
        "\44\1\105\1\111\1\116\1\101\1\uffff\1\105\1\122\1\123\1\uffff\1"+
        "\105\1\114\1\115\1\124\1\122\2\105\1\44\1\105\1\125\2\105\1\101"+
        "\1\44\1\uffff\1\117\1\uffff\1\120\1\117\1\44\1\115\2\101\1\105\1"+
        "\123\1\101\1\117\1\111\1\115\1\44\1\101\1\uffff\1\122\1\105\1\124"+
        "\1\44\1\114\1\uffff\1\116\1\105\1\116\1\44\1\uffff\1\44\1\126\1"+
        "\103\1\124\1\117\1\122\1\106\1\115\1\111\1\uffff\1\126\2\123\1\120"+
        "\1\uffff\2\44\1\122\1\104\1\uffff\1\131\1\124\1\44\1\114\1\44\1"+
        "\106\1\105\1\103\1\131\2\44\2\120\1\105\2\uffff\1\104\1\105\1\103"+
        "\1\111\1\uffff\1\125\1\uffff\1\123\1\44\1\122\1\101\2\uffff\1\44"+
        "\1\105\1\103\2\117\1\124\1\105\1\uffff\1\117\1\124\1\uffff\1\107"+
        "\1\101\2\116\1\111\1\124\1\116\1\117\1\122\1\131\1\103\1\44\1\117"+
        "\2\44\1\115\1\105\1\115\1\44\1\uffff\1\116\2\uffff\1\123\1\105\1"+
        "\117\1\uffff\3\44\1\104\3\uffff\1\105\1\114\1\44\1\uffff";
    static final String DFA19_maxS =
        "\24\172\1\71\1\172\1\71\4\uffff\4\172\1\uffff\42\172\1\71\2\172"+
        "\1\163\1\146\1\154\1\143\1\145\1\156\1\146\1\145\1\172\2\141\1\155"+
        "\1\160\1\166\1\162\1\145\1\154\1\143\1\162\1\165\1\63\1\uffff\1"+
        "\164\1\141\1\172\1\162\1\145\1\164\1\172\1\164\1\162\1\145\1\147"+
        "\1\145\1\155\1\170\1\155\1\145\1\172\1\151\1\164\1\71\2\172\1\164"+
        "\1\143\1\151\1\165\1\172\1\163\1\162\1\156\1\uffff\1\171\1\165\1"+
        "\171\1\154\1\145\1\164\1\141\1\172\1\157\1\141\1\145\1\154\1\172"+
        "\1\141\1\170\1\163\1\uffff\1\147\1\154\1\145\1\uffff\1\143\1\157"+
        "\2\156\1\146\1\145\1\154\4\172\1\uffff\1\172\1\151\2\141\1\155\1"+
        "\154\1\uffff\1\154\1\141\1\163\1\160\1\154\1\172\1\145\1\156\1\157"+
        "\1\162\1\uffff\1\145\1\147\1\160\1\141\1\156\1\142\1\165\1\171\1"+
        "\163\1\151\1\145\1\156\1\163\2\141\1\162\1\172\1\146\4\uffff\1\157"+
        "\1\143\2\154\3\141\1\143\1\151\1\141\1\164\1\uffff\1\172\1\164\1"+
        "\146\1\172\1\145\2\172\1\162\1\147\1\145\1\162\1\172\1\160\1\156"+
        "\1\154\1\157\1\151\2\172\1\141\1\uffff\1\151\1\156\1\141\1\172\1"+
        "\143\1\164\1\162\2\164\1\157\1\162\1\172\1\uffff\1\150\1\146\1\uffff"+
        "\1\172\2\uffff\1\162\1\165\1\141\1\145\1\uffff\1\145\1\172\1\150"+
        "\1\154\1\155\1\144\2\uffff\1\155\1\154\1\164\1\154\1\uffff\1\172"+
        "\1\151\1\172\1\145\1\151\1\156\1\141\1\uffff\1\145\1\162\1\163\1"+
        "\uffff\1\145\1\154\1\155\1\164\1\162\2\145\1\172\1\145\1\165\2\145"+
        "\1\141\1\172\1\uffff\1\157\1\uffff\1\160\1\157\1\172\1\155\2\141"+
        "\1\145\1\163\1\141\1\157\1\151\1\155\1\172\1\141\1\uffff\1\162\1"+
        "\145\1\164\1\172\1\154\1\uffff\1\156\1\145\1\156\1\172\1\uffff\1"+
        "\172\1\166\1\143\1\164\1\157\1\162\1\146\1\155\1\151\1\uffff\1\166"+
        "\2\163\1\171\1\uffff\2\172\1\162\1\144\1\uffff\1\171\1\164\1\172"+
        "\1\154\1\172\1\146\1\145\1\143\1\171\2\172\2\160\1\145\2\uffff\1"+
        "\144\1\145\1\143\1\151\1\uffff\1\165\1\uffff\1\163\1\172\1\162\1"+
        "\141\2\uffff\1\172\1\145\1\143\2\157\1\164\1\145\1\uffff\1\157\1"+
        "\164\1\uffff\1\147\1\141\2\156\1\151\1\164\1\156\1\157\1\162\1\171"+
        "\1\143\1\172\1\157\2\172\1\155\1\145\1\155\1\172\1\uffff\1\156\2"+
        "\uffff\1\163\1\145\1\157\1\uffff\3\172\1\144\3\uffff\1\145\1\154"+
        "\1\172\1\uffff";
    static final String DFA19_acceptS =
        "\27\uffff\1\62\1\63\1\61\1\64\4\uffff\1\31\72\uffff\1\15\36\uffff"+
        "\1\4\20\uffff\1\16\3\uffff\1\34\13\uffff\1\43\6\uffff\1\2\12\uffff"+
        "\1\7\22\uffff\1\37\1\40\1\41\1\44\13\uffff\1\12\24\uffff\1\53\14"+
        "\uffff\1\5\2\uffff\1\6\1\uffff\1\20\1\21\4\uffff\1\42\6\uffff\1"+
        "\26\1\27\4\uffff\1\1\7\uffff\1\13\3\uffff\1\14\16\uffff\1\11\1\uffff"+
        "\1\50\16\uffff\1\23\5\uffff\1\52\4\uffff\1\17\11\uffff\1\45\4\uffff"+
        "\1\36\4\uffff\1\10\16\uffff\1\51\1\46\4\uffff\1\56\1\uffff\1\47"+
        "\4\uffff\1\24\1\25\7\uffff\1\54\2\uffff\1\35\23\uffff\1\33\1\uffff"+
        "\1\60\1\22\3\uffff\1\32\4\uffff\1\55\1\30\1\57\3\uffff\1\3";
    static final String DFA19_specialS =
        "\u01a2\uffff}>";
    static final String[] DFA19_transitionS = {
            "\2\30\2\uffff\1\30\22\uffff\1\30\1\27\1\uffff\1\27\1\32\6\uffff"+
            "\1\31\1\uffff\1\24\1\26\1\27\12\25\7\uffff\1\6\1\16\1\1\1\3"+
            "\1\10\1\17\1\23\1\21\2\23\1\20\1\5\1\15\1\13\1\23\1\11\1\23"+
            "\1\7\1\4\1\2\1\12\1\22\1\14\3\23\4\uffff\1\32\1\uffff\1\6\1"+
            "\16\1\1\1\3\1\10\1\17\1\23\1\21\2\23\1\20\1\5\1\15\1\13\1\23"+
            "\1\11\1\23\1\7\1\4\1\2\1\12\1\22\1\14\3\23",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\10\36\1\35\5\36\1"+
            "\34\2\36\1\33\10\36\4\uffff\1\32\1\uffff\10\36\1\35\5\36\1\34"+
            "\2\36\1\33\10\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\21\36\1\41\6\36\1"+
            "\40\1\36\4\uffff\1\32\1\uffff\21\36\1\41\6\36\1\40\1\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\3\36\1\43\1\44\3"+
            "\36\1\42\13\36\1\45\5\36\4\uffff\1\32\1\uffff\3\36\1\43\1\44"+
            "\3\36\1\42\13\36\1\45\5\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\10\36\1\46\5\36\1"+
            "\47\4\36\1\50\6\36\4\uffff\1\32\1\uffff\10\36\1\46\5\36\1\47"+
            "\4\36\1\50\6\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\4\36\1\52\3\36\1"+
            "\51\21\36\4\uffff\1\32\1\uffff\4\36\1\52\3\36\1\51\21\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\1\36\1\53\13\36\1"+
            "\55\7\36\1\54\4\36\4\uffff\1\32\1\uffff\1\36\1\53\13\36\1\55"+
            "\7\36\1\54\4\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\3\36\1\56\1\57\11"+
            "\36\1\60\13\36\4\uffff\1\32\1\uffff\3\36\1\56\1\57\11\36\1\60"+
            "\13\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\15\36\1\62\11\36"+
            "\1\61\2\36\4\uffff\1\32\1\uffff\15\36\1\62\11\36\1\61\2\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\3\36\1\65\4\36\1"+
            "\63\10\36\1\64\10\36\4\uffff\1\32\1\uffff\3\36\1\65\4\36\1\63"+
            "\10\36\1\64\10\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\15\36\1\66\14\36"+
            "\4\uffff\1\32\1\uffff\15\36\1\66\14\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\24\36\1\67\5\36\4"+
            "\uffff\1\32\1\uffff\24\36\1\67\5\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\4\36\1\71\3\36\1"+
            "\70\21\36\4\uffff\1\32\1\uffff\4\36\1\71\3\36\1\70\21\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\16\36\1\72\13\36"+
            "\4\uffff\1\32\1\uffff\16\36\1\72\13\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\4\36\1\73\25\36\4"+
            "\uffff\1\32\1\uffff\4\36\1\73\25\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\10\36\1\76\2\36\1"+
            "\74\12\36\1\75\3\36\4\uffff\1\32\1\uffff\10\36\1\76\2\36\1\74"+
            "\12\36\1\75\3\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\4\36\1\77\25\36\4"+
            "\uffff\1\32\1\uffff\4\36\1\77\25\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\16\36\1\100\13\36"+
            "\4\uffff\1\32\1\uffff\16\36\1\100\13\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\4\36\1\101\25\36"+
            "\4\uffff\1\32\1\uffff\4\36\1\101\25\36",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\36\4\uffff\1\32"+
            "\1\uffff\32\36",
            "\1\26\1\uffff\12\25",
            "\1\32\10\uffff\1\32\1\103\1\uffff\12\25\7\uffff\4\32\1\102"+
            "\25\32\4\uffff\1\32\1\uffff\4\32\1\102\25\32",
            "\12\104",
            "",
            "",
            "",
            "",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\30\32\1\105\1\32"+
            "\4\uffff\1\32\1\uffff\30\32\1\105\1\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\4\32\1\106\6\32\1"+
            "\107\16\32\4\uffff\1\32\1\uffff\4\32\1\106\6\32\1\107\16\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\21\32\1\110\10\32"+
            "\4\uffff\1\32\1\uffff\21\32\1\110\10\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\17\32\1\111\12\32"+
            "\4\uffff\1\32\1\uffff\17\32\1\111\12\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\1\112\31\32\4\uffff"+
            "\1\32\1\uffff\1\112\31\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\5\32\1\113\6\32\1"+
            "\114\15\32\4\uffff\1\32\1\uffff\5\32\1\113\6\32\1\114\15\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\14\32\1\115\15\32"+
            "\4\uffff\1\32\1\uffff\14\32\1\115\15\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\2\32\1\116\2\32\1"+
            "\117\24\32\4\uffff\1\32\1\uffff\2\32\1\116\2\32\1\117\24\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\14\32\1\120\15\32"+
            "\4\uffff\1\32\1\uffff\14\32\1\120\15\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\14\32\1\121\15\32"+
            "\4\uffff\1\32\1\uffff\14\32\1\121\15\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\13\32\1\122\16\32"+
            "\4\uffff\1\32\1\uffff\13\32\1\122\16\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\1\123\31\32\4\uffff"+
            "\1\32\1\uffff\1\123\31\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\15\32\1\124\14\32"+
            "\4\uffff\1\32\1\uffff\15\32\1\124\14\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\1\125\31\32\4\uffff"+
            "\1\32\1\uffff\1\125\31\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\22\32\1\126\7\32"+
            "\4\uffff\1\32\1\uffff\22\32\1\126\7\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\4\32\1\127\25\32"+
            "\4\uffff\1\32\1\uffff\4\32\1\127\25\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\6\32\1\130\23\32"+
            "\4\uffff\1\32\1\uffff\6\32\1\130\23\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\25\32\1\131\4\32"+
            "\4\uffff\1\32\1\uffff\25\32\1\131\4\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\2\32\1\133\27\32"+
            "\4\uffff\1\32\1\uffff\2\32\1\133\27\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\23\32\1\134\6\32"+
            "\4\uffff\1\32\1\uffff\23\32\1\134\6\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\17\32\1\135\12\32"+
            "\4\uffff\1\32\1\uffff\17\32\1\135\12\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\4\32\1\136\25\32"+
            "\4\uffff\1\32\1\uffff\4\32\1\136\25\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\27\32\1\137\2\32"+
            "\4\uffff\1\32\1\uffff\27\32\1\137\2\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\16\32\1\140\13\32"+
            "\4\uffff\1\32\1\uffff\16\32\1\140\13\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\1\32\1\141\30\32"+
            "\4\uffff\1\32\1\uffff\1\32\1\141\30\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\10\32\1\142\21\32"+
            "\4\uffff\1\32\1\uffff\10\32\1\142\21\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\14\32\1\143\15\32"+
            "\4\uffff\1\32\1\uffff\14\32\1\143\15\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\21\32\1\144\10\32"+
            "\4\uffff\1\32\1\uffff\21\32\1\144\10\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\3\32\1\145\26\32"+
            "\4\uffff\1\32\1\uffff\3\32\1\145\26\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\3\32\1\146\26\32"+
            "\4\uffff\1\32\1\uffff\3\32\1\146\26\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\1\147\31\32\4\uffff"+
            "\1\32\1\uffff\1\147\31\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\24\32\1\150\5\32"+
            "\4\uffff\1\32\1\uffff\24\32\1\150\5\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\7\32\1\151\22\32"+
            "\4\uffff\1\32\1\uffff\7\32\1\151\22\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\13\32\1\152\16\32"+
            "\4\uffff\1\32\1\uffff\13\32\1\152\16\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\25\32\1\153\4\32"+
            "\4\uffff\1\32\1\uffff\25\32\1\153\4\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\21\32\1\154\10\32"+
            "\4\uffff\1\32\1\uffff\21\32\1\154\10\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\21\32\1\155\10\32"+
            "\4\uffff\1\32\1\uffff\21\32\1\155\10\32",
            "\1\31\1\uffff\1\156\2\uffff\12\157",
            "\1\32\10\uffff\2\32\1\uffff\12\160\7\uffff\4\32\1\102\25\32"+
            "\4\uffff\1\32\1\uffff\4\32\1\102\25\32",
            "\1\32\10\uffff\2\32\1\uffff\12\104\7\uffff\4\32\1\102\25\32"+
            "\4\uffff\1\32\1\uffff\4\32\1\102\25\32",
            "\1\161\37\uffff\1\161",
            "\1\162\37\uffff\1\162",
            "\1\163\37\uffff\1\163",
            "\1\164\37\uffff\1\164",
            "\1\165\37\uffff\1\165",
            "\1\166\37\uffff\1\166",
            "\1\167\37\uffff\1\167",
            "\1\170\37\uffff\1\170",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\172\37\uffff\1\172",
            "\1\173\37\uffff\1\173",
            "\1\174\37\uffff\1\174",
            "\1\175\37\uffff\1\175",
            "\1\176\37\uffff\1\176",
            "\1\177\37\uffff\1\177",
            "\1\u0080\37\uffff\1\u0080",
            "\1\u0081\37\uffff\1\u0081",
            "\1\u0082\37\uffff\1\u0082",
            "\1\u0083\37\uffff\1\u0083",
            "\1\u0084\10\uffff\1\u0085\26\uffff\1\u0084\10\uffff\1\u0085",
            "\2\u0086",
            "",
            "\1\u0087\37\uffff\1\u0087",
            "\1\u0088\37\uffff\1\u0088",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\16\32\1\u0089\13"+
            "\32\4\uffff\1\32\1\uffff\16\32\1\u0089\13\32",
            "\1\u008b\37\uffff\1\u008b",
            "\1\u008c\37\uffff\1\u008c",
            "\1\u008d\37\uffff\1\u008d",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u008f\37\uffff\1\u008f",
            "\1\u0092\10\uffff\1\u0090\4\uffff\1\u0091\21\uffff\1\u0092"+
            "\10\uffff\1\u0090\4\uffff\1\u0091",
            "\1\u0093\37\uffff\1\u0093",
            "\1\u0094\37\uffff\1\u0094",
            "\1\u0095\37\uffff\1\u0095",
            "\1\u0096\37\uffff\1\u0096",
            "\1\u0097\37\uffff\1\u0097",
            "\1\u0098\37\uffff\1\u0098",
            "\1\u0099\37\uffff\1\u0099",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u009b\37\uffff\1\u009b",
            "\1\u009c\37\uffff\1\u009c",
            "\12\157",
            "\1\32\10\uffff\2\32\1\uffff\12\157\7\uffff\32\32\4\uffff\1"+
            "\32\1\uffff\32\32",
            "\1\32\10\uffff\2\32\1\uffff\12\160\7\uffff\4\32\1\102\25\32"+
            "\4\uffff\1\32\1\uffff\4\32\1\102\25\32",
            "\1\u009d\37\uffff\1\u009d",
            "\1\u009e\37\uffff\1\u009e",
            "\1\u009f\37\uffff\1\u009f",
            "\1\u00a0\37\uffff\1\u00a0",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u00a2\37\uffff\1\u00a2",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\u00a4\37\uffff\1\u00a4",
            "",
            "\1\u00a5\37\uffff\1\u00a5",
            "\1\u00a6\37\uffff\1\u00a6",
            "\1\u00a7\37\uffff\1\u00a7",
            "\1\u00a8\37\uffff\1\u00a8",
            "\1\u00a9\37\uffff\1\u00a9",
            "\1\u00aa\37\uffff\1\u00aa",
            "\1\u00ab\37\uffff\1\u00ab",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u00ad\37\uffff\1\u00ad",
            "\1\u00ae\37\uffff\1\u00ae",
            "\1\u00af\37\uffff\1\u00af",
            "\1\u00b0\37\uffff\1\u00b0",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u00b1\37\uffff\1\u00b1",
            "\1\u00b2\37\uffff\1\u00b2",
            "\1\u00b3\37\uffff\1\u00b3",
            "",
            "\1\u00b4\37\uffff\1\u00b4",
            "\1\u00b5\37\uffff\1\u00b5",
            "\1\u00b6\37\uffff\1\u00b6",
            "",
            "\1\u00b7\37\uffff\1\u00b7",
            "\1\u00b8\37\uffff\1\u00b8",
            "\1\u00b9\10\uffff\1\u00ba\26\uffff\1\u00b9\10\uffff\1\u00ba",
            "\1\u00bb\37\uffff\1\u00bb",
            "\1\u00bc\37\uffff\1\u00bc",
            "\1\u00bd\37\uffff\1\u00bd",
            "\1\u00be\37\uffff\1\u00be",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "",
            "\1\u00c3\37\uffff\1\u00c3",
            "\1\u00c4\37\uffff\1\u00c4",
            "\1\u00c5\37\uffff\1\u00c5",
            "\1\u00c6\37\uffff\1\u00c6",
            "\1\u00c7\37\uffff\1\u00c7",
            "\1\u00c8\37\uffff\1\u00c8",
            "",
            "\1\u00c9\37\uffff\1\u00c9",
            "\1\u00ca\37\uffff\1\u00ca",
            "\1\u00cb\37\uffff\1\u00cb",
            "\1\u00cc\37\uffff\1\u00cc",
            "\1\u00cd\37\uffff\1\u00cd",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u00cf\37\uffff\1\u00cf",
            "\1\u00d0\37\uffff\1\u00d0",
            "\1\u00d1\37\uffff\1\u00d1",
            "\1\u00d2\37\uffff\1\u00d2",
            "",
            "\1\106\37\uffff\1\106",
            "\1\u00d3\37\uffff\1\u00d3",
            "\1\u00d5\3\uffff\1\u00d4\33\uffff\1\u00d5\3\uffff\1\u00d4",
            "\1\u00d6\37\uffff\1\u00d6",
            "\1\u00d7\37\uffff\1\u00d7",
            "\1\u00d8\37\uffff\1\u00d8",
            "\1\u00d9\37\uffff\1\u00d9",
            "\1\u00da\37\uffff\1\u00da",
            "\1\u00db\37\uffff\1\u00db",
            "\1\u00dc\37\uffff\1\u00dc",
            "\1\u00dd\37\uffff\1\u00dd",
            "\1\u00de\37\uffff\1\u00de",
            "\1\u00df\37\uffff\1\u00df",
            "\1\u00e0\37\uffff\1\u00e0",
            "\1\u00e1\37\uffff\1\u00e1",
            "\1\u00e2\37\uffff\1\u00e2",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u00e4\37\uffff\1\u00e4",
            "",
            "",
            "",
            "",
            "\1\u00e5\37\uffff\1\u00e5",
            "\1\u00e6\37\uffff\1\u00e6",
            "\1\u00e7\37\uffff\1\u00e7",
            "\1\u00e8\37\uffff\1\u00e8",
            "\1\u00e9\37\uffff\1\u00e9",
            "\1\u00ea\37\uffff\1\u00ea",
            "\1\u00eb\37\uffff\1\u00eb",
            "\1\u00ec\37\uffff\1\u00ec",
            "\1\u00ed\37\uffff\1\u00ed",
            "\1\u00ee\37\uffff\1\u00ee",
            "\1\u00ef\37\uffff\1\u00ef",
            "",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u00f1\37\uffff\1\u00f1",
            "\1\u00f2\37\uffff\1\u00f2",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u00f4\37\uffff\1\u00f4",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u00f7\37\uffff\1\u00f7",
            "\1\u00f8\37\uffff\1\u00f8",
            "\1\u00f9\37\uffff\1\u00f9",
            "\1\u00fa\37\uffff\1\u00fa",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u00fd\6\uffff\1\u00fc\30\uffff\1\u00fd\6\uffff\1\u00fc",
            "\1\u00fe\37\uffff\1\u00fe",
            "\1\u00ff\37\uffff\1\u00ff",
            "\1\u0100\37\uffff\1\u0100",
            "\1\u0101\37\uffff\1\u0101",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u0104\37\uffff\1\u0104",
            "",
            "\1\u0105\37\uffff\1\u0105",
            "\1\u0106\37\uffff\1\u0106",
            "\1\u0107\37\uffff\1\u0107",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u0109\37\uffff\1\u0109",
            "\1\u010a\37\uffff\1\u010a",
            "\1\u010b\37\uffff\1\u010b",
            "\1\u010c\37\uffff\1\u010c",
            "\1\u010d\37\uffff\1\u010d",
            "\1\u010e\37\uffff\1\u010e",
            "\1\u010f\37\uffff\1\u010f",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "",
            "\1\u0112\1\uffff\1\u0111\35\uffff\1\u0112\1\uffff\1\u0111",
            "\1\u0113\37\uffff\1\u0113",
            "",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "",
            "",
            "\1\u0115\37\uffff\1\u0115",
            "\1\u0116\37\uffff\1\u0116",
            "\1\u0117\37\uffff\1\u0117",
            "\1\u0118\37\uffff\1\u0118",
            "",
            "\1\u0119\37\uffff\1\u0119",
            "\1\u011a\37\uffff\1\u011a",
            "\1\u011b\37\uffff\1\u011b",
            "\1\u011c\37\uffff\1\u011c",
            "\1\u011d\37\uffff\1\u011d",
            "\1\u011e\37\uffff\1\u011e",
            "",
            "",
            "\1\u011f\37\uffff\1\u011f",
            "\1\u0120\37\uffff\1\u0120",
            "\1\u0121\37\uffff\1\u0121",
            "\1\u0122\37\uffff\1\u0122",
            "",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u0124\37\uffff\1\u0124",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u0126\37\uffff\1\u0126",
            "\1\u0127\37\uffff\1\u0127",
            "\1\u0128\37\uffff\1\u0128",
            "\1\u0129\37\uffff\1\u0129",
            "",
            "\1\u012a\37\uffff\1\u012a",
            "\1\u012b\37\uffff\1\u012b",
            "\1\u012c\37\uffff\1\u012c",
            "",
            "\1\u012d\37\uffff\1\u012d",
            "\1\u012e\37\uffff\1\u012e",
            "\1\u012f\37\uffff\1\u012f",
            "\1\u0130\37\uffff\1\u0130",
            "\1\u0131\37\uffff\1\u0131",
            "\1\u0132\37\uffff\1\u0132",
            "\1\u0133\37\uffff\1\u0133",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u0135\37\uffff\1\u0135",
            "\1\u0136\37\uffff\1\u0136",
            "\1\u0137\37\uffff\1\u0137",
            "\1\u0138\37\uffff\1\u0138",
            "\1\u0139\37\uffff\1\u0139",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "",
            "\1\u013b\37\uffff\1\u013b",
            "",
            "\1\u013c\37\uffff\1\u013c",
            "\1\u013d\37\uffff\1\u013d",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\22\32\1\u013e\7\32"+
            "\4\uffff\1\32\1\uffff\22\32\1\u013e\7\32",
            "\1\u0140\37\uffff\1\u0140",
            "\1\u0141\37\uffff\1\u0141",
            "\1\u0142\37\uffff\1\u0142",
            "\1\u0143\37\uffff\1\u0143",
            "\1\u0144\37\uffff\1\u0144",
            "\1\u0145\37\uffff\1\u0145",
            "\1\u0146\37\uffff\1\u0146",
            "\1\u0147\37\uffff\1\u0147",
            "\1\u0148\37\uffff\1\u0148",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u014a\37\uffff\1\u014a",
            "",
            "\1\u014b\37\uffff\1\u014b",
            "\1\u014c\37\uffff\1\u014c",
            "\1\u014d\37\uffff\1\u014d",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u014f\37\uffff\1\u014f",
            "",
            "\1\u0150\37\uffff\1\u0150",
            "\1\u0151\37\uffff\1\u0151",
            "\1\u0152\37\uffff\1\u0152",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u0154\37\uffff\1\u0154",
            "\1\u0155\37\uffff\1\u0155",
            "\1\u0156\37\uffff\1\u0156",
            "\1\u0157\37\uffff\1\u0157",
            "\1\u0158\37\uffff\1\u0158",
            "\1\u0159\37\uffff\1\u0159",
            "\1\u015a\37\uffff\1\u015a",
            "\1\u015b\37\uffff\1\u015b",
            "",
            "\1\u015c\37\uffff\1\u015c",
            "\1\u015d\37\uffff\1\u015d",
            "\1\u015e\37\uffff\1\u015e",
            "\1\u0161\10\uffff\1\u015f\26\uffff\1\u0161\10\uffff\1\u0160",
            "",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u0164\37\uffff\1\u0164",
            "\1\u0165\37\uffff\1\u0165",
            "",
            "\1\u0166\37\uffff\1\u0166",
            "\1\u0167\37\uffff\1\u0167",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u0169\37\uffff\1\u0169",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u016b\37\uffff\1\u016b",
            "\1\u016c\37\uffff\1\u016c",
            "\1\u016d\37\uffff\1\u016d",
            "\1\u016e\37\uffff\1\u016e",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u0161\37\uffff\1\u0161",
            "\1\u0161\37\uffff\1\u0161",
            "\1\u0171\37\uffff\1\u0171",
            "",
            "",
            "\1\u0172\37\uffff\1\u0172",
            "\1\u0173\37\uffff\1\u0173",
            "\1\u0174\37\uffff\1\u0174",
            "\1\u0175\37\uffff\1\u0175",
            "",
            "\1\u0176\37\uffff\1\u0176",
            "",
            "\1\u0177\37\uffff\1\u0177",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u0179\37\uffff\1\u0179",
            "\1\u017a\37\uffff\1\u017a",
            "",
            "",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u017c\37\uffff\1\u017c",
            "\1\u017d\37\uffff\1\u017d",
            "\1\u017e\37\uffff\1\u017e",
            "\1\u017f\37\uffff\1\u017f",
            "\1\u0180\37\uffff\1\u0180",
            "\1\u0181\37\uffff\1\u0181",
            "",
            "\1\u0182\37\uffff\1\u0182",
            "\1\u0183\37\uffff\1\u0183",
            "",
            "\1\u0184\37\uffff\1\u0184",
            "\1\u0185\37\uffff\1\u0185",
            "\1\u0186\37\uffff\1\u0186",
            "\1\u0187\37\uffff\1\u0187",
            "\1\u0188\37\uffff\1\u0188",
            "\1\u0189\37\uffff\1\u0189",
            "\1\u018a\37\uffff\1\u018a",
            "\1\u018b\37\uffff\1\u018b",
            "\1\u018c\37\uffff\1\u018c",
            "\1\u018d\37\uffff\1\u018d",
            "\1\u018e\37\uffff\1\u018e",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u0190\37\uffff\1\u0190",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u0193\37\uffff\1\u0193",
            "\1\u0194\37\uffff\1\u0194",
            "\1\u0195\37\uffff\1\u0195",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "",
            "\1\u0197\37\uffff\1\u0197",
            "",
            "",
            "\1\u0198\37\uffff\1\u0198",
            "\1\u0199\37\uffff\1\u0199",
            "\1\u019a\37\uffff\1\u019a",
            "",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            "\1\u019e\37\uffff\1\u019e",
            "",
            "",
            "",
            "\1\u019f\37\uffff\1\u019f",
            "\1\u01a0\37\uffff\1\u01a0",
            "\1\32\10\uffff\2\32\1\uffff\12\32\7\uffff\32\32\4\uffff\1\32"+
            "\1\uffff\32\32",
            ""
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( CRYSTAL | TYPE | DIFFRACTIONDECAYMODEL | DDM | SIMPLE | LINEAR | LEAL | DECAYPARAM | ABSCOEFCALC | DUMMY | DEFAULT | AVERAGE | RD | PDB | DIMENSION | ANGLEP | ANGLEL | PIXELSPERMICRON | UNITCELL | NUMMONOMERS | NUMRESIDUES | NUMRNA | NUMDNA | PROTEINHEAVYATOMS | ELEMENT | SOLVENTHEAVYCONC | SOLVENTFRACTION | PDBNAME | WIREFRAMETYPE | MODELFILE | BEAM | FLUX | FWHM | ENERGY | KEV | FILE | PIXELSIZE | COLLIMATION | RECTANGULAR | CIRCULAR | HORIZONTAL | VERTICAL | WEDGE | EXPOSURETIME | ANGULARRESOLUTION | STARTOFFSET | TRANSLATEPERDEGREE | ROTAXBEAMOFFSET | FLOAT | COMMENT | WS | STRING );";
        }
    }
 

}