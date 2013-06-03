package info.shelfunit.concurrency.venkatsbook.ch007;

import clojure.lang.LockingTransaction;
import java.util.concurrent.Callable;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class Transfer {

    public static void transfer( final AccountJava from, final AccountJava to, final int amount ) throws Exception {
	LockingTransaction.runInTransaction( new Callable< Boolean >() {
	    public Boolean call() throws Exception {
		to.deposit(amount);
		from.withdraw(amount);
		return true;
	    }
	}
        );
    } // end transfer

    public static void transferAndPrint( final AccountJava from, final AccountJava to, final int amount  ) {
	System.out.println( "Balance of from account before: " + from.getBalance() );
	System.out.println( "Balance of to account before: " + to.getBalance() );
	try {
	    transfer( from, to, amount );
	} catch ( Exception ex ) {
	    System.out.println( "transfer failed " + ex );
	}
	System.out.println( "Balance of from account after: " + from.getBalance() );
	System.out.println( "Balance of to account after: " + to.getBalance() );
	System.out.println("\n");
    } // end transferAndPrint

    public static void main( final String args[] ) throws Exception {
	final AccountJava account1 = new AccountJava(2000);
	final AccountJava account2 = new AccountJava(500);
	transferAndPrint( account1, account2, 500 );
	transferAndPrint( account1, account2, 5000 );
	
    } // end main

} // end class Transfer
