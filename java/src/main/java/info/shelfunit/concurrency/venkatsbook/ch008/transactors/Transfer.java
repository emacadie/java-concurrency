package info.shelfunit.concurrency.venkatsbook.ch008.transactors;

import akka.actor.ActorRef;

// from Programming Concurrency on the JVM by Venkat Subramaniam  

public class Transfer { 

    public final ActorRef from;
    public final ActorRef to;
    public final int amount;

    public Transfer( final ActorRef fromAccount, final ActorRef toAccount, final int argAmount ) {
	from = fromAccount;
	to = toAccount;
	amount = argAmount;
    }

} // end Trasnfer

