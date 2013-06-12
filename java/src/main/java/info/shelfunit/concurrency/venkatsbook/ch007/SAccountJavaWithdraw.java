package info.shelfunit.concurrency.venkatsbook.ch007;

import clojure.lang.Ref;
import java.util.concurrent.Callable;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class SAccountJavaWithdraw implements Callable< Boolean > {
    final private Ref balance;
    final int amount;
    
    public SAccountJavaWithdraw( final Ref balance, final int amountA) {
	// System.out.println( "in SAccountjavaWithdraw constructor" );
	this.balance = balance;
	this.amount = amountA;
    }

    public Boolean call() {
	// System.out.println( "in SAccountjavaWithdraw.call" );
	final int currentBalance = (Integer) balance.deref();
	if ( ( amount > 0 ) && ( currentBalance >= amount ) ) {
	    balance.set(currentBalance - amount);
	    System.out.println( "separate withdraw " + amount + "...will it stay" );
	    return true;
	} else throw new RuntimeException("Operation invalid");
    }   

} // end class AccountJava
