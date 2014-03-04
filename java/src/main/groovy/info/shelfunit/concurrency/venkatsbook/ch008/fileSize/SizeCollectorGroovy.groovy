package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import groovyx.gpars.actor.DynamicDispatchActor

import java.util.ArrayList;
import java.util.List;

public class SizeCollectorGroovy extends DynamicDispatchActor {
    // these are created and initialized upon instantiation
    // a list of files/directories to process
    private final toProcessFileNames = [];
    
    private final idleFileProcessors = [];
    private long pendingNumberOfFilesToVisit = 0L;
    private long totalSize = 0L;
    private long start = System.nanoTime();

    void sendAFileToProcess() {
        if ( !toProcessFileNames.isEmpty() && !idleFileProcessors.isEmpty() ) {
            println( "toProcessFileNames[ 0 ]: ${toProcessFileNames[ 0 ]}"  )
            def ftpG = new FileToProcessGroovy( toProcessFileNames.remove( 0 ) )
            // println( "idleFileProcessors is a ${idleFileProcessors.getClass().getName()} size: ${idleFileProcessors.size}" )
            def first = idleFileProcessors.remove( 0 )
            println( "first is a ${first.getClass().getName()}" )
            first.send( ftpG )
            // idleFileProcessors.remove( 0 ).send( );
        }
    } // sendAFileToProcess
	
	void onMessage( RequestAFileGroovy rafG ) {
	    // the sender is a FileProcessor
	    // def sender = getSender()
	    // println("idleFileProcessors sender is a " + sender.class.name)
	    println( "sender is a ${sender.getClass().getName()}" )
	    idleFileProcessors.add( getSender() );
	    sendAFileToProcess();
	}
	
	void onMessage( FileProcessorGroovy message ) {
	    println( "SizeCollectorGroovy got a message that is a ${message.getClass().getName()}" )
	    idleFileProcessors.add( message );
	    sendAFileToProcess();
	}
	
	void onMessage( FileToProcessGroovy ftpG ) {
	    toProcessFileNames.add( ftpG.fileName );
	    pendingNumberOfFilesToVisit += 1;
	    sendAFileToProcess();
	}

	void onMessage( FileSizeGroovy fsg ) {
	    totalSize += fsg.size;
	    pendingNumberOfFilesToVisit -= 1;
	    println( "pendingNumberOfFilesToVisit: ${pendingNumberOfFilesToVisit}" )
	    if ( pendingNumberOfFilesToVisit == 0 ) {
            long end = System.nanoTime();
            println( "Total size is: " + totalSize );
            println( "Time taken is: " + ( end - start )/1.0e9  );
            this.parallelGroup.shutdown();
	    }

    } // onMessage
    
} // end SizeCollectorGroovy - line 53

