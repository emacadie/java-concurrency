package info.shelfunit.concurrency.venkatsbook.ch009;

import groovyx.gpars.dataflow.DataflowQueue;
import groovyx.gpars.group.DefaultPGroup;
import java.io.File;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class FileSizeJ {
  
    private final DataflowQueue<Integer> pendingFiles = new DataflowQueue<Integer>();
    private final DataflowQueue<Long> sizes = new DataflowQueue<Long>();
    private final DefaultPGroup group = new DefaultPGroup();

    public void findSize(File file) { 
	long size = 0;
	if(!file.isDirectory()) { 
	    size = file.length();
	} else { 
	    final File[] children = file.listFiles();
	    if (children != null) { 
		for( final File child : children ) {
		    if(child.isFile()) { 
			size += child.length();
		    } else { 
			pendingFiles.bind( 1 );
			group.task(   
			  new Runnable() {
			      public void run() {
				  findSize(child); 
			      }
			  });
		    }
		} // for
	    } // if (children != null)
	} // if (!file.isDirectory)
      
      pendingFiles.bind( -1 );
      sizes.bind( size );
  } // def findSize(File file)

    public long findTotalFileSize(final File file) { 
	pendingFiles.bind(1);
	group.task(   
	      new Runnable() {
		  public void run() {
		      findSize(file); 
		  }
	      });


	int filesToVisit = 0;
	long totalSize = 0;
	boolean someBool = true;
	try {
	    while(someBool) { 
		totalSize += (long) sizes.getVal();
		filesToVisit += ( pendingFiles.getVal() + pendingFiles.getVal() );
		if ( (filesToVisit == 0) ) { 
		    someBool = false;
		    // break; 
		}
	    }
	} catch ( InterruptedException ex ) { }
    
	return totalSize;
    } // def findTotalFileSize(File file) 
  
  public static void main( String[ ] args ) { 
      FileSizeJ fs = new FileSizeJ();
      long start = System.nanoTime();
      System.out.println("Calling findTotalFileSize with " + args[0]);
      long totalSize = fs.findTotalFileSize(new File(args[0]));
      System.out.println("Total size " + totalSize);
      System.out.println("Time taken: " + (System.nanoTime() - start) / 1.0e9);
      try {
	Thread.sleep(2000);
      } catch ( Exception e ) {}
  } // end main

} // FileSizeJ
