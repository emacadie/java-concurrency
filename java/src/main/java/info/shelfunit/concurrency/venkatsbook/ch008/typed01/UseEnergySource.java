package info.shelfunit.concurrency.venkatsbook.ch008.typed01;

import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
import akka.japi.Creator;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UseEnergySource {

    public static void main( final String[] args ) throws InterruptedException {
	System.out.println( "Thread in main: " + Thread.currentThread().getName() );

	ActorSystem system = ActorSystem.create("This-seems-like-a-lot-of-work");
	
	final EnergySource energySource = 
	    TypedActor.get(system).typedActorOf(
		new TypedProps<EnergySourceImpl>(EnergySource.class,
		    new Creator<EnergySourceImpl>() {
			public EnergySourceImpl create() { 
			    return new EnergySourceImpl("foo"); }
			}),
		"name");
	System.out.println( "Energy units: " + energySource.getUnitsAvailable() );


    } // end main

} // end UseEnergySource
