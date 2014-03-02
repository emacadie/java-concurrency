package info.shelfunit.concurrency.venkatsbook.ch008.fileSize;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ConcurrentFileSizeWAkka {

    public static void startFileProcessor( ActorSystem system, ActorRef sizeCollector ) {
	for ( int i = 0; i < 10; i++ ) {
	    System.out.println( "in the loop of ConcurrentFileSizeWAkka, and i == " + i );
	    final ActorRef fileProcessor = system.actorOf( Props.create( FileProcessor.class, sizeCollector ) );
	    fileProcessor.tell( sizeCollector, fileProcessor );
	}
    } // end startFileProcessor

    public static void main( final String[] args ) {
	
	ActorSystem system = ActorSystem.create( "Get-the-size" );
	final ActorRef sizeCollector = system.actorOf( Props.create( SizeCollector.class ) );
	System.out.println( "Here is the arg: " + args[ 0 ] );
	sizeCollector.tell( new FileToProcess( args[ 0 ] ), sizeCollector );
	startFileProcessor( system, sizeCollector );
		
    } // end main

} // ConcurrentFileSizeWAkka 
