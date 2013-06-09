package info.shelfunit.concurrency.venkatsbook.ch006;

import org.multiverse.api.references.*;
import org.multiverse.api.*;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class MultiverseReplenish implements Runnable {
    
    private final long MAXLEVEL;
    final TxnLong level;
   
    public MultiverseReplenish(final long MAXLEVELA, final TxnLong levela) {
	this.MAXLEVEL = MAXLEVELA;
	this.level = levela;
    }

    public void run() {
	long currentLevel = level.get();
	if ( currentLevel < MAXLEVEL ) {
	    level.set(currentLevel + 1);
	}
    } // end run

} // end MultiverseReplenish
