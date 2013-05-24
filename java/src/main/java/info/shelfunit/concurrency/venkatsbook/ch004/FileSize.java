package info.shelfunit.concurrency.venkatsbook.ch004;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class FileSize {
    private final static ForkJoinPool forkJoinPool = new ForkJoinPool();

    // Yo dawg, I hear you like Java concurrency
    // So I put a class inside your class
    private static class FileSizeFinder extends RecursiveTask< Long > {
	final File file;
	public FileSizeFinder( final File theFile ) {
	    file = theFile;
	} // public constructor in a private static inner class
	
	@Override 
	public Long compute() {
	    long size = 0;
	    if ( file.isFile() ) { size = file.length(); }
	    else {
		final File[] children = file.listFiles();
		if ( children != null ) {
		    List< ForkJoinTask< Long > > tasks = new ArrayList< ForkJoinTask< Long > >();
		    for ( final File child : children ) {
			if ( child.isFile() ) { size += child.length(); }
			else { tasks.add( new FileSizeFinder( child ) ); }
		    } // end for

		    for ( final ForkJoinTask< Long > task : invokeAll( tasks ) ) {
			size += task.join();
		    }

		} // if ( children != null ) 
	    } // if ( file.isFile ) / else
	    return size;
	}  // end compute
    } // end class FileSizeFinder

    public static void main( final String[] args ) {
	final long start = System.nanoTime();
	final long total = forkJoinPool.invoke( new FileSizeFinder( new File( args[ 0 ] ) ) );
	final long end = System.nanoTime();
	System.out.println( "Total size: " + total );
	System.out.println( "Time taken: " + (end - start)/1.0e9 );
    } // end main

} // end class FileSize
