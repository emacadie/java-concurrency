package info.shelfunit.concurrency.venkatsbook.ch008;

import akka.actor.Status.Failure;
import akka.actor.UntypedActor;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class FortuneTeller extends UntypedActor {

    public void onReceive( final Object name ) {
	System.out.println( "In onReceive for FortuneTeller" );
	try {
	    if (name instanceof String) {
		getSender().tell("Here's looking at you, " + name, getSelf());
	    } else {
		getSender().tell("The arg was not a String, but a " + name.getClass().getName(), getSelf());
	    }
	    
	} catch ( Exception e ) {
	    getSender().tell(new Failure(e), getSelf());
	    throw e;
	}

    } // end onReceive

} // end HollywoodActor
