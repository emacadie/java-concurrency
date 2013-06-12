package info.shelfunit.concurrency.venkatsbook.ch007;

import clojure.lang.LockingTransaction;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class STransfer {

    public static void transfer( final SAccountJava from, final SAccountJava to, final int amount ) throws Exception {
	LockingTransaction.runInTransaction( 
	    new STransferCallable(from, to, amount)
        );
    } // end transfer

    public static void transferAndPrint( final SAccountJava from, final SAccountJava to, final int amount  ) {
	System.out.println( "Balance of from account before: " + from.getBalance() );
	System.out.println( "Balance of to account before: " + to.getBalance() );
	try {
	    transfer( from, to, amount );
	} catch ( Exception ex ) {
	    System.out.println( "transfer failed " + ex );
	}
	System.out.println( "Balance of from account after: " + from.getBalance() );
	System.out.println( "Balance of to account after: " + to.getBalance() );
	System.out.println( "\n" );
    } // end transferAndPrint

    public static void main( final String args[] ) throws Exception {
	final SAccountJava account1 = new SAccountJava(2000);
	final SAccountJava account2 = new SAccountJava(500);
	transferAndPrint( account1, account2, 500 );
	transferAndPrint( account1, account2, 5000 );
	
    } // end main

} // end class Transfer
