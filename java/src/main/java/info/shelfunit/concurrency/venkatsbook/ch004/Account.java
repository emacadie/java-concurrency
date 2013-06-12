package info.shelfunit.concurrency.venkatsbook.ch004;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.UUID;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class Account implements Comparable< Account > {
    private int balance;
    private final String uuid = UUID.randomUUID().toString();
    public final Lock monitor = new ReentrantLock();

    public void printBalance() {  
	System.out.println( "Account balance of " + uuid + ": " + balance );
    }

    public String printLockMessage() {
	return uuid + ": getting Lock";
    }

    public String printUnlockMessage() {
	return uuid + ": releasing Lock";
    }

    public String printBalance1() {  
	System.out.println( "Account balance of " + uuid + ": " + balance );
	return " ";
    }

    public Account( final int initialBalance ) {
	balance = initialBalance;
    }

    public int compareTo( final Account other ) {
	return new Integer( hashCode() ).compareTo( other.hashCode() );
    }

    public void deposit( final int amount ) {
	monitor.lock();
	try {
	    if ( amount > 0 ) { balance += amount; }
	} finally { monitor.unlock(); }
    } // end deposit

    public boolean withdraw( final int amount ) {
	try { 
	    monitor.lock();
	    if ( amount > 0 && balance >= amount ) {
		balance -= amount;
		return true;
	    } else { return false; }
	} finally { monitor.unlock();  }

    } // end withdraw

} // end class Account
