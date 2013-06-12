package info.shelfunit.concurrency.venkatsbook.ch007;

import clojure.lang.Ref;
import clojure.lang.LockingTransaction;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class SAccountJava {
    final private Ref balance;

    public SAccountJava( final int initialBalance ) {
	balance = new Ref(initialBalance);
    }

    public int getBalance() {
	return ( Integer ) balance.deref();
    }

    public void deposit( final int amount ) throws Exception {
	LockingTransaction.runInTransaction( 
	    new SAccountJavaDeposit(balance, amount)
        );
    } // end method deposit

    public void withdraw( final int amount ) throws Exception {
	LockingTransaction.runInTransaction( 
	    new SAccountJavaWithdraw(balance, amount)
        );
    } // end method withdraw

} // end class AccountJava
