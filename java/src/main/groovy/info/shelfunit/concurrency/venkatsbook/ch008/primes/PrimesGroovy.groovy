package info.shelfunit.concurrency.venkatsbook.ch008.primes;

import groovyx.gpars.actor.DynamicDispatchActor

import akka.actor.UntypedActor;
import java.util.List;
import info.shelfunit.util.ClassUtil;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class PrimesGroovy extends DynamicDispatchActor {

    public void onMessage( final Object boundsList ) {

        System.out.println( "Primes " + hashCode() + " from Thread " + Thread.currentThread().getName() );
    
        if ( new ClassUtil(boundsList).doesImplement( "java.util.List" ) ) {
            final List< Integer > bounds = ( List< Integer > ) boundsList;
        // boundsList is a java.util.Collections$UnmodifiableRandomAccessList 
            final int count = PrimeFinder.countPrimesInRange( bounds.get(0), bounds.get(1) );
    
            getSender().tell(count, getSelf());
        } // if ( cu.doesImplement( "java.util.List" ) ) 
    } // end onReceive

} // end Primes
