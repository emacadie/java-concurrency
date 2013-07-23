package info.shelfunit.concurrency.venkatsbook.ch008.gpars;

import groovyx.gpars.dataflow.Promise;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UseFortuneTellerGPars {

    public void doStuff() throws InterruptedException, Throwable {
	final FortuneTellerGPars actor = new FortuneTellerGPars();
        actor.start();
	Promise<Object> p1 = actor.sendAndPromise("Bilbo");
	Promise<Object> p2 = actor.sendAndPromise("Baggins");
	Promise<Object> p3 = actor.sendAndPromise(new Double(2.2));

	Thread.sleep( 2 * 1000 );
	System.out.println( "p1 is a " + p1.getClass().getName() );
	System.out.println( "p1.get(): " + p1.get() );

	System.out.println( "p3 is a " + p3.getClass().getName() );
	System.out.println( "p3.get(): " + p3.get() );
	// Double d1 = p3.get();
	
	
    } // end doStuff

    public static void main( final String[] args ) throws InterruptedException {
	UseFortuneTellerGPars uftG = new UseFortuneTellerGPars();
	try {
	    uftG.doStuff();
	} catch ( Exception e ) {
	    e.printStackTrace();
	} catch ( Throwable t) {
	    t.printStackTrace();
	}
	
    } // end method main

} // end UseFortuneTellerGPars
