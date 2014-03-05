package info.shelfunit.concurrency.venkatsbook.ch008.primes;

import groovyx.gpars.actor.DynamicDispatchActor

import akka.actor.UntypedActor;
import java.util.List;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList
import info.shelfunit.util.ClassUtil;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class PrimesGroovyWithGuava extends DynamicDispatchActor {

   
    public void onMessage( ImmutableList boundsList ) {

        println( "Primes " + hashCode() + " from Thread " + Thread.currentThread().getName() );
        println( "boundsList is a " + boundsList.getClass().getName() );
        def count = 0
        
        count = this.countPrimesInRange( boundsList.get( 0 ), boundsList.get( 1 ) );
        reply count
        
    } // end onMessage

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

} // end PrimesWithGuava

