package info.shelfunit.concurrency.venkatsbook.ch008.primes;

import akka.actor.UntypedActor;
import java.util.List;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class Primes extends UntypedActor {

    public void onReceive( final Object boundsList ) {

	System.out.println( "Primes " + hashCode() + " from Thread " + Thread.currentThread().getName() );
	// final List< Integer > bounds = boundsList;
	final List< Integer > bounds = ( List< Integer > ) boundsList;
	// boundsList is a java.util.Collections$UnmodifiableRandomAccessList 
	final int count = PrimeFinder.countPrimesInRange( bounds.get(0), bounds.get(1) );

	getSender().tell(count, getSelf());

    } // end onReceive

} // end Primes
