package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;

import groovyx.gpars.actor.DynamicDispatchActor

public class SizeCollectorGroovy extends DynamicDispatchActor {
    // these are created and initialized upon instantiation
    // a list of files/directories to process
    private List< String > toProcessFileNames = new ArrayList< String >();
    
    private List< ActorRef > idleFileProcessors = new ArrayList< ActorRef >();
    private long pendingNumberOfFilesToVisit = 0L;
    private long totalSize = 0L;
    private long start = System.nanoTime();

    public void sendAFileToProcess() {
        if ( !toProcessFileNames.isEmpty() && !idleFileProcessors.isEmpty() ) {
            idleFileProcessors.remove( 0 ).send( new FileToProcessGroovy( toProcessFileNames.remove( 0 ) )  );
        }
    } // sendAFileToProcess

    
	
	void onMessage( RequestAFileGroovy message ) {
	    // the sender is a FileProcessor
	    idleFileProcessors.add( getSender() );
	    sendAFileToProcess();
	}
	
	void onMessage( FileProcessorGroovy message ) {
	    // the sender is a FileProcessor
	    idleFileProcessors.add( message );
	    sendAFileToProcess();
	}
	
	void onMessage( FileToProcessGroovy message ) {
	    toProcessFileNames.add( message.fileName );
	    pendingNumberOfFilesToVisit += 1;
	    sendAFileToProcess();
	}

	void onMessage( FileSizeGroovy message ) {
	    totalSize += message.size;
	    pendingNumberOfFilesToVisit -= 1;
	    if ( pendingNumberOfFilesToVisit == 0 ) {
            long end = System.nanoTime();
            println( "Total size is: " + totalSize );
            println( "Time taken is: " + ( end - start )/1.0e9  );
            this.parallelGroup.shutdown();
	    }
	}
    
} // end SizeCollector - line 51

