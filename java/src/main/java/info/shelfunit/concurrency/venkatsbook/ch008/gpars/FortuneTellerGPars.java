package info.shelfunit.concurrency.venkatsbook.ch008.gpars;

import groovyx.gpars.actor.DynamicDispatchActor;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class FortuneTellerGPars extends DynamicDispatchActor {

    public void onMessage( final String msg ) {
	System.out.println( "In onMessage for FortuneTellerGPars with String" );
	try {
	    replyIfExists( "Here's looking at you, " + msg );
	    System.out.println( "Just sent reply for String" );
	} catch ( Exception e ) {
	    throw e;
	}
    } // end onMessage

    public void onMessage( final Object msg ) {
	System.out.println( "In onMessage for FortuneTellerGPars with Object: " + msg.getClass().getName() );
	try {
	} catch ( Exception e ) {
	    throw e;
	}
    } // end onMessage

    public void onMessage( final Double msg ) {
	System.out.println( "In onMessage for FortuneTellerGPars with Double" );
	try {
	    replyIfExists( new Double( msg.doubleValue() * 2 ) );
	    System.out.println( "Just sent reply for Double" );
	} catch ( Exception e ) {
	    throw e;
	}
    } // onMessage 

    public void onMessage( final Planet msg ) {
	System.out.println( "In onMessage for FortuneTellerGPars with Planet" );
	try {
	    Planet plan2 = new Planet( ( msg.getMass() * 2 ), 
		new StringBuilder( msg.getName() ).reverse().toString(), 
		msg.getDateOfDiscovery()
	    );
	    for ( int x = 0; x < 10; x++ ) {
		System.out.println( "In onMessage for planet, with x == " + x );
		Thread.sleep( 1 * 1000 );
	    }
	    System.out.println( "About to send Planet reply" );
	    replyIfExists( plan2 );
	} catch ( Exception e ) {
	    System.out.println( "Exception in Planet message" );
	}
    } // end onMessage

} // end FortuneTellerGPars
