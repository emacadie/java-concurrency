package info.shelfunit.concurrency.venkatsbook.ch008.typed02;

import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
// import akka.japi.Creator;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class UseEnergySource {

    public static void main( final String[] args ) throws InterruptedException {
	System.out.println( "Thread in main: " + Thread.currentThread().getName() );

	ActorSystem system = ActorSystem.create("This-seems-like-a-lot-of-work");
	
	final EnergySource energySource = 
	    TypedActor.get( system ).typedActorOf(
		 new TypedProps< EnergySourceImpl >(
		     EnergySource.class, EnergySourceImpl.class ) );

	System.out.println( "Energy units: " + energySource.getUnitsAvailable() );
	System.out.println( "Firing two requests for use energy" );
	energySource.useEnergy( 10 );
	energySource.useEnergy( 10 );
	System.out.println( "Fired two requests for use energy" );
	Thread.sleep( 100 );
	System.out.println( "Firing one more request for use energy" );
	energySource.useEnergy( 10 );

	Thread.sleep( 100 );
	System.out.println( "Energy units " + energySource.getUnitsAvailable() );
	System.out.println( "Usage: " + energySource.getUsageCount() );
	
	system.shutdown();
    } // end main

} // end UseEnergySource
