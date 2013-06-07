package info.shelfunit.concurrency.venkatsbook.ch006;

import scala.concurrent.stm.Ref;
import scala.concurrent.stm.Ref.View;
import scala.concurrent.stm.japi.STM;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class EnergySource {
    private final long MAXLEVEL = 100;
    // if you call STM.newRef(xxx).ref(), you get a Ref
    // without calling .ref() at the end, you get a View, which does not
    // require a transaction. But isn't requiring a transaction the whole point?
    // at least, I think that is what it says
    final View< Long > level = STM.newRef( new Long(MAXLEVEL) ); // new Ref< Long >( MAXLEVEL );
    final View< Long > usageCount = STM.newRef( new Long( 0L ) ); // .ref();
    final View< Boolean > keepRunning = STM.newRef( new Boolean(true) );
    private static final ScheduledExecutorService replenishTimer = Executors.newScheduledThreadPool( 10 );

    private EnergySource() {}
    /*
    private void init() {
	replenishTimer.schedule( new Runnable() {
	    public void run() {
		replenish();
		if ( keepRunning.get() ) {
		    replenishTimer.schedule( this, 1, TimeUnit.SECONDS );
		}
	    }
	    }, 1, TimeUnit.SECONDS 
        );
    }

    public static EnergySource create() {
	final EnergySource energySource = new EnergySource();
	energySource.init();
	return energySource;
    }

    public void stopEnergySource() { keepRunning.swap(false); }

    public long getUnitsAvailable() { return level.get(); }
    
    public long getUsageCount() {  return usageCount.get(); }

    public boolean useEnergy(final long units) {
	STM.atomic<Boolean>() {
	    public Boolean atomically() {
		long currentLevel = level.get();
		if(units > 0 && currentLevel >= units) {
		    level.swap(currentLevel - units);
		    usageCount.swap(usageCount.get() + 1);
		    return true;
		} else {
		    return false;
		}
	    }
	}.execute();
    }
*/
} // end EnergySource
