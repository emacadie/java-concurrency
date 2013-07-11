package info.shelfunit.testing;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
// import akka.actor.UntypedActor;
// import akka.actor.UntypedActorFactory;
// import akka.routing.RoundRobinRouter;
// import scala.concurrent.duration.Duration;
// import java.util.concurrent.TimeUnit;

public class TestingRunner {

    public void doStuff() {
	ActorSystem system = ActorSystem.create("PiSystem");
	Class systemClass = system.getClass();
	System.out.println( "system is a " + systemClass.getName() );

	final ActorRef myActor = system.actorOf(Props.create(MyActor.class), "my-actor");
	myActor.tell( "say42", myActor );
    } // end doStuff
    
    public static void main( final String args[] ) {
	TestingRunner tr = new TestingRunner();
	tr.doStuff();
    } // end main

} // end TestingRunner
