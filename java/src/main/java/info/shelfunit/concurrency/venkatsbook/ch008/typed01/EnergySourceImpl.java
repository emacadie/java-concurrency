package info.shelfunit.concurrency.venkatsbook.ch008.typed01;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public class EnergySourceImpl implements EnergySource {
    
    private final long MAXLEVEL = 100L;
    private long level = MAXLEVEL;
    private long usageCount = 0;

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
