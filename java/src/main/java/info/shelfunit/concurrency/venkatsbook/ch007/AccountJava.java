package info.shelfunit.concurrency.venkatsbook.ch007;

import clojure.lang.Ref;
import clojure.lang.LockingTransaction;
import java.util.concurrent.Callable;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class AccountJava {
    final private Ref balance;

    public AccountJava( final int initialBalance ) {
	balance = new Ref(initialBalance);
    }

    public int getBalance() {
	return ( Integer ) balance.deref();
    }

    public void deposit( final int amount ) throws Exception {
	LockingTransaction.runInTransaction( new Callable< Boolean >() {
	    public Boolean call() {
		if (amount > 0) {
		    final int currentBalance = (Integer) balance.deref();
		    balance.set(currentBalance + amount);
		    System.out.println( "inline deposit " + amount + "...will it stay" );
		    return true;
		} else throw new RuntimeException("Operation invalid");
	    }
	}
        );
    } // end method deposit

    public void withdraw( final int amount ) throws Exception {
	LockingTransaction.runInTransaction( new Callable< Boolean >() {
	    public Boolean call() {
		final int currentBalance = (Integer) balance.deref();
		if ( ( amount > 0 ) && ( currentBalance >= amount ) ) {
		    balance.set(currentBalance - amount);
		    System.out.println( "inline withdraw " + amount + "...will it stay" );
		    return true;
		} else throw new RuntimeException("Operation invalid");
	    }
	}
        );
    } // end method withdraw

} // end class AccountJava
