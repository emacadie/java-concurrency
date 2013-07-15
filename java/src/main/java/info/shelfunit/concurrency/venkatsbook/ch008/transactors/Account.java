package info.shelfunit.concurrency.venkatsbook.ch008.transactors;

import akka.transactor.UntypedTransactor;

import scala.concurrent.stm.Ref.View;
import scala.concurrent.stm.japi.STM;

// from Programming Concurrency on the JVM by Venkat Subramaniam  

public class Account extends UntypedTransactor{ 

    private final View< Integer > balance = STM.newRef( new Integer( 0 ) );

    public void atomically( final Object message ) {
	if ( message instanceof Deposit ) {
	    int amount = ( ( Deposit ) ( message ) ).amount;
	    if ( amount > 0 ) {
		balance.swap( balance.get() + amount );
		System.out.println( "Received Deposit request " + amount );
	    }
	} // if ( message instanceof Deposit ) 

	if ( message instanceof Withdraw ) {
	    int amount = ( ( Withdraw )( message ) ).amount;
	    System.out.println( "Received Withdraw request: " + amount );
	    if ( amount > 0 && balance.get() >= amount ) {
		balance.swap( balance.get() - amount );
	    } else {
		System.out.println( "insufficient funds" );
		throw new RuntimeException( "Insufficient funds" );
	    }
	} // if ( message instanceof Withdraw ) 

	if ( message instanceof FetchBalance ) {
	    getSender().tell( new Balance( balance.get() ), getSelf() );
	} // if ( message instanceof FetchBalance ) 

    } // end atomically

} // end Account


