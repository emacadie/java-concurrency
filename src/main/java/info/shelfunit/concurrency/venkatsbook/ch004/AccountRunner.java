package info.shelfunit.concurrency.venkatsbook.ch004;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class AccountRunner {

    public void run001() throws LockException, InterruptedException {
	Account to = new Account( 2000 );
	Account from = new Account( 2000 );
	AccountService aService = new AccountService();

	System.out.println( to.printBalance1() );
	System.out.println( from.printBalance1() );

	aService.transfer( from, to, 500 );

	System.out.println( to.printBalance1() );
	System.out.println( from.printBalance1() );
    } // end run001

    public static void main( final String[] args ) {
	try {
	    AccountRunner ar = new AccountRunner();
	    ar.run001();
	} catch ( Exception e ) {
	}
    } // end main

    public boolean transfer( final Account from, final Account to, final int amount ) 
    throws LockException, InterruptedException {
	final Account[] accounts = new Account[] { from, to };
	Arrays.sort( accounts );
	if ( accounts[ 0 ].monitor.tryLock(1, TimeUnit.SECONDS ) ) {
	    try {
		if ( accounts[ 1 ].monitor.tryLock( 1, TimeUnit.SECONDS ) ) {
		    try {
			if ( from.withdraw( amount ) ) {
			    to.deposit( amount );
			    return true;
			} else { return false; }
		    } finally { accounts[ 1 ].monitor.unlock(); }
		} // if ( accounts[ 1 ].monitor.tryLock( 1, TimeUnit.SECONDS ) ) {
	    } finally { accounts[ 0 ].monitor.unlock(); }
	} // if ( account[ 0 ].monitor.tryLock(1, TimeUnit.SECONDS ) ) {
	throw new LockException( "Unable to acquire locks on the accounts" );
    } // end transfer
} // end class AccountRunner
