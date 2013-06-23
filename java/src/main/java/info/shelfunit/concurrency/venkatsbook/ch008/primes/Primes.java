package info.shelfunit.concurrency.venkatsbook.ch008.primes;

import akka.actor.Status.Failure;
import akka.pattern.Patterns;
import akka.actor.UntypedActor;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import scala.concurrent.Future;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class Primes extends UntypedActor {

    public void onReceive( final Object boundsList ) {
	System.out.println( "In onReceive for Primes" );
	final List< Integer > bounds = ( List< Integer > ) boundsList;
	final int count = PrimeFinder.countPrimesInRange( bounds.get(0), bounds.get(1) );

	// try {
	getSender().tell(count, getSelf());

	    /* 
System.out.println( "Actor" + hashCode() + " from Thread " + Thread.currentThread().getName() );

	} catch ( Exception e ) {
	    getSender().tell(new Failure(e), getSelf());
	    throw e;
	}
	    */
    } // end onReceive

} // end Primes
