package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import java.util.ArrayList;
import java.util.List;


import groovyx.gpars.actor.DynamicDispatchActor

class SizeCollectorGroovy extends DynamicDispatchActor {
    // these are created and initialized upon instantiation
    // a list of files/directories to process
    def toProcessFileNames = []
    def idleFileProcessors = []
    private long pendingNumberOfFilesToVisit = 0L
    private long totalSize = 0L
    private long start = System.nanoTime()
    def finished = false
    void setFinished( arg ) { }

    void sendAFileToProcess() {
        if ( !toProcessFileNames.isEmpty() && !idleFileProcessors.isEmpty() ) {
            idleFileProcessors.remove( 0 ).send( new FileToProcessGroovy( toProcessFileNames.remove( 0 ) ) )
        }
    } // sendAFileToProcess
	
    // def onMessage( def requestAFileGroovy ) {    
	
 
    void onMessage( RequestAFileGroovy message ) {
        // the sender is a FileProcessor
        // println( "Message is ${message.getClass().getName()}, sender is a ${sender.getClass().getName()}" )
	    // idleFileProcessors.add( sender )
	    idleFileProcessors.add( message.fileProcessorGroovy )
	    sendAFileToProcess()
	}

	void onMessage( FileProcessorGroovy message ) {
	    // println( "Message is FileProcessorGroovy, sender is a ${sender.getClass().getName()}" )
	    // the sender is a FileProcessor
	    idleFileProcessors.add( message )
	    sendAFileToProcess()
	}
	
	void onMessage( FileToProcessGroovy message ) {
	    toProcessFileNames.add( message.fileName )
	    pendingNumberOfFilesToVisit += 1
	    sendAFileToProcess()
	}

	void onMessage( FileSizeGroovy message ) {
	    totalSize += message.size
	    pendingNumberOfFilesToVisit -= 1
	    if ( pendingNumberOfFilesToVisit == 0 ) {
            long end = System.nanoTime()
            println( "Total size is: " + totalSize )
            println( "Time taken is: " + ( end - start )/1.0e9  )
            finished = true
	    }
	}
    
} // end SizeCollector - line 51

