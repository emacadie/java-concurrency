package info.shelfunit.concurrency.venkatsbook.ch004;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

// from Programming Concurrency on the JVM by Venkat Subramaniam

// I took the inner class from FileSize and put it in a new class


public class FileSizeWorker extends RecursiveTask< Long > {
	
    final File file;
    
    public FileSizeWorker( final File theFile ) {
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
		    else { tasks.add( new FileSizeWorker( child ) ); }
		} // end for

		for ( final ForkJoinTask< Long > task : invokeAll( tasks ) ) {
		    size += task.join();
		}

	    } // if ( children != null ) 
	} // if ( file.isFile ) / else
	return size;
    }  // end compute
} // end class FileSizeWorker


