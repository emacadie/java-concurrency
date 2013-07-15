package info.shelfunit.concurrency.venkatsbook.ch008.transactors;

import akka.transactor.SendTo;
import akka.transactor.UntypedTransactor;

// import scala.concurrent.stm.Ref.View;
// import scala.concurrent.stm.japi.STM;

import java.util.Set;
import com.google.common.collect.ImmutableSet;

// from Programming Concurrency on the JVM by Venkat Subramaniam  

public class AccountService extends UntypedTransactor{ 

    public Set< SendTo > coordinate( final Object message ) {
	if ( message instanceof Transfer ) {
	    Transfer transfer = ( Transfer ) message;
	    Set< SendTo > coordinations = new ImmutableSet.Builder< SendTo >()
		.add( sendTo( transfer.to, new Deposit( transfer.amount ) ) )
		.add( sendTo( transfer.from, new Withdraw( transfer.amount ) ) )
		.build();
	    return coordinations;
	} // if ( message instanceof Transfer ) 
	return nobody();
    } // end coordinate

    public void atomically( final Object message ) {}

} // end AccountService


