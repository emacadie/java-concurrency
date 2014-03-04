package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import groovyx.gpars.group.DefaultPGroup
import groovyx.gpars.actor.DynamicDispatchActor

public class ConcurrentFileSizeWGPars {

    public static void startFileProcessor( pGroup, sizeCollector ) {
        for ( int i = 0; i < 10; i++ ) {
            println( "in the loop of ConcurrentFileSizeWAkka, and i == " + i );
            final fileProcessor = new FileProcessorGroovy( sizeCollector ).start() 
            // fileProcessor.send( sizeCollector );
            fileProcessor.parallelGroup = pGroup
        }
    } // end startFileProcessor

    public static void main( final String[] args ) {
	
        def pGroup = new DefaultPGroup()
        final sizeCollector = new SizeCollectorGroovy().start()
        sizeCollector.parallelGroup = pGroup
        println( "Here is the arg: " + args[ 0 ] );
        sizeCollector.send( new FileToProcessGroovy( args[ 0 ] ) );
        startFileProcessor( pGroup, sizeCollector );
		
    } // end main

} // ConcurrentFileSizeWGPars - line 31

