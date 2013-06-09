package info.shelfunit.concurrency.venkatsbook.ch006.stm;

import org.multiverse.api.references.*;
// import static org.multiverse.api.StmUtils.*;
import org.multiverse.api.*;
import org.multiverse.stms.gamma.transactions.fat.*;
import org.multiverse.stms.gamma.GammaStm;
import org.multiverse.stms.gamma.transactionalobjects.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class MultiverseEnergySource {
    private final long MAXLEVEL = 100;
    
    final TxnLong level = StmUtils.newTxnLong( new Long(MAXLEVEL) ); 
    final TxnLong usageCount = StmUtils.newTxnLong( new Long( 0L ) ); 
    final TxnBoolean keepRunning = StmUtils.newTxnBoolean( true );
    private static final ScheduledExecutorService replenishTimer = Executors.newScheduledThreadPool( 10 );

    private MultiverseEnergySource() {}

    public void shutdownScheduledExecutorService() {
	replenishTimer.shutdown();
    }
    
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

    // Would this be reallly slow? - 
    // I can't just use set() because that needs a transaction
    // I thought you would need to wrap it in StmUtils, but this does not throw an exception
    public void stopMultiverseEnergySource() { 
	new Runnable() { public void run() { keepRunning.set(false); } };
    } // line 57

    public long getUnitsAvailable() { return level.atomicGet( ); }
    
    public long getUsageCount() {  return usageCount.atomicGet(); }

    public boolean useEnergy(final long units) {
	Boolean result = StmUtils.atomic(
	    new MultiverseUseEnergy(units, level, usageCount,  MAXLEVEL)
	);
       return result;
    } // end useEnergy

    private void replenish() {
	StmUtils.atomic( new MultiverseReplenish(MAXLEVEL, level) );
    } // end replenish
    
} // end MultiverseEnergySource
