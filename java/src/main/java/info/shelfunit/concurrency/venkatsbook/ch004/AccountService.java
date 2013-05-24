package info.shelfunit.concurrency.venkatsbook.ch004;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class AccountService {
    public boolean transfer( final Account from, final Account to, final int amount ) 
    throws LockException, InterruptedException {
	final Account[] accounts = new Account[] { from, to };
	Arrays.sort( accounts );
	if ( accounts[ 0 ].monitor.tryLock(1, TimeUnit.SECONDS ) ) {
	    System.out.println( accounts[ 0 ].printLockMessage() );
	    try {
		if ( accounts[ 1 ].monitor.tryLock( 1, TimeUnit.SECONDS ) ) {
		    System.out.println( accounts[ 1 ].printLockMessage() );
		    try {
			if ( from.withdraw( amount ) ) {
			    to.deposit( amount );
			    return true;
			} else { return false; }
		    } finally { 
			accounts[ 1 ].monitor.unlock(); 
			System.out.println( accounts[ 1 ].printUnlockMessage() );
		    }
		} // if ( accounts[ 1 ].monitor.tryLock( 1, TimeUnit.SECONDS ) ) {
	    } finally { 
		accounts[ 0 ].monitor.unlock(); 
		System.out.println( accounts[ 0 ].printUnlockMessage() );
	    }
	} // if ( account[ 0 ].monitor.tryLock(1, TimeUnit.SECONDS ) ) {
	throw new LockException( "Unable to acquire locks on the accounts" );
    } // end transfer
} // end class AccountService
