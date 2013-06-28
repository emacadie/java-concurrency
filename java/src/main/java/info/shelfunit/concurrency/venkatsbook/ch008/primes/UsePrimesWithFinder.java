package info.shelfunit.concurrency.venkatsbook.ch008.primes;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

// More imports!
import akka.pattern.Patterns;
import scala.concurrent.Future;
import akka.util.Timeout;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.UUID;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UsePrimesWithFinder {

    public static void main( final String[] args ) throws InterruptedException {
	if ( args.length < 2 ) {
	    System.out.println( "Usage: number numberOfParts" );
	} else {
	    try {
		final long start = System.nanoTime();
		final long count = countPrimes( Integer.parseInt( args[0] ), Integer.parseInt(args[ 1 ]) );
		final long end = System.nanoTime();
		System.out.println( "Number of Primes is " + count );
		System.out.println( "Time taken: " + (end/start)/1.0e9 );
	    } catch ( InterruptedException e ) {
	    }
	} // if ( args.length < 2 ) 

    } // end method main

    public static int countPrimes(final int number, final int numberOfParts)
    throws InterruptedException {
	ActorSystem system = ActorSystem.create("This-seems-like-a-lot-of-work");
	Timeout timeout = new Timeout( 5 * 1000 );
	final int chunksPerPartition = number / numberOfParts;
	final List< Future< Object > > results = new ArrayList< Future< Object > >();
	for ( int index = 0; index < numberOfParts; index++ ) {
	    final int lower = index * chunksPerPartition + 1;
	    final int upper = (index == numberOfParts - 1) ? number : lower + chunksPerPartition - 1;
	    final List< Integer > bounds = Collections.unmodifiableList( Arrays.asList(lower, upper) );
	    final ActorRef primeFinder = system.actorOf(new Props(PrimesWithFinder.class), UUID.randomUUID().toString());
	    results.add( Patterns.ask(primeFinder, bounds, (2 * 1000) ) );
	    Thread.sleep( 1 * 1000 );
	} // for ( int index = 0; index < numberOfParts; index++ )
 
	int count = 0;
	IntegerFuture< Object > intFuture = new IntegerFuture< Object >();
	Thread.sleep( 10 * 1000 );
	for ( Future< Object > result : results ) {
	     
	    try {
		count += 0; 
		result.onSuccess(intFuture, system.dispatcher());
	    } catch ( Exception e ) {
		System.out.println( "Exception in for loop" );
		e.printStackTrace();
	    }
	} // for ( Future< Object > result : results ) 
	System.out.println( "Loop over" );
	Thread.sleep( 2 * 1000 );
	count = intFuture.getHoldInt();
	System.out.println("ending holdInt: " + intFuture.getHoldInt());
	system.shutdown();
	return count;
    } // countPrimes

} // end UsePrimesWithFinder - line 104
