package info.shelfunit.concurrency.venkatsbook.ch008.refactoredFileSize;

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
       if ( toProcessFileNames && idleFileProcessors ) {
        def ifpSize01 = idleFileProcessors.size
        idleFileProcessors.first() << new FileToProcessGroovy(toProcessFileNames.first())
        def ifpSize02 = idleFileProcessors.size
        idleFileProcessors = idleFileProcessors.tail()
        def ifpSize03 = idleFileProcessors.size
        toProcessFileNames = toProcessFileNames.tail()
    // println("idleFileProcessors is a ${idleFileProcessors.class.name}")
    // println("idleFileProcessors sizes: 1: ${ifpSize01} 2: ${ifpSize02} 3: ${ifpSize03}")
    // println("toProcessFileNames is a ${toProcessFileNames.class.name}")
       }
      /*
        if ( !toProcessFileNames.isEmpty() && !idleFileProcessors.isEmpty() ) {
            idleFileProcessors.remove( 0 ).send( new FileToProcessGroovy( toProcessFileNames.remove( 0 ) ) )
        }
*/
    } // sendAFileToProcess
	
    void onMessage( RequestAFileGroovy message ) {
      // idleFileProcessors.add( sender )
      println "Message is a ${message.class.name}"
      println "Here is getSender: ${this.getSender()}"
      println "Here is sender: ${sender}"
      // idleFileProcessors.add( message.sender )
	    sendAFileToProcess()
    }

	void onMessage( FileProcessorGroovy message ) {
	  println "Here is sender: ${sender}"
	    idleFileProcessors.add( message )
	    sendAFileToProcess()
	}
	
	void onMessage( FileToProcessGroovy message ) {
	  println "Here is sender: ${sender}"
	    toProcessFileNames.add( message.fileName )
	    pendingNumberOfFilesToVisit += 1
	    sendAFileToProcess()
	}

	void onMessage( FileSizeGroovy message ) {
	  println "Here is sender: ${sender}"
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

