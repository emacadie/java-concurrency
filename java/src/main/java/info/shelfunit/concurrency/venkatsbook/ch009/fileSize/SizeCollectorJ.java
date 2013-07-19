package info.shelfunit.concurrency.venkatsbook.ch009.fileSize;

import java.util.ArrayList;
import groovyx.gpars.actor.DynamicDispatchActor;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class SizeCollectorJ extends DynamicDispatchActor {
    
    ArrayList<String> toProcessFileNames = new ArrayList<String>();
    ArrayList<FileProcessorJ> idleFileProcessors = new ArrayList<FileProcessorJ>();
    int pendingNumberOfFilesToVisit = 0;
    private long  totalSize = 0L;
    final long start = System.nanoTime();

    private void sendAFileToProcess() {
      
	if (!toProcessFileNames.isEmpty() && !idleFileProcessors.isEmpty()) {
	    FileProcessorJ fpj = idleFileProcessors.get(0);
	    fpj.send(new FileToProcessJ(toProcessFileNames.get(0)));
	    idleFileProcessors.remove(0);
	    toProcessFileNames.remove(0);
	} 
      
    } // sendAFileToProcess() 

    public void onMessage(RequestAFileJ message) {
	idleFileProcessors.add((FileProcessorJ)getSender());
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
	    System.out.println("Total size is " + totalSize);
	    System.out.println("Time taken is " + (end - start)/1.0e9 );
	    terminate();
	}
    }

    public void onMessage(Object message) { 
	System.out.println("Handling default message");
    }
    
} // end class SizeCollector 
