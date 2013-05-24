package info.shelfunit.concurrency.venkatsbook.ch002;

// from Programming Concurrency on the JVM by Venkat Subramaniam

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class YahooFinance {

    public static double getPrice( final String ticker ) throws IOException {
	final URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);
	final BufferedReader reader = new BufferedReader( new InputStreamReader( url.openStream() ) );

	// Date, Open, High, Low, Close, Volume, Adjusted Close
	final String discardHeader = reader.readLine();
	final String data = reader.readLine();
	final String[] dataItems = data.split( "," );
	final double priceIsTheLastValue = Double.valueOf(dataItems[dataItems.length - 1]);
	return priceIsTheLastValue;
    } // getPrice

} // info.shelfunit.concurrency.venkatsbook.ch002.YahooJava
