package info.shelfunit.concurrency.venkatsbook.ch006.events;

import org.multiverse.api.StmUtils;
import org.multiverse.api.references.TxnInteger;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class MultiverseCounter {

    private final TxnInteger value = StmUtils.newTxnInteger(1); 

    // the afterCommit and afterRollback can come before your "business logic"
    public void decrement() {
	StmUtils.atomic( new Runnable() {
	public void run() {

	    	    // this is our "busines logic"
	    if (value.get() <= 0) {
		    throw new RuntimeException( "Operation not allowed" );
		}
		System.out.println( "Doing the swap" );
		value.set( value.get() - 1 );

	    // after a rollback
	    StmUtils.scheduleCompensatingTask( new Runnable() {
	        public void run() {
		    System.out.println( "After rollback: Transaction aborted" );
	        } // end run
	    });

	    // after a commit
	    StmUtils.scheduleDeferredTask( new Runnable() {
		public void run() {
		    System.out.println( "In the deferred part" );
	        } // end run
	    });


	    } // end run
	});
    } // end decrement

} // end class Counter
