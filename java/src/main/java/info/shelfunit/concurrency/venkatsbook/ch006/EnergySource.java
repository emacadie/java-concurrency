package info.shelfunit.concurrency.venkatsbook.ch006;

import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class EnergySource {
    private final long MAXLEVEL = 100;
    // without calling .ref() at the end, you get a View, which does not
    // require a transaction. But isn't requiring a transaction the whole point?
    final Ref< Long > level = STM.newRef( new Long(MAXLEVEL) ).ref(); // new Ref< Long >( MAXLEVEL );
    final Ref< Long > usageCount = STM.newRef( new Long( 0L ) ).ref();
    final Ref< Boolean > keepRunning = STM.newRef( new Boolean(true) ).ref();
    private static final ScheduledExecutorService replenishTimer = Executors.newScheduledThreadPool( 10 );

    private EnergySource() {}

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

} // end EnergySource
