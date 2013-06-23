package info.shelfunit.concurrency.venkatsbook.ch008;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UseHollywoodActor {

    public static void main( final String[] args ) throws InterruptedException {
	ActorSystem system = ActorSystem.create("This-is-the-best-book-ever");
	final ActorRef scottBakula = system.actorOf(new Props(HollywoodActor.class), "NX-01");

	scottBakula.tell("Sam Beckett", scottBakula);
	// Thread.sleep(1000);
	scottBakula.tell( "Captain Archer", scottBakula );
	// Thread.sleep(1000);
	scottBakula.tell("That's ADMIRAL Archer to you, pal", scottBakula);
	system.shutdown();
    } // end method main

} // end UseHollywoodActor
