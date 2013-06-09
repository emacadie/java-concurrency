package info.shelfunit.concurrency.venkatsbook.ch006;

import org.multiverse.api.references.*;
import static org.multiverse.api.StmUtils.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class MultiverseEnergySource {
    private final long MAXLEVEL = 100;
    
    // final TxnLong level = STMUtils.newTxnLong( new Long(MAXLEVEL) ); 
    // final View< Long > usageCount = STM.newRef( new Long( 0L ) ); 
    // final View< Boolean > keepRunning = STM.newRef( new Boolean(true) );
    private static final ScheduledExecutorService replenishTimer = Executors.newScheduledThreadPool( 10 );

    private MultiverseEnergySource() {}

    public void shutdownScheduledExecutorService() {
	replenishTimer.shutdown();
    }
    /*
    private void init() {
	replenishTimer.schedule( new Runnable() {
	    public void run() {
		replenish();
		if ( keepRunning.get() ) {
		    replenishTimer.schedule( this, 1, TimeUnit.SECONDS );
		}
	    } // end run		
	    }, 1, TimeUnit.SECONDS 
        );
    } // end init

    public static MultiverseEnergySource create() {
	final MultiverseEnergySource energySource = new MultiverseEnergySource();
	energySource.init();
	return energySource;
    }

    public void stopMultiverseEnergySource() { keepRunning.swap(false); }

    public long getUnitsAvailable() { return level.get(); }
    
    public long getUsageCount() {  return usageCount.get(); }

    public boolean useEnergy(final long units) {
	Boolean result = STM.atomic(
	    new CallableUseEnergy(units, level, usageCount,  MAXLEVEL)
	);
       return result;
    } // end useEnergy

    private void replenish() {
	STM.atomic( new ReplenishRunnable(MAXLEVEL, level) );
    } // end replenish
    */
} // end MultiverseEnergySource
