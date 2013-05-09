package info.shelfunit.concurrency.venkatsbook.ch002;

// from Programming Concurrency on the JVM by Venkat Subramaniam

import java.util.Map;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SequentialNAV extends AbstractNAV{

    public double computeNetAssetValue ( final Map< String, Integer > stocks )
    throws IOException {
	double netAssetValue = 0.0;
	for ( String ticker : stocks.keySet() ) {
	    netAssetValue += stocks.get( ticker ) * YahooFinance.getPrice( ticker );
	}
	return netAssetValue;
    } // computeNetAssetValue

    public static void main( String[] args ) 
    throws ExecutionException, IOException, InterruptedException {
	new SequentialNAV().timeAndComputeValue();
    }

} // end class info.shelfunit.concurrency.venkatsbook.ch002.SequentialNAV
