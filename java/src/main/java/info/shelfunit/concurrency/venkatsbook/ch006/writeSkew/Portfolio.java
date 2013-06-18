package info.shelfunit.concurrency.venkatsbook.ch006.writeSkew;

import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

// import java.util.concurrent.Callable;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class Portfolio {

    final Ref.View< Integer > checkingBalance = STM.newRef( new Integer(500) );
    final Ref.View< Integer > savingsBalance = STM.newRef( new Integer(600) );

    public int getCheckingBalance() {
	return checkingBalance.get();
    }

    public int getSavingsBalance() {
	return savingsBalance.get();
    }

    public void withdraw( final boolean fromChecking, final int amount ) {
	STM.atomic( new Runnable() {
	    public void run() {
		final int totalBalance = checkingBalance.get() + savingsBalance.get();
		try { Thread.sleep(1000); } catch( InterruptedException ex ){ }
		if ( totalBalance - amount >= 1000 ) {
		    if ( fromChecking ) {
			checkingBalance.swap( checkingBalance.get() - amount );
		    } else {
			savingsBalance.swap( savingsBalance.get() - amount );
		    } // if ( fromChecking ) 
		} else {
		    System.out.println( "Unable to withdraw due to constraint violation" );
		} // if ( totalBalance - amount >= 1000 ) 
	    } // end run
	});
    } // end withdraw

} // end class Portfolio
