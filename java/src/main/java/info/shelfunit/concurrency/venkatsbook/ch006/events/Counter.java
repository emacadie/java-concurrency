package info.shelfunit.concurrency.venkatsbook.ch006.events;

import scala.concurrent.stm.Ref.View;
import scala.concurrent.stm.japi.STM;
// import java.util.concurrent.Callable;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class Counter {
    // final private Ref< Integer > balance = new Ref< Integer >();
    final View< Integer > value = STM.newRef( new Integer(1) ); 
    /*
    public Account(int initialBalance) {
	balance.swap(initialBalance);
    }
*/
    // public int getBalance() { return balance.get(); }

    public void decrement() {
	STM.atomic( new Runnable() {
	public void run() {

	    // the afterCommit and afterRollback can come before your
	    // "business logic"
	// same as deferred in Multiverse
	STM.afterCommit( new Runnable() {
	    public void run() {
		System.out.println( "In the deferred part" );
	    } // end run
	});

	// same as compensating in Multiverse
	STM.afterRollback( new Runnable() {
	    public void run() {
		System.out.println( "After rollback: Transaction aborted" );
	    } // end run
	});

	// STM.atomic( new Runnable() {
	// public void run() {
		if (value.get() <= 0) {
		    throw new RuntimeException( "Operation not allowed" );
		}
		System.out.println( "Doing the swap" );
		value.swap( value.get() - 1 );
	    } // end run
	});
    } // end decrement

} // end class Counter
