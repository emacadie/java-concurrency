package info.shelfunit.concurrency.venkatsbook.ch008.csFileSize;

import java.util.ArrayList;
import java.util.List;
import groovy.transform.CompileStatic

import groovyx.gpars.actor.DynamicDispatchActor

@CompileStatic
class SizeCollectorGroovy extends DynamicDispatchActor {
    // these are created and initialized upon instantiation
    // a list of files/directories to process
    List< FileToProcessGroovy > toProcessFileNames = []
    List< FileProcessorGroovy > idleFileProcessors = []
    private long pendingNumberOfFilesToVisit = 0L
    private long totalSize = 0L
    private long start = System.nanoTime()
    def finished = false
    void setFinished( arg ) { }

    void sendAFileToProcess() {
        if ( !toProcessFileNames.isEmpty() && !idleFileProcessors.isEmpty() ) {
            idleFileProcessors.remove( 0 ).send( new FileToProcessGroovy( toProcessFileNames.remove( 0 ).fileName ) )
        }
    } // sendAFileToProcess
	
    void onMessage( RequestAFileGroovy message ) {
	    idleFileProcessors.add( message.fileProcessorGroovy )
	    // println "Here is RequestAFileGroovy sender: ${sender}"
	    sendAFileToProcess()
	}

	void onMessage( FileProcessorGroovy message ) {
	    idleFileProcessors.add( message )
	    // println "Here is FileProcessorGroovy  sender: ${sender}"
	    sendAFileToProcess()
	}
	
	void onMessage( FileToProcessGroovy message ) {
	  toProcessFileNames.add( message ) // .fileName )
	    pendingNumberOfFilesToVisit += 1
	    // println "Here is FileToProcessGroovy sender: ${sender}"
	    sendAFileToProcess()
	}

	void onMessage( FileSizeGroovy message ) {
	  // println "Here is FileSizeGroovy sender: ${sender}"
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

