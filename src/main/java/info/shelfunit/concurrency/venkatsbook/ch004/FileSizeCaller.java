package info.shelfunit.concurrency.venkatsbook.ch004;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

// from Programming Concurrency on the JVM by Venkat Subramaniam

// I took the inner class from FileSize and put it in a new class

public class FileSizeCaller {
    private final static ForkJoinPool forkJoinPool = new ForkJoinPool();


    public static void main( final String[] args ) {
	final long start = System.nanoTime();
	final long total = forkJoinPool.invoke( new FileSizeWorker( new File( args[ 0 ] ) ) );
	final long end = System.nanoTime();
	System.out.println( "Total size: " + total );
	System.out.println( "Time taken: " + (end - start)/1.0e9 );
    } // end main

} // end class FileSizeCaller
