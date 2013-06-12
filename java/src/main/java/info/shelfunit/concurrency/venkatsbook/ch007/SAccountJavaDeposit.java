package info.shelfunit.concurrency.venkatsbook.ch007;

import clojure.lang.Ref;
import java.util.concurrent.Callable;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class SAccountJavaDeposit implements Callable< Boolean > {
    final private Ref balance;
    final int amount;

    public SAccountJavaDeposit( final Ref balance, final int amountA ) {
	// System.out.println( "In SAccountJavaDeposit constructor" );
	this.balance = balance;
	this.amount = amountA;
    }

    public Boolean call() {
	// System.out.println( "In SAccountJavaDeposit.call" );
	if (amount > 0) {
	    final int currentBalance = (Integer) balance.deref();
	    balance.set(currentBalance + amount);
	    System.out.println( "separate deposit " + amount + "...will it stay" );
	    return true;
	} else throw new RuntimeException("Operation invalid");
    } // end method call

} // end class AccountJava
