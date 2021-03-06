package info.shelfunit.concurrency.venkatsbook.ch006.stm;

import scala.concurrent.stm.Ref.View;

import java.util.concurrent.Callable;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class CallableUseEnergy implements Callable< Boolean > {

    final private long units;
    final private long MAXLEVEL;
    final private View< Long > level;  
    final private View< Long > usageCount; 

    public CallableUseEnergy(final long unitsA, final View< Long > levelA, final View< Long > usageCountA, final long MAXLEVELA) {
	this.units = unitsA; 
	this.level = levelA;
	this.usageCount =  usageCountA;
	this.MAXLEVEL = MAXLEVELA;
    } // end constructor

    public Boolean call() {
	long currentLevel = level.get();
	if (units > 0 && currentLevel >= units) {
	    level.swap(currentLevel - units);
	    usageCount.swap(usageCount.get() + 1);
	    return true;
	} else {
	    return false;
	}
    } // end call

} // end SeparateEnergySource
