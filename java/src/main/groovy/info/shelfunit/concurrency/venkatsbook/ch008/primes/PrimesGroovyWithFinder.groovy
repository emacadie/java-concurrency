package info.shelfunit.concurrency.venkatsbook.ch008.primes;

import java.util.List;
import groovyx.gpars.actor.DynamicDispatchActor

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class PrimesGroovyWithFinder extends DynamicDispatchActor {

    void onMessage( List boundsList ) {

        println( "Primes " + hashCode() + " from Thread " + Thread.currentThread().getName() );
        final int count = this.countPrimesInRange( boundsList.get( 0 ), boundsList.get( 1 ) );
        reply( count )
        
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
        println( "Leaving countPrimesInRange in " + hashCode() + " from Thread " + Thread.currentThread().getName() );
        return count;
    } // countPrimesInRange()

} // end PrimesWithFinder

