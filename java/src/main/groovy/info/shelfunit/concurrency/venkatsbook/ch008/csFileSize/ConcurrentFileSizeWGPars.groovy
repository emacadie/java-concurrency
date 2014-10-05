package info.shelfunit.concurrency.venkatsbook.ch008.csFileSize;

import groovyx.gpars.group.DefaultPGroup
import groovyx.gpars.actor.DynamicDispatchActor

class ConcurrentFileSizeWGPars {

    def static void startFileProcessor( group, sizeCollector ) {
        for ( int i = 0; i < 10; i++ ) {
            def fileProcessor = new FileProcessorGroovy( sizeCollector ).start()
            fileProcessor.parallelGroup = group
        }
        while ( !sizeCollector.finished ) {
            Thread.sleep( 1 * 1000 )
        }
        group.shutdown()
    } // end startFileProcessor

    def static void main( final String[] args ) {
	
        def group = new DefaultPGroup()
        def sizeCollector = new SizeCollectorGroovy().start()
        sizeCollector.parallelGroup = group
        println( "Here is the arg: " + args[ 0 ] );
        sizeCollector.send( new FileToProcessGroovy( args[ 0 ] ) )
        startFileProcessor( group, sizeCollector )
		
    } // end main

} // ConcurrentFileSizeWGPars - line 27

