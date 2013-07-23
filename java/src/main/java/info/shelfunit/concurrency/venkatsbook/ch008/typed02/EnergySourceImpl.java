package info.shelfunit.concurrency.venkatsbook.ch008.typed02;

import akka.actor.TypedActor.PreStart;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class EnergySourceImpl implements EnergySource, PreStart {
    
    private final long MAXLEVEL = 100L;
    private long level = MAXLEVEL;
    private long usageCount = 0;

    class Replenish {}

    public void preStart() {
	// Scheduler.schedule( TypedActor.self(), new Replenish(), 1, 1, TimeUnit.SECONDS );
    } // end preStart

    public long getUnitsAvailable() { return level; }

    public long getUsageCount() { return usageCount; }

    public void useEnergy( final long units ) {
	if ( ( units > 0 ) && ( level - units >= 0 ) ) {
	    System.out.println( "Thread in useEnergy: " + Thread.currentThread().getName() );
	    level -= units;
	    usageCount++;
	} // if ( ( units > 0 ) && ( level - units >= 0 ) ) 
    } // useEnergy

} // end EnergySourceImpl
