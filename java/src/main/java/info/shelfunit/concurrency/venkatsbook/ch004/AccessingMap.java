package info.shelfunit.concurrency.venkatsbook.ch004;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class AccessingMap {
    private static void useMap( final Map< String, Integer > scores ) 
    throws InterruptedException{
	scores.put( "Fred", 10 );
	scores.put( "Sara", 12 );

	try {
	    for ( final String key : scores.keySet() ) {
		System.out.println( key + " score " + scores.get( key ) );
		scores.put( "Joe", 14 );
	    }
	} catch ( Exception ex ) {
	    System.out.println( "Failed: " + ex );
	}

	System.out.println( "elements in the map: " + scores.keySet().size() );
	System.out.println( "----------------------------------------\n\n" );
	
    } // end useMap

    public static void main( final String[] args ) throws InterruptedException {
	System.out.println( "Using plain vanilla HashMap" );
	useMap( new HashMap< String, Integer >() );

	System.out.println( "Using synchronized HashMap" );
	useMap( Collections.synchronizedMap( new HashMap< String, Integer >() ) );

	System.out.println( "Using plain concurrent HashMap" );
	useMap( new ConcurrentHashMap< String, Integer >() );
    } // end main
} // end class AccessingMap
