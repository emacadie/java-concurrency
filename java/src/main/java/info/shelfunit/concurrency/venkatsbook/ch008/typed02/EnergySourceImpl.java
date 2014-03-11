package info.shelfunit.concurrency.venkatsbook.ch008.typed02;

import akka.actor.TypedActor.PreStart;
// import akka.actor.Scheduler;

// import scala.concurrent.duration.Duration;
// import java.util.concurrent.TimeUnit;


// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class EnergySourceImpl implements EnergySource, PreStart {
    
    private final long MAXLEVEL = 100L;
    private long level = MAXLEVEL;
    private long usageCount = 0;

    class Replenish {}

    public void preStart() {
        // Scheduler.schedule( TypedActor.self(), new Replenish(), 1, 1, TimeUnit.SECONDS );
	// Cancellable cancellable = system.scheduler().schedule(Duration.Zero(),
	//					      Duration.create(1, TimeUnit.SECONDS), this, new Replenish(),
	//						      system.dispatcher(), null);
    } // end preStart

    // Scheduler.schedule(receiverActor, messageToBeSent, initialDelayBeforeSending, delayBetweenMessages, timeUnit)
    //This will schedule to send the Tick-message
    //to the tickActor after 0ms repeating every 50ms
    // Cancellable cancellable = system.scheduler().schedule(Duration.Zero(),
    //						      Duration.create(50, TimeUnit.MILLISECONDS), tickActor, "Tick",
    //							      system.dispatcher(), null);



    public long getUnitsAvailable() { return level; }

    public long getUsageCount() { return usageCount; }

    public void useEnergy( final long units ) {
	if ( ( units > 0 ) && ( level - units >= 0 ) ) {
	    System.out.println( "Thread in useEnergy: " + Thread.currentThread().getName() );
	    level -= units;
	    usageCount++;
	} // if ( ( units > 0 ) && ( level - units >= 0 ) ) 
    } // useEnergy


    private void replenish() {
	System.out.println("Thread in replenish: " + Thread.currentThread().getName());
	if (level < MAXLEVEL) { 
	    level += 1;
	}
    }

} // end EnergySourceImpl
