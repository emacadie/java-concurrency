package info.shelfunit.concurrency.venkatsbook.ch002;

// from Programming Concurrency on the JVM by Venkat Subramaniam

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ConcurrentCallableNAV extends AbstractNAV {
    public double computeNetAssetValue( final Map< String, Integer > stocks) 
    throws InterruptedException, ExecutionException {
	final int numberOfCores = Runtime.getRuntime().availableProcessors();
	final double blockingCoefficient = 0.9;
	final int poolSize = ( int )( numberOfCores / ( 1 - blockingCoefficient ) );
	
	System.out.println( "Number of cores available: " + numberOfCores );
	System.out.println( "Pool size is: " + poolSize );
	final List< Callable< Double > > partitions = new ArrayList< Callable< Double > >();

	for ( final String ticker : stocks.keySet() ) {
	    partitions.add( new ConcurrentCallable(stocks.get( ticker ), ticker ) ); 
	} // end for
	
	final ExecutorService executorPool = Executors.newFixedThreadPool(poolSize );
	final List< Future< Double > > valueOfStocks = executorPool.invokeAll( partitions, 10000, TimeUnit.SECONDS );
	
	double netAssetValue = 0.0;
	for ( final Future< Double > valueOfAStock : valueOfStocks ) {
	    netAssetValue += valueOfAStock.get();
	} // end for

	executorPool.shutdown();
	return netAssetValue;
    } // end computNetAssetValue

    public static void main( final String[] args ) 
	throws ExecutionException, InterruptedException, IOException {
	new ConcurrentCallableNAV().timeAndComputeValue();	
    } // end main

} // info.shelfunit.concurrency.venkatsbook.ch002.ConcurrentCallableNAV
