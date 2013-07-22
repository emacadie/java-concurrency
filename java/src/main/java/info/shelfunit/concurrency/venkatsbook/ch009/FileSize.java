package info.shelfunit.concurrency.venkatsbook.ch009;

import groovy.transform.Immutable;

import groovyx.gpars.actor.Actors;
import groovyx.gpars.actor.DefaultActor;
import groovyx.gpars.dataflow.DataflowQueue;
import groovyx.gpars.group.DefaultPGroup;
import java.io.File;
import java.util.concurrent.TimeUnit;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class FileSize {
  
    private final DataflowQueue pendingFiles = new DataflowQueue();
    private final DataflowQueue sizes = new DataflowQueue();
    private final DefaultPGroup group = new DefaultPGroup();

  def findSize(File file) { 
      int size = 0;
    if(!file.isDirectory()) { 
	size = file.length();
    } else { 
	File[] children = file.listFiles();
      if (children != null) { 
	  for( File child : children ) {
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
        }
      }
    }

    pendingFiles.bind( -1 );
    sizes.bind( size );
  } // def findSize(File file)

    public int findTotalFileSize(File file) { 
	pendingFiles.bind(1);
	group.task(   
	      new Runnable() {
		  public void run() {
		      findSize(file); 
		  }
	      });


	int filesToVisit = 0;
	long totalSize = 0;
	while(true) { 
	    totalSize += sizes.getVal();
	    if(!(filesToVisit += (pendingFiles.getVal() + pendingFiles.getVal()))) break;
										       }
    
	return totalSize;
    } // def findTotalFileSize(File file) 
  
  public static void main( String[ ] args ) { 
      FileSize fs = new FileSize();

      long start = System.nanoTime();
      System.out.println("Calling findTotalFileSize with " + args[0]);
      int totalSize = fs.findTotalFileSize(new File(args[0]));
      System.out.println("Total size " + totalSize);
      System.out.println("Time taken: " + (System.nanoTime() - start) / 1.0e9);
	Thread.sleep(2000);
  } // end main

} // FileSize

