package info.shelfunit.concurrency.venkatsbook.ch008.transactors;

import akka.actor.ActorRef;
// import akka.transactor.SendTo;
// import akka.actor.UntypedActor;

// import scala.concurrent.stm.Ref.View;
// import scala.concurrent.stm.japi.STM;

// import java.util.Set;
// import com.google.common.collect.ImmutableSet;

// from Programming Concurrency on the JVM by Venkat Subramaniam  

public class UseAccountService { // extends UntypedActor{ 

    public static void printBalance( final String accountName, final ActorRef account ) {
	// Balance balance = ( Balance ) ( account.tell( new FetchBalance(), account ) );
	// System.out.println( accountName + " balance is " + balance.amount );
    } // end printBalance


} // end UseAccountService


