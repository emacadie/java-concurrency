package info.shelfunit.concurrency.venkatsbook.ch008.primes;

import groovyx.gpars.group.DefaultPGroup
import groovyx.gpars.actor.DynamicDispatchActor

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.ImmutableList;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UsePrimesGroovyWithGuava {
    
    public static void main( final String[] args ) throws InterruptedException {
        if ( args.length < 2 ) {
            System.out.println( "Usage: number numberOfParts" );
        } else {
            def group = new DefaultPGroup()
            try {
                
                final long start = System.nanoTime();
                final long count = countPrimes( Integer.parseInt( args[ 0 ] ), Integer.parseInt( args[ 1 ] ), Integer.parseInt( args[ 2 ] ), group );
                final long end = System.nanoTime();
                println( "Number of Primes is " + count );
                println( "Time taken: " + (end - start)/1.0e9 );
            } catch ( InterruptedException e ) {
            } finally {
                group.shutdown()
            }
        } // if ( args.length < 2 ) 
        println( "At the end of main" )

    } // end method main
    

    public static int countPrimes( final int number, final int numberOfParts, final int patternTimeout, group )
    throws InterruptedException {
        
        final int chunksPerPartition = number / numberOfParts;
        final results = []
        
        for ( int index = 0; index < numberOfParts; index++ ) {
            
            final int lower = index * chunksPerPartition + 1;
            final int upper  
            
            if ( index == numberOfParts - 1 ) {
                upper = number 
            } else { 
                upper = lower + chunksPerPartition - 1
            }
            
            def bounds2 =  new ImmutableList.Builder< Integer >()
            .add( lower )
            .add( upper )
            .build();
    
            final  primeFinder = new PrimesGroovyWithGuava().start()
            primeFinder.parallelGroup = group
            results.add( primeFinder.sendAndPromise( bounds2 ) )
            // Thread.sleep( 1 * 1000 );
        } // for ( int index = 0; index < numberOfParts; index++ )
        
        def answer = 0
        Thread.sleep( 10 * 1000 );
        results.each  { result ->
             
            try {
                answer += result.get()
            } catch ( Exception e ) {
                System.out.println( "Exception in for loop" );
                e.printStackTrace();
            }
        } // for ( Future< Object > result : results ) 
        println( "Loop over" );
        Thread.sleep( 2 * 1000 );
        
        return answer
    } // countPrimes

} // end UsePrimesWithGuava - line 104 - 84

