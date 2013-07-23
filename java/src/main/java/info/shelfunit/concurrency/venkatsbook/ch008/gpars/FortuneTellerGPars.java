package info.shelfunit.concurrency.venkatsbook.ch008.gpars;

import groovyx.gpars.actor.DynamicDispatchActor;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class FortuneTellerGPars extends DynamicDispatchActor {

    public void onMessage( final String msg ) {
	System.out.println( "In onMessage for FortuneTellerGPars with String" );
	try {
	    replyIfExists("Here's looking at you, " + msg );
	    System.out.println( "Just sent reply for String" );
	} catch ( Exception e ) {
	    // getSender().tell(new Failure(e), getSelf());
	    throw e;
	}

    } // end onMessage

    public void onMessage( final Object msg ) {
	System.out.println( "In onMessage for FortuneTellerGPars with Object" );
	try {
	    // getSender().tell("The arg was not a String, but a " + msg.getClass().getName(), getSelf());
	   
	} catch ( Exception e ) {
	    // getSender().tell(new Failure(e), getSelf());
	    throw e;
	}

    } // end onMessage

    public void onMessage(final Double msg) {
		System.out.println( "In onMessage for FortuneTellerGPars with Double" );
	try {
	    replyIfExists( new Double(msg.doubleValue() * 2));
	    System.out.println( "Just sent reply for Double" );
	} catch ( Exception e ) {
	    // getSender().tell(new Failure(e), getSelf());
	    throw e;
	}
    } // onMessage 

} // end FortuneTellerGPars
