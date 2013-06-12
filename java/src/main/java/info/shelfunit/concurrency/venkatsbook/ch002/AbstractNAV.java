package info.shelfunit.concurrency.venkatsbook.ch002;

// from Programming Concurrency on the JVM by Venkat Subramaniam

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.text.DecimalFormat;

import java.net.URL;
import java.io.InputStreamReader;

public abstract class AbstractNAV {
    public static Map< String, Integer > readTickers() throws IOException {
	// final BufferedReader reader = new BufferedReader( new FileReader( "/stocks.txt" ) );
	String hh = "hh";
	URL theURL = hh.getClass().getResource( "/stocks.txt" );
	final BufferedReader reader = new BufferedReader( new InputStreamReader( theURL.openStream() ) );
	final Map< String, Integer > stocks = new HashMap< String, Integer >();
	String stockInfo = null;
	while ( ( stockInfo = reader.readLine() ) != null ) {
	    final String[] stockInfoData = stockInfo.split(",");
	    final String stockTicker = stockInfoData[ 0 ];
	    final Integer quantity = Integer.valueOf( stockInfoData[ 1 ] );
	    stocks.put( stockTicker, quantity );
	} // while
	return stocks;
    } // readTickers

    public void timeAndComputeValue() 
    throws ExecutionException, InterruptedException, IOException {
	
	final long start = System.nanoTime();

	final Map< String, Integer > stocks = readTickers();
	final double nav = computeNetAssetValue( stocks );
	final long end = System.nanoTime();

	final String value = new DecimalFormat("$##,##0.00").format(nav);
	System.out.println( "Your net asset value is " + value );
	System.out.println( "Time (seconds) taken: " + ( end - start )/1.0e9 );
	
    } // end method timeAndComputeValue

    public abstract double computeNetAssetValue( final Map< String, Integer > stocks )
	throws ExecutionException, InterruptedException, IOException;

} // end class info.shelfunit.concurrency.venkatsbook.ch002.AbstractNAV 
