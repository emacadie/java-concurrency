package info.shelfunit.concurrency.venkatsbook.ch008.primes;

import akka.actor.UntypedActor;
import java.util.List;
import com.google.common.collect.ImmutableList;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class PrimesWithGuava extends UntypedActor {

    public void onReceive( final Object boundsList ) {

	System.out.println( "Primes " + hashCode() + " from Thread " + Thread.currentThread().getName() );
	System.out.println( "boundsList is a " + boundsList.getClass().getName() );
	// final List< Integer > bounds = ( List< Integer > ) boundsList;
	// final List< Integer > bounds = ( ImmutableList< Integer > ) boundsList;
	
	// final List< Integer > bounds = new ArrayList< Integer >();
	// bounds = List.class.cast( boundsList);

	final List< Integer > bounds = ImmutableList.class.cast( boundsList);
	// final List< Integer > bounds = ImmutableList.Builder.addAll( boundsList).build();

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

} // end PrimesWithGuava
