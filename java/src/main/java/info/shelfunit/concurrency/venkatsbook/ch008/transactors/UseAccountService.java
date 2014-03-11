package info.shelfunit.concurrency.venkatsbook.ch008.transactors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.UUID;

// import akka.actor.ActorRef;
// import akka.transactor.SendTo;
// import akka.actor.UntypedActor;

// import scala.concurrent.stm.Ref.View;
// import scala.concurrent.stm.japi.STM;

// import java.util.Set;
// import com.google.common.collect.ImmutableSet;

// from Programming Concurrency on the JVM by Venkat Subramaniam  

public class UseAccountService { 


    public static void printBalance( final String accountName, final ActorRef account ) {
	/*
	Balance balance = 
	    (Balance)(account.tell( new FetchBalance(), account ) );
	System.out.println(accountName + " balance is " + balance.amount);
	*/
    }
  
    public static void main( final String[] args ) throws InterruptedException {
	ActorSystem system = ActorSystem.create( "This-seems-like-a-lot-of-work" );
	 
	final ActorRef account1 = system.actorOf( Props.create( Account.class ), UUID.randomUUID().toString() );
	final ActorRef account2 = system.actorOf( Props.create( Account.class ), UUID.randomUUID().toString() );
	final ActorRef accountService = system.actorOf( Props.create( AccountService.class ), UUID.randomUUID().toString() );
    
	account1.tell( new Deposit( 1000 ), account1 );
	account2.tell( new Deposit( 1000 ), account2 );
    
	Thread.sleep( 1000 );

	printBalance( "Account1", account1 );
	printBalance( "Account2", account2 );
    
	System.out.println( "Let's transfer $20... should succeed" );
	accountService.tell( new Transfer( account1, account2, 20 ), accountService );

	Thread.sleep( 1000 );

	printBalance( "Account1", account1 );
	printBalance( "Account2", account2 );
	
	System.out.println( "Let's transfer $2000... should not succeed" );

	accountService.tell( new Transfer( account1, account2, 2000 ), accountService );

	Thread.sleep( 6000 );

	printBalance( "Account1", account1 );
	printBalance( "Account2", account2 );
	
	system.shutdown();
    } // end main

} // end UseAccountService


