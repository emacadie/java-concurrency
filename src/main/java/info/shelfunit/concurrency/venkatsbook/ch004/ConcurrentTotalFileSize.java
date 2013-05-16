package info.shelfunit.concurrency.venkatsbook.ch004;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class ConcurrentTotalFileSize {
    class SubDirectoriesAndSize {
	final public long size;
	final public List< File > subDirectories;
	public SubDirectoriesAndSize( final long totalSize, final List< File > theSubDirs ) {
	    size = totalSize;
	    subDirectories = Collections.unmodifiableList( theSubDirs );
	}
    } // end class SubDirectoriesAndSize 

    private SubDirectoriesAndSize getTotalAndSubDirs( final File file ) {
	long total = 0;
	final List< File > subDirectories = new ArrayList< File >();
	if ( file.isDirectory() ) {
	    final File[] children = file.listFiles();
	    if ( children != null ) {
		for ( final File child : children ) {
		    if ( child.isFile() ) { total += child.length(); }
		    else { subDirectories.add( child ); }
		} // for
	    } // if
	} // if file.isDirectory
	return new SubDirectoriesAndSize( total, subDirectories );
    } // getTotalAndSubDirs( final File file ) 

    private long getTotalSizeOfFilesInDir( final File file ) 
    throws InterruptedException, ExecutionException, TimeoutException {
	final ExecutorService service = Executors.newFixedThreadPool( 100 );
	long total = 0;
	try {
	    final List< File > directories = new ArrayList< File >();
	    directories.add( file );
	    while ( !directories.isEmpty() ) {
		final List< Future< SubDirectoriesAndSize > > partialResults = new ArrayList< Future< SubDirectoriesAndSize > >();
		for ( final File directory : directories ) {
		    partialResults.add(
			service.submit( new Callable< SubDirectoriesAndSize >() {
			    public SubDirectoriesAndSize call() {
				return getTotalAndSubDirs( directory );
			    }
			} ) 
                    );
		} // for ( final File directory : directories ) 
		directories.clear();
		for ( final Future< SubDirectoriesAndSize > partialResultFuture : partialResults ) {
		    final SubDirectoriesAndSize sdas = partialResultFuture.get( 100, TimeUnit.SECONDS );
		    directories.addAll( sdas.subDirectories );
		    total += sdas.size;
		} // end for
	    } // while ( !directories.isEmpty() )
	} finally {
	    service.shutdown();
	}
	return total;
    } // getTotalSizeOfFiles() 

    public static void main( final String[] args ) 
    throws InterruptedException, ExecutionException, TimeoutException {
	final long start = System.nanoTime();
	final long total = new ConcurrentTotalFileSize().getTotalSizeOfFilesInDir( new File( args[ 0 ] ) );
	final long end  = System.nanoTime();
	System.out.println( "Total size: " + total );
	System.out.println( "Time taken: " + ( end -start )/1.0e9 );
    } // end main

} // end class package info.shelfunit.concurrency.venkatsbook.ch004.ConcurrentTotalFileSize