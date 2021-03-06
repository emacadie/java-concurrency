package info.shelfunit.concurrency.venkatsbook.ch006.stm;

import scala.concurrent.stm.Ref.View;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class ReplenishRunnable implements Runnable {
    
    private final long MAXLEVEL;
    final View< Long > level;
   
    public ReplenishRunnable(final long MAXLEVELA, final View< Long > levela) {
	this.MAXLEVEL = MAXLEVELA;
	this.level = levela;
    }

    public void run() {
	long currentLevel = level.get();
	if ( currentLevel < MAXLEVEL ) {
	    level.swap(currentLevel + 1);
	}
    } // end run

} // end SeparateEnergySource
