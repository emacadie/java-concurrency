package info.shelfunit.concurrency.venkatsbook.ch002;

// from Programming Concurrency on the JVM by Venkat Subramaniam

import java.util.concurrent.Callable;

public class ConcurrentCallable implements Callable< Double > {

    private int numOfShares;
    private String ticker;

    public ConcurrentCallable( int numOfShares, String ticker ) {
	this.numOfShares = numOfShares;
	this.ticker = ticker;
    }

    @Override
    public Double call() throws Exception {
	return numOfShares * YahooFinance.getPrice( ticker );
    }

} // info.shelfunit.concurrency.venkatsbook.ch002.ConcurrentCallable
