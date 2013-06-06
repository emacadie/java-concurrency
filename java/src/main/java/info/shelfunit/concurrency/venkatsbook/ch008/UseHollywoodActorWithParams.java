package src.main.java.info.shelfunit.concurrency.venkatsbook.ch008;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
// import src.main.java.info.shelfunit.concurrency.venkatsbook.ch008.HollywoodActorWithParams;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UseHollywoodActorWithParams {

    // no exception handling. How would you test this?
    public static ActorRef getNewHollywoodActorWithParams( ActorSystem system, final String theName, final String nameArg ) {
	return system.actorOf(new Props(new UntypedActorFactory() {
		public UntypedActor create() {
		    return new HollywoodActorWithParams(theName);
		}
	    }), 
	    nameArg
	);
    } // end getNewHollywoodActorWithParams() 

    public static void main( final String[] args ) throws InterruptedException {
	ActorSystem system = ActorSystem.create("This-is-for-Tom-Hanks");
	try {
	    
	    final ActorRef tomHanks = getNewHollywoodActorWithParams( system, "Tom Hanks", "tom-hanks");
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
