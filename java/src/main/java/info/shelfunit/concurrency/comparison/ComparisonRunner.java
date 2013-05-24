package info.shelfunit.concurrency.comparison;

import java.util.UUID;
import java.util.Random;

public class ComparisonRunner {

    private String fString;
    private String sString;
    private String fcString;
    private String scString;

    private double fNum;
    private double sNum;
    private double fcNum;
    private double scNum;

    private long iterations;
    private String classToRun;

    public void setClassToRun( String ctr ) { classToRun = ctr; }
    public void setIterations( String i ) {
	iterations = new Long( i ).longValue();
    }

    public void runTheClass() {}

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

    }

    public static void main(final String[] args) {
	ComparisonRunner crun = new ComparisonRunner();
	crun.setClassToRun( args[ 0 ] );
	crun.setIterations( args[ 1 ] );
	crun.runTheClass();
    } // end method main

    // LockHolder.java  SynchronizedHolder.java
} // info.shelfunit.concurrency.comparison.ComparisonRunner
