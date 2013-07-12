package info.shelfunit.concurrency.venkatsbook.ch008.typed02;

// from Programming Concurrency on the JVM by Venkat Subramaniam   

public interface EnergySource {
    long getUnitsAvailable();
    long getUsageCount();
    void useEnergy( final long units );
} // end EnergySource
