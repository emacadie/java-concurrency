package info.shelfunit.concurrency.cookbook.chapter08;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

// from Java 7 Concurrency Cookbook by Javier Fernández González

public class MyLock extends ReentrantLock {

    public String getOwnerName() {
	if ( this.getOwner() == null ) {
	    return "None";
	}
	return this.getOwner().getName();
    } // getOwnerName

    public Collection< Thread > getThreads() {
	return this.getQueuedThreads();
    } // getThreads
    
} // end class MyLock
