package src.main.java.info.shelfunit.concurrency.venkatsbook.ch008;

import akka.actor.UntypedActor;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class HollywoodActorWithParams extends UntypedActor {

    private final String name;

    public HollywoodActorWithParams( final String theName ) {
	name = theName;
    }

    public void onReceive( final Object role ) {
	if ( role instanceof String ) {
	    System.out.println( "Playing " + role + " from Thread " + Thread.currentThread().getName() );
	} else {
	    System.out.println(name + " plays no " + role);
	}
    } // end onReceive

} // end HollywoodActorWithParams
