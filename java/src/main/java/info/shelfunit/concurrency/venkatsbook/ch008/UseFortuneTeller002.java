package info.shelfunit.concurrency.venkatsbook.ch008;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import akka.pattern.Patterns;
import scala.concurrent.Future;
import java.util.ArrayList;
import java.util.UUID;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UseFortuneTeller002 {

    public static void main( final String[] args ) throws InterruptedException {
	ActorSystem system = ActorSystem.create("This-seems-like-a-lot-of-work");
	final ActorRef gypsyLady = system.actorOf(Props.create(FortuneTeller.class), UUID.randomUUID().toString());
	final ActorRef gypsyLady02 = system.actorOf(Props.create(FortuneTeller.class), UUID.randomUUID().toString());

	final ArrayList< Future< Object > > futures = new ArrayList< Future< Object > >();
	futures.add( Patterns.ask(gypsyLady, "Hello", 1000) );
	futures.add( Patterns.ask(gypsyLady02, "Hello again", 1000) );

	System.out.println( "Size of futures: " + futures.size() );


	for ( Future< Object > fString : futures) {
	    System.out.println( "In the enhanced for loop" );
	    fString.onSuccess(new PrintResult< Object >(), system.dispatcher());
	} // end for

	
	System.out.println( "-----------------------------------" );

	system.shutdown();
    } // end method main

} // end UseFortuneTeller
