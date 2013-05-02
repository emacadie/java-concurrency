package de.vogella.concurrency.callables;

/**
Based on code at http://vogella.com/articles/JavaConcurrency/article.htm
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutures {
    private static final int NTHREDS = 10;
    private static int iterations; 

    public static void setIterations() {
	if ( System.getProperties().containsKey( "iterations" ) ) {
	    System.out.println( "System has the key iterations" );
	} else {
	    System.out.println( "System does not have the key iterations" );
	}
	if ( System.getProperties().containsKey( "sysProp" ) ) {
	    System.out.println( "System has the key sysProp" );
	} else {
	    System.out.println( "System does not have the key sysProp" );
	}
	
	try {
	    iterations = new Integer( System.getProperty( "iterations" ) );
	} catch ( Exception e) {
	    e.printStackTrace();
	    iterations = 5; // some default value
	}
    }

    public static void main( String[] args ) {
	
	setIterations();

	ExecutorService executor = Executors.newFixedThreadPool( NTHREDS );
	List< Future< Long > > futureList = new ArrayList< Future< Long > >();
	for ( int i = 0; i < 20000; i++ ) {
	    Callable< Long > worker = new MyCallable();
	    Future< Long > submit = executor.submit( worker );
	    futureList.add( submit );
	}
	long sum = 0;
	System.out.println( "The size of the list is: " + futureList.size() );
	
	for ( Future< Long > future : futureList ) {
	    try {
		sum += future.get();
	    } catch ( InterruptedException e ) {
		e.printStackTrace();
	    } catch ( ExecutionException e ) {
		e.printStackTrace();
	    }
	} // for ( Future< Long > future : futureList ) {
	System.out.println( "The sum is: " + sum );
	executor.shutdown();
    } // end method main

} // end class de.vogella.concurrency.callables.CallableFutures
