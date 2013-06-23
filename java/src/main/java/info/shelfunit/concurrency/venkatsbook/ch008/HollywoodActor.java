package info.shelfunit.concurrency.venkatsbook.ch008;

import akka.actor.UntypedActor;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class HollywoodActor extends UntypedActor {

    public void onReceive( final Object role ) {
	System.out.println( "Playing " + role + " from Thread " + Thread.currentThread().getName() );
    } // end onReceive

} // end HollywoodActor
