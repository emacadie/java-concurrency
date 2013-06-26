package info.shelfunit.concurrency.venkatsbook.ch008.primes;

// import akka.actor.Status.Failure;
// import akka.pattern.Patterns;
// import akka.actor.UntypedActor;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.UUID;
// import scala.concurrent.Future;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class PrimeFinder  {

    public static boolean isPrime( final int number ) {
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

    public static int countPrimesInRange( final int lower, final int upper ) {
	int count = 0;
	for ( int index = lower; index <= upper; index++ ) {
	    if ( isPrime( index ) ) {
		count += 1;
	    }
	}
	return count;
    } // countPrimesInRange()

} // end Primes
