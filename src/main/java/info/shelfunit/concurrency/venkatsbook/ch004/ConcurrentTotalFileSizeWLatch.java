package info.shelfunit.concurrency.venkatsbook.ch004;

// from Programming Concurrency on the JVM by Venkat Subramaniam

import java.io.File;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ConcurrentTotalFileSizeWLatch {

    private ExecutorService service;
    final private AtomicLong pendingFileVisits = new AtomicLong();
    final private AtomicLong totalSize = new AtomicLong();
    final private CountDownLatch latch = new CountDownLatch(1);
    
    private void updateTotalSizeOfFilesInDir( final File file ) {
	long fileSize = 0;
	if ( file.isFile() ) { fileSize = file.length(); }
	else {
	    final File[] children = file.listFiles();
	    if ( children != null ) {
		for ( final File child : children ) {
		    if ( child.isFile() ) { fileSize += child.length(); }
		    else {
			pendingFileVisits.incrementAndGet();
			service.execute( new Runnable() {
			    public void run(){ updateTotalSizeOfFilesInDir( child ); }
			});
		    }
		} // for ( final File child : children ) 
	    } // if ( children != null ) 
	} // if/else ( file.isFile() )
	totalSize.addAndGet( fileSize );
	if ( pendingFileVisits.decrementAndGet() == 0 ) { latch.countDown(); }
	
    } // updateTotalSizeOfFilesInDir

    private long getTotalSizeOfFile( final String fileName ) 
    throws InterruptedException {
	service = Executors.newFixedThreadPool( 100 );
	pendingFileVisits.incrementAndGet();
	try {
	    updateTotalSizeOfFilesInDir( new File( fileName ) );
	    latch.await( 100, TimeUnit.SECONDS );
	    return totalSize.longValue();
	} finally { service.shutdown(); }
	
    } // getTotalSizeOfFile

    public static void main( final String[] args ) 
    throws InterruptedException {
	final long start = System.nanoTime();
	final long total = new ConcurrentTotalFileSizeWLatch().getTotalSizeOfFile( args[ 0 ] );
	final long end = System.nanoTime();
	System.out.println( "Total size: " + total );
	System.out.println( "Time taken: " + ( end - start )/1.0e9 );
    } // end method main


} // end class ConcurrentTotalFileSizeWLatch
