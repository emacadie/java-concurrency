package src.main.java.info.shelfunit.concurrency.venkatsbook.ch008;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UseHollywoodActorWithParams {

    public static void main( final String[] args ) throws InterruptedException {
	try {
	ActorSystem system = ActorSystem.create("This-is-for-Tom-Hanks");
	// final ActorRef tomHanks = system.actorOf(new Props(HollywoodActorWithParams.class), "tom-hanks");

	final ActorRef tomHanks = system.actorOf(new Props(new UntypedActorFactory() {
		public UntypedActor create() {
		    return new HollywoodActorWithParams("Tom Hanks");
		}
	    }), "myactor");

	tomHanks.tell("James Lovell", tomHanks);
	Thread.sleep(1000);
	tomHanks.tell( new StringBuilder("Politics"), tomHanks );
	Thread.sleep(1000);
	tomHanks.tell("Forrest Gump", tomHanks);
	system.shutdown();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    } // end method main

} // end UseHollywoodActor
