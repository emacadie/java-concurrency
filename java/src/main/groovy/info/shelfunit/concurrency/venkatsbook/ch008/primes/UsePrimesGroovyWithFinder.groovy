package info.shelfunit.concurrency.venkatsbook.ch008.primes;

import groovyx.gpars.group.DefaultPGroup
import groovyx.gpars.actor.DynamicDispatchActor

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.UUID;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UsePrimesGroovyWithFinder {

    public static void main( final String[] args ) throws InterruptedException {
        if ( args.length < 2 ) {
            System.out.println( "Usage: number numberOfParts" );
        } else {
            try {
                final long start = System.nanoTime()
                def group = new DefaultPGroup()
                final long count = countPrimes( Integer.parseInt( args[ 0 ] ), Integer.parseInt( args[ 1 ] ), Integer.parseInt( args[ 2 ] ), group )
                final long end = System.nanoTime()
                println( "Number of Primes is " + count )
                println( "Time taken: " + (end - start)/1.0e9 )
                group.shutdown()
            } catch ( InterruptedException e ) {
            }
        } // if ( args.length < 2 ) 

    } // end method main

    public static int countPrimes(final int number, final int numberOfParts, final int patternTimeout, DefaultPGroup group )
    throws InterruptedException {
        
        final int chunksPerPartition = number / numberOfParts;
        def results = []
        for ( int index = 0; index < numberOfParts; index++ ) {
            final int lower = index * chunksPerPartition + 1;
            final int upper = (index == numberOfParts - 1) ? number : lower + chunksPerPartition - 1;
            def bounds = [ lower, upper ] // Collections.unmodifiableList( Arrays.asList(lower, upper) );
            def primeFinder = new PrimesGroovyWithFinder().start()
            primeFinder.parallelGroup = group
            results.add( primeFinder.sendAndPromise( bounds ) ) 
            
        } // for ( int index = 0; index < numberOfParts; index++ )
     
        println( "Loop starting" );
        
        def answer = 0
        results.each  { result ->
            try {
                answer += result.get()
            } catch ( Exception e ) {
                println( "Exception in for loop" )
                e.printStackTrace();
            }
        } // for ( Future< Object > result : results ) 
        println( "Loop over" );
        // Thread.sleep( 2 * 1000 );
        return answer
    } // countPrimes

} // end UsePrimesWithFinder - line 73


