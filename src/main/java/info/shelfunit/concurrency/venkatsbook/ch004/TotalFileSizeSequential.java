package info.shelfunit.concurrency.venkatsbook.ch004;

import java.io.File;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class TotalFileSizeSequential {

    private long getTotalSizeOfFilesInDir( final File file ) {
	if ( file.isFile() ) { return file.length(); }

	final File[] children = file.listFiles();
	long total = 0;
	if ( children != null ) {
	    for ( final File child : children ) {
		total += getTotalSizeOfFilesInDir( child );
	    }
	}

	return total;
    } // end getTotalSizeOfFilesInDir

    public static void main( final String[] args ) {
	final long start = System.nanoTime();
	final long total = new TotalFileSizeSequential().
	    getTotalSizeOfFilesInDir( new File( args[ 0 ] ) );
	final long end = System.nanoTime();
	System.out.println( "Total file size: " + total );
	System.out.println( "Time taken: " + ( end - start )/1.0e9 );
    } // end method main

} // end class info.shelfunit.concurrency.venkatsbook.ch004.TotalFileSizeSequential