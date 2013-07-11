package info.shelfunit.concurrency.venkatsbook.ch008;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

// More imports!
import akka.pattern.Patterns;
import scala.concurrent.Await;
import scala.concurrent.Future;
// import akka.dispatch.Futures;
// import scala.concurrent.duration.Duration;
import akka.util.Timeout;
// import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.UUID;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UseFortuneTeller {

    public static void main( final String[] args ) throws InterruptedException {
	ActorSystem system = ActorSystem.create("This-seems-like-a-lot-of-work");
	final ActorRef gypsyLady = system.actorOf(Props.create(FortuneTeller.class), UUID.randomUUID().toString());
	final ActorRef gypsyLady02 = system.actorOf(Props.create(FortuneTeller.class), UUID.randomUUID().toString());

	final ArrayList< Future< Object > > futures = new ArrayList< Future< Object > >();
	futures.add( Patterns.ask(gypsyLady, "Hello", 1000) );
	futures.add( Patterns.ask(gypsyLady02, "Hello again", 1000) );

	System.out.println( "Size of futures: " + futures.size() );
	System.out.println( "-----------------------------------" );
	
	// let's try another way
	try {
	    Timeout timeout = new Timeout( 5 * 1000 );
	    // Timeout timeout = new Timeout(Duration.create( 5, "seconds") );
	    final ActorRef gypsyLady03 = system.actorOf(Props.create(FortuneTeller.class), UUID.randomUUID().toString());
	    final ActorRef gypsyLady04 = system.actorOf(Props.create(FortuneTeller.class), UUID.randomUUID().toString());
	    final ArrayList< String > futureStrings = new ArrayList< String >();
	    System.out.println( "About to ask gypsyLady03" );
	    Future< Object > future03 = Patterns.ask( gypsyLady03, "Hello03", 3 * 1000 );
	    System.out.println( "About to add gypsyLady03 to the list" );
	    futureStrings.add( ( String )  Await.result(future03, timeout.duration() ) );
	    System.out.println( "About to ask gypsyLady04" );
	    // was 3. seconds, now try 10
	    Future< Object > future04 = Patterns.ask( gypsyLady04, "Hello04", ( 3 * 1000 ) );
	    // perhaps this is the problem?
	    System.out.println( "About to add gypsyLady04 to the list" );
	    futureStrings.add( ( String )  Await.result(future04, timeout.duration() ) );
	    System.out.println( "Size of futureStrings: " + futureStrings.size() );

	    for ( String fString : futureStrings) {
		System.out.println( "Here is the result: " + fString );
	    } // end for
	} catch ( Exception e ) {
	    System.out.println( "Exception: " + e.getMessage() );
	    e.printStackTrace();
	} finally {
	    system.shutdown();
	}

    } // end method main

} // end UseFortuneTeller
