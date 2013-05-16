package info.shelfunit.concurrency.venkatsbook.ch004;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class NaivelyConcurrentTotalFileSize {

    private long getTotalSizeOfFilesInDir( final ExecutorService service, final File file ) 
    throws InterruptedException, ExecutionException, TimeoutException {
	if ( file.isFile() ) { return file.length(); }

	final File[] children = file.listFiles();
	long total = 0;
	if ( children != null ) {
	    final List< Future< Long > > partialTotalFutures = new ArrayList< Future< Long > >();

	    for ( final File child : children ) {
		partialTotalFutures.add(service.submit( new Callable< Long >() {
		    public Long call() throws InterruptedException, ExecutionException, TimeoutException {
			return getTotalSizeOfFilesInDir(service, child);
		    } // end call()
		} ) ); // add to the list
	     } // for ( final File child : children ) {
		    
	    for ( final Future< Long > partialTotalFuture : partialTotalFutures ) {
		total += partialTotalFuture.get( 100, TimeUnit.SECONDS );
	    } // for ( final Future< Long > partialTotalFuture : partialTotalFutures ) {
	} // end if (children != null)
	return total;
    } // end getTotalSizeOfFilesInDir

    private long getTotalSizeOfFile( final String fileName ) 
    throws InterruptedException, ExecutionException, TimeoutException {
	final ExecutorService service = Executors.newFixedThreadPool( 100 );
	try { return getTotalSizeOfFilesInDir( service, new File( fileName ) ); 
	} finally { service.shutdown(); }
    } // end long getTotalSizeOfFile() {

    public static void main( final String[] args ) throws InterruptedException, ExecutionException, TimeoutException {
	final long start = System.nanoTime();
	final long total = new NaivelyConcurrentTotalFileSize().
	    getTotalSizeOfFile( args[ 0 ] );
	final long end = System.nanoTime();
	System.out.println( "Total file size: " + total );
	System.out.println( "Time taken: " + ( end - start )/1.0e9 );
    } // end method main

} // end class info.shelfunit.concurrency.venkatsbook.ch004.TotalFileSizeSequential