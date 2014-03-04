package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import groovyx.gpars.group.DefaultPGroup
import groovyx.gpars.actor.DynamicDispatchActor

public class ConcurrentFileSizeWGPars {

    public static void startFileProcessor( group, sizeCollector ) {
        for ( int i = 0; i < 10; i++ ) {
            System.out.println( "in the loop of ConcurrentFileSizeWGPars, and i == " + i );
            def fileProcessor = new FileProcessorGroovy( sizeCollector ).start()
            fileProcessor.parallelGroup = group
            // fileProcessor.send( sizeCollector, fileProcessor );
        }
    } // end startFileProcessor

    public static void main( final String[] args ) {
	
        ActorSystem system = ActorSystem.create( "Get-the-size" );
        def group = new DefaultPGroup()
        def sizeCollector = new SizeCollectorGroovy().start()
        sizeCollector.parallelGroup = group
        System.out.println( "Here is the arg: " + args[ 0 ] );
        sizeCollector.send( new FileToProcessGroovy( args[ 0 ] ) );
        startFileProcessor( group, sizeCollector );
		
    } // end main

} // ConcurrentFileSizeWAkka - line 27

