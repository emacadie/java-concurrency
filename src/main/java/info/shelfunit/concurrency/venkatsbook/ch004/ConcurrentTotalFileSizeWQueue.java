package info.shelfunit.concurrency.venkatsbook.ch004;

import java.io.File;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class ConcurrentTotalFileSizeWQueue {
    private ExecutorService service;
    final private BlockingQueue< Long > fileSizes = new ArrayBlockingQueue< Long >( 500 );
    final AtomicLong pendingFileVisits = new AtomicLong();

    private void startExploreDir( final File file ) {
	pendingFileVisits.incrementAndGet();
	service.execute( new Runnable() {
	    public void run() { exploreDir( file ); }
	});
    } // end startExploreDir

    private void exploreDir( final File file ) {
	long fileSize = 0;
	if ( file.isFile() ) { fileSize = file.length(); }
	else {
	    final File[] children = file.listFiles();
	    if ( children != null ) {
		for ( final File child : children ) {
		    if ( child.isFile() ) { fileSize += child.length(); }
		    else { startExploreDir( child ); }
		} // for ( final File child : children ) 
	    } // if ( children != null )
	} // end if ( file.isFile() ) / else
	try { fileSizes.put( fileSize );
	} catch ( Exception ex ) { throw new RuntimeException( ex ); }
	pendingFileVisits.decrementAndGet();
    } // end exploreDir

    private long getTotalSizeOfFile( final String fileName ) 
    throws InterruptedException{
	service = Executors.newFixedThreadPool( 100 );
	try {
	    startExploreDir( new File( fileName ) );
	    long totalSize = 0;
	    // long timesPolled = 0;
	    while ( pendingFileVisits.get() > 0 || fileSizes.size() > 0 ) {
		final Long size = fileSizes.poll( 10, TimeUnit.SECONDS );
		totalSize += size;
		// System.out.println( "timesPolled: " + ( ++timesPolled ) );
	    } // while ( pendingFileVisits.get() > 0 || fileSizes.size() > 0 )
	    return totalSize;
	} finally { service.shutdown(); }
    } // getTotalSizeOfFile()

    public static void main( final String[] args ) throws InterruptedException {
	final long start = System.nanoTime();
	final long total = new ConcurrentTotalFileSizeWQueue().getTotalSizeOfFile( args[ 0 ] );
	final long end = System.nanoTime();
	System.out.println( "Total size: " + total );
	System.out.println( "Time taken: " + (end - start)/1.0e9 );
    } // end main

} // ConcurrentTotalFileSizeWQueue
