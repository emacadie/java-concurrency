package info.shelfunit.concurrency.venkatsbook.ch008.gpars;

import groovyx.gpars.dataflow.Promise;
import java.util.Date;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UseFortuneTellerGPars {

    public void doStuff() throws InterruptedException, Throwable {
	final FortuneTellerGPars actor = new FortuneTellerGPars();
        actor.start();
	Promise< Object > p1 = actor.sendAndPromise( "Bilbo" );
	Promise< Object > p2 = actor.sendAndPromise( "Baggins" );
	Promise< Object > p3 = actor.sendAndPromise( new Double( 2.2 ) );

	Thread.sleep( 2 * 1000 );
	System.out.println( "p1 is a " + p1.getClass().getName() );
	System.out.println( "p1.get(): " + p1.get() );

	System.out.println( "p3 is a " + p3.getClass().getName() );
	System.out.println( "p3.get(): " + p3.get() );
	Double d1 = ( Double ) p3.get();
	System.out.println( "d1: " + d1.doubleValue() + " and it's a " + 
	    d1.getClass().getName()
	);
	actor.terminate();
    } // end doStuff

    public void doPlanetStuff() throws InterruptedException, Throwable {
	final FortuneTellerGPars actor = new FortuneTellerGPars();
        actor.start();
	Planet planet = new Planet( 2.2, "Mars", new Date() );
	System.out.println( planet.toString() );
	Promise< Object > p1 = actor.sendAndPromise(planet);
	Thread.sleep( 1 * 1000 );
	System.out.println( "p1 is a " + p1.getClass().getName() );
	System.out.println( "p1.isBound(): " + p1.isBound() );
	Planet planet2 = ( Planet ) p1.get();
	System.out.println( "p1.isBound(): " + p1.isBound() );
	System.out.println( planet2.toString() );
	actor.terminate();
    } // end doPlanetStuff

    public static void main( final String[] args ) throws InterruptedException {
	UseFortuneTellerGPars uftG = new UseFortuneTellerGPars();
	try {
	    uftG.doStuff();
	    System.out.println("----------------------------------");
	    uftG.doPlanetStuff();
	} catch ( Exception e ) {
	    e.printStackTrace();
	} catch ( Throwable t ) {
	    t.printStackTrace();
	}
	
    } // end method main

} // end UseFortuneTellerGPars
