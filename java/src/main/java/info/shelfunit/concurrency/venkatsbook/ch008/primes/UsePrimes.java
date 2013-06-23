package info.shelfunit.concurrency.venkatsbook.ch008.primes;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

// More imports!
import akka.pattern.Patterns;
import scala.concurrent.Await;
import scala.concurrent.Future;
// import akka.dispatch.Futures;
// import scala.concurrent.duration.Duration;
import akka.util.Timeout;
// import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.UUID;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UsePrimes {

    public static void main( final String[] args ) throws InterruptedException {
	if ( args.length < 2 ) {
	    System.out.println( "Usage: number numberOfParts" );
	} else {
	    final long start = System.nanoTime();
	    final long count = countPrimes( Integer.parseInt( args[0] ), Integer.parseInt(args[ 1 ]) );
	    final long end = System.nanoTime();
	    System.out.println( "Number of Primes is " + count );
	    System.out.println( "Time taken: " + (end/start)/1.0e9 );
	} // if ( args.length < 2 ) 
	

	////////////////////////////////////////////////////////////
	

	
	// let's try another way
	try {
	    /*
	    Timeout timeout = new Timeout( 5 * 1000 );
	    // Timeout timeout = new Timeout(Duration.create( 5, "seconds") );
	    final ActorRef gypsyLady03 = system.actorOf(new Props(FortuneTeller.class), UUID.randomUUID().toString());
	    final ActorRef gypsyLady04 = system.actorOf(new Props(FortuneTeller.class), UUID.randomUUID().toString());
	    final ArrayList< String > futureStrings = new ArrayList< String >();
	    System.out.println( "About to ask gypsyLady03" );
	    Future< Object > future03 = Patterns.ask( gypsyLady03, "Hello03", 3 * 1000 );
	    System.out.println( "About to add gypsyLady03 to the list" );
	    futureStrings.add( ( String )  Await.result(future03, timeout.duration() ) );
	    System.out.println( "About to ask gypsyLady04" );
	    // was 3. seconds, now try 10
	    Future< Object > future04 = Patterns.ask( gypsyLady04, "Hello04", ( 3 * 1000 ) );
	    // perhaps this is the problem?
	    System.out.println( "About to add gypsyLady04 to the list" );
	    futureStrings.add( ( String )  Await.result(future04, timeout.duration() ) );
	    System.out.println( "Size of futureStrings: " + futureStrings.size() );

	    for ( String fString : futureStrings) {
		System.out.println( "Here is the result: " + fString );
	    } // end for
*/
	} catch ( Exception e ) {
	    System.out.println( "Exception: " + e.getMessage() );
	    e.printStackTrace();
	} finally {
	    // system.shutdown();
	}

    } // end method main

    public static int countPrimes(final int number, final int numberOfParts) {
	ActorSystem system = ActorSystem.create("This-seems-like-a-lot-of-work");

	final int chunksPerPartition = number / numberOfParts;
	final List< Future< ? > > results = new ArrayList< Future< ? > >();
	for ( int index = 0; index < numberOfParts; index++ ) {
	    final int lower = index * chunksPerPartition + 1;
	    final int upper = (index == numberOfParts - 1) ? number : lower + chunksPerPartition - 1;
	    final List< Integer > bounds = Collections.unmodifiableList( Arrays.asList(lower, upper) );
	    final ActorRef primeFinder = system.actorOf(new Props(Primes.class), UUID.randomUUID().toString());
	    results.add( Patterns.ask(primeFinder, bounds, 1000) );
	} // for ( int index = 0; index < numberOfParts; index++ ) 
	int count = 0;
	for (Future<?> result : results) {
	    count += ( Integer ) result.await().result().get(); 
	}
	system.shutdown();
	return count;
    } // countPrimes

} // end UseFortuneTeller
