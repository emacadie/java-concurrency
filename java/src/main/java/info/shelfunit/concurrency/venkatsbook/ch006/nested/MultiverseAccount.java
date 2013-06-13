package info.shelfunit.concurrency.venkatsbook.ch006.nested;

import org.multiverse.api.references.*;
import org.multiverse.api.*;

import java.util.concurrent.Callable;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class MultiverseAccount {
    // final private Ref< Integer > balance = new Ref< Integer >();
    final TxnInteger  balance; // = StmUtils.newTxnInteger( new Integer(0) ); 

    public MultiverseAccount(int initialBalance) {
	// new Runnable() { public void run() { balance.set(initialBalance); } };
	this.balance = StmUtils.newTxnInteger( new Integer(initialBalance) ); 
    }

    public int getBalance() { return balance.atomicGet(); }

    public void deposit( final int amount ) {
	StmUtils.atomic( new Callable< Boolean >() {
	    public Boolean call() {
		System.out.println( "Deposit: " + amount );
		if (amount > 0) {
		    int currentBalance = balance.get(); 
		    balance.set( currentBalance + amount );
		    return true;
		}
		// we use a Callable instead of Runnable because
		// if it goes well, we return from "return true" above
		// we only get to here if something goes wrong
		throw new AccountOperationFailedException();
	    } // end call
	} );
    } // end deposit

    public void withdraw(final int amount) {
	StmUtils.atomic( new Callable< Boolean >() {
	    public Boolean call() {
		int currentBalance = balance.get();
		if (amount > 0 && currentBalance >= amount) {
		    balance.set( currentBalance - amount );
		    return true;
		}
		throw new AccountOperationFailedException();
	    } // end call	   
	});
    } // end withdraw

} // end class MultiverseAccount
