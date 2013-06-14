package info.shelfunit.concurrency.comparison;

import java.util.UUID;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ComparisonRunner {

    private String fString;
    private String sString;
    private String fcString;
    private String scString;

    private double fNum;
    private double sNum;
    private double fcNum;
    private double scNum;

    private int iterations;
    private String classToRun;

    public void setClassToRun( String ctr ) { classToRun = ctr; }
    public String getClassToRun() { return classToRun; }
    public void setIterations( String i ) {
	iterations = new Integer( i ).intValue();
    }
    public int getIterations( ) { return iterations; }

    public ComparisonRunner() {
	fString = UUID.randomUUID().toString();
	sString = UUID.randomUUID().toString();
	fcString = fString + sString;
	scString = sString + fString;
	Random random = new Random();
	fNum = random.nextDouble() * new Long( random.nextLong() ).doubleValue();
	sNum = random.nextDouble() * new Long( random.nextLong() ).doubleValue();

	fcNum = Math.pow( fNum, sNum ); 
	scNum = Math.scalb( fNum, Math.round( new Double( sNum ).floatValue() ) );

    } // end constructor

    private void runSynchronizedHolder() {
	System.out.println( "About to run runSynchronizedHolder()" );
	BlockingQueue< SynchronizedHolder > theQueue = new ArrayBlockingQueue< SynchronizedHolder >( iterations );
	final long start = System.nanoTime();
	for ( int x = 0; x < iterations;  x++ ) {
	    // System.out.println( "About to do iteration " + x );
	    SynchronizedHolder sh = new SynchronizedHolder();
	    sh.setFirstString(fString);
	    sh.setSecondString(sString);
	    sh.setFirstNum(fNum);
	    sh.setSecondNum(sNum);
	    sh.setFirstConcatString();
	    sh.setSecondConcatString();
	    sh.setFirstCalcNum();
	    sh.setSecondCalcNum();
	} // for ( int x = 0; x < iterations; iterations++ ) 
	final long end = System.nanoTime();
	System.out.println( "Time taken for " + iterations + " SynchronizedHolder: " + (end - start)/1.0e9 );
    } // end runSynchronizedHolder

    private void runLockHolder() throws Exception {
	System.out.println( "About to run runLockHolder()" );
	BlockingQueue< LockHolder > theQueue = new ArrayBlockingQueue< LockHolder >( iterations );
	final long start = System.nanoTime();
	for ( int x = 0; x < iterations;  x++ ) {
	    // System.out.println( "About to do iteration " + x );
	    LockHolder lh = new LockHolder();
	    lh.setFirstString(fString);
	    lh.setSecondString(sString);
	    lh.setFirstNum(fNum);
	    lh.setSecondNum(sNum);
	    lh.setFirstConcatString();
	    lh.setSecondConcatString();
	    lh.setFirstCalcNum();
	    lh.setSecondCalcNum();
	} // for ( int x = 0; x < iterations; iterations++ ) 
	final long end = System.nanoTime();
	System.out.println( "Time taken for " + iterations + " LockHolder: " + (end - start)/1.0e9 );
    } // end runLockHolder

    private void runSingleLockHolder() throws Exception {
	System.out.println( "About to run runLockHolder()" );
	BlockingQueue< SingleLockHolder > theQueue = new ArrayBlockingQueue< SingleLockHolder >( iterations );
	final long start = System.nanoTime();
	for ( int x = 0; x < iterations;  x++ ) {
	    // System.out.println( "About to do iteration " + x );
	    SingleLockHolder slh = new SingleLockHolder();
	    slh.setFirstString(fString);
	    slh.setSecondString(sString);
	    slh.setFirstNum(fNum);
	    slh.setSecondNum(sNum);
	    slh.setFirstConcatString();
	    slh.setSecondConcatString();
	    slh.setFirstCalcNum();
	    slh.setSecondCalcNum();
	} // for ( int x = 0; x < iterations; iterations++ ) 
	final long end = System.nanoTime();
	System.out.println( "Time taken for " + iterations + " SingleLockHolder: " + (end - start)/1.0e9 );
    } // end runSingleLockHolder

    public void runTheClass() {
	try {
	    if ( this.classToRun.equals( "SynchronizedHolder" ) ) { this.runSynchronizedHolder(); }
	    if ( this.classToRun.equals( "LockHolder" ) ) { this.runLockHolder(); }
	    if ( this.classToRun.equals( "SingleLockHolder" ) ) { this.runSingleLockHolder(); }
	} catch ( Exception e ) {
	    e.printStackTrace();
	}
    }

    public static void main(final String[] args) {
	ComparisonRunner crun = new ComparisonRunner();
	crun.setClassToRun( args[ 0 ] );
	crun.setIterations( args[ 1 ] );
	System.out.println( "About to run class " + crun.getClassToRun() );
	System.out.println( "Iterations: " + crun.getIterations() );
	crun.runTheClass();
    } // end method main

} // info.shelfunit.concurrency.comparison.ComparisonRunner
