package info.shelfunit.concurrency.venkatsbook.ch009.fileSize;

import groovy.transform.Immutable;
import java.util.List;
import java.util.ArrayList;
// import groovyx.gpars.actor.Actors
// import groovyx.gpars.actor.DefaultActor
import groovyx.gpars.actor.DynamicDispatchActor;
// import java.util.concurrent.TimeUnit

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class SizeCollectorJ extends DynamicDispatchActor {
    
    ArrayList<String> toProcessFileNames = new ArrayList<String>();
    ArrayList<FileProcessorJ> idleFileProcessors = new ArrayList<FileProcessorJ>();
      int pendingNumberOfFilesToVisit = 0;
      private long  totalSize = 0L;
      final long start = System.nanoTime();

  private void sendAFileToProcess() {
    if (toProcessFileNames && idleFileProcessors) {
      idleFileProcessors.first() << new FileToProcessJ(toProcessFileNames.first())
      idleFileProcessors = idleFileProcessors.remove(0);
      toProcessFileNames = toProcessFileNames.remove(0);
    }
  } // sendAFileToProcess() 

    public void onMessage(RequestAFileJ message) {
	idleFileProcessors.add(sender);
	this.sendAFileToProcess();
    }

    public void onMessage(FileToProcessJ message) {
	toProcessFileNames.add(message.getFileName());
	pendingNumberOfFilesToVisit += 1;
	this.sendAFileToProcess();
    }

    public void onMessage(FileSizeJ message) {
	totalSize += message.getSize();
	pendingNumberOfFilesToVisit -= 1;
	if (pendingNumberOfFilesToVisit == 0) {
	    long end = System.nanoTime();
	    System.out.println("Total size is ${totalSize}");
	    System.out.println("Time taken is ${(end - start)/1.0e9}");
	    terminate();
	}
    }

    public void onMessage(Object message) { 
	println("Handling default message");
    }
    
} // end class SizeCollector 





