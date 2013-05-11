package info.shelfunit.concurrency.venkatsbook.ch002;

// from Programming Concurrency on the JVM by Venkat Subramaniam


public class SequentialPrimeFinder extends AbstractPrimeFinder {

    public int countPrimes( final int number ) {
	return countPrimesInRange( 1, number );
    } // countPrimes

    public static void main( final String[] args ) {
	new SequentialPrimeFinder().timeAndCompute( Integer.parseInt( args[ 0 ] ) );
    } // end main


} // end class info.shelfunit.concurrency.venkatsbook.ch002.SequentialPrimeFinder 
