package info.shelfunit.concurrency.venkatsbook.ch008.primes;

import akka.actor.UntypedActor;
import java.util.List;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class PrimesWithFinder extends UntypedActor {

    public void onReceive( final Object boundsList ) {

	System.out.println( "Primes " + hashCode() + " from Thread " + Thread.currentThread().getName() );
	final List< Integer > bounds = ( List< Integer > ) boundsList;
	final int count = this.countPrimesInRange( bounds.get(0), bounds.get(1) );
	getSender().tell(count, getSelf());

    } // end onReceive

    private boolean isPrime( final int number ) {
	if ( number <= 1 ) {
	    return false;
	}
	final int limit = ( int ) Math.sqrt( number );
	for ( int i = 2; i <= limit; i++ ) {
	    if ( number % i ==0 ) {
		return false;
	    }
	}
	return true;
    } // isPrime

    public int countPrimesInRange( final int lower, final int upper ) {
	int count = 0;
	for ( int index = lower; index <= upper; index++ ) {
	    if ( this.isPrime( index ) ) {
		count += 1;
	    }
	}
	System.out.println( "Leaving countPrimesInRange in " + hashCode() + " from Thread " + Thread.currentThread().getName() );
	return count;
    } // countPrimesInRange()

} // end Primes
