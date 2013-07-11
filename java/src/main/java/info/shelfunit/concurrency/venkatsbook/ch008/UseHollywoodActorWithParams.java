package info.shelfunit.concurrency.venkatsbook.ch008;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UseHollywoodActorWithParams {

    public static void main( final String[] args ) throws InterruptedException {
	ActorSystem system = ActorSystem.create("This-is-for-Tom-Hanks");
	try {
	    
	    Props props = Props.create(HollywoodActorWithParams.class, "Tom Hanks");
	    final ActorRef tomHanks = system.actorOf(props, "tom-hanks");

	    tomHanks.tell("James Lovell", tomHanks);
	    Thread.sleep(1000);
	    tomHanks.tell( new StringBuilder("Politics"), tomHanks );
	    Thread.sleep(1000);
	    tomHanks.tell("Forrest Gump", tomHanks);

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    system.shutdown();
	}
    } // end method main

} // end UseHollywoodActor
