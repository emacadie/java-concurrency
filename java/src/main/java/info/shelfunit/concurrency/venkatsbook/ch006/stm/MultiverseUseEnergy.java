package info.shelfunit.concurrency.venkatsbook.ch006.stm;

import org.multiverse.api.references.*;
import org.multiverse.api.*;

import java.util.concurrent.Callable;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class MultiverseUseEnergy implements Callable< Boolean > {

    final private long units;
    final private long MAXLEVEL;
    final private TxnLong level;  
    final private TxnLong usageCount; 

    public MultiverseUseEnergy(final long unitsA, final TxnLong levelA, final TxnLong usageCountA, final long MAXLEVELA) {
	this.units = unitsA; 
	this.level = levelA;
	this.usageCount =  usageCountA;
	this.MAXLEVEL = MAXLEVELA;
    } // end constructor

    public Boolean call() {
	long currentLevel = level.get();
	if (units > 0 && currentLevel >= units) {
	    level.set(currentLevel - units);
	    usageCount.set(usageCount.get() + 1);
	    return true;
	} else {
	    return false;
	}
    } // end call

} // end MultiverseEnergy
