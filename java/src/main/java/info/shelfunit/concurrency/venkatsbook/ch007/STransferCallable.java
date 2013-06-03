package info.shelfunit.concurrency.venkatsbook.ch007;

import clojure.lang.LockingTransaction;
import java.util.concurrent.Callable;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class STransferCallable implements Callable< Boolean > {

    final SAccountJava to;
    final SAccountJava from;
    final int amount;

    public STransferCallable(final SAccountJava fromA, final SAccountJava toA, final int amountA ) {
	this.from = fromA;
	this.to = toA;
	this.amount = amountA;
    }

    public Boolean call() throws Exception {
	to.deposit(amount);
	from.withdraw(amount);
	return true;
    }

} // end class Transfer
