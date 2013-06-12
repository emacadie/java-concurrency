package info.shelfunit.concurrency.venkatsbook.ch006.nested;

import scala.concurrent.stm.Ref;
import scala.concurrent.stm.Ref.View;
import scala.concurrent.stm.japi.STM;
import java.util.concurrent.Callable;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class MultiverseAccount {
    // final private Ref< Integer > balance = new Ref< Integer >();
    final View< Integer > balance = STM.newRef( new Integer(0) ); 

    public MultiverseAccount(int initialBalance) {
	balance.swap(initialBalance);
    }

    public int getBalance() { return balance.get(); }

    public void deposit( final int amount ) {
	STM.atomic( new Callable< Boolean >() {
	    public Boolean call() {
		System.out.println( "Deposit: " + amount );
		if (amount > 0) {
		    balance.swap( balance.get() + amount );
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
	STM.atomic( new Callable< Boolean >() {
	    public Boolean call() {
		int currentBalance = balance.get();
		if (amount > 0 && currentBalance >= amount) {
		    balance.swap( currentBalance - amount );
		    return true;
		}
		throw new AccountOperationFailedException();
	    } // end call	   
	});
    } // end withdraw

} // end class Account
