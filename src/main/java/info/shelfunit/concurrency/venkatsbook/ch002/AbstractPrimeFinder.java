package info.shelfunit.concurrency.venkatsbook.ch002;

// from Programming Concurrency on the JVM by Venkat Subramaniam


public abstract class AbstractPrimeFinder {

    public boolean isPrime( final int number ) {
	if ( number <= 1 ) { return false; }

	for ( int i = 2; i <= Math.sqrt( number ); i++ ) {
	    if ( number % i == 0 ) { return false; }
	}
	
	return true;

    }  // isPrime

    public int countPrimesInRange( final int lower, final int upper ) {
	int total = 0;

	for ( int i = lower; i <= upper; i++ ) {
	    if ( isPrime( i ) ) { total++; }
	} 

	return total;
    } // countPrimesInRange

    public void timeAndCompute( final int number ) {
	final long start = System.nanoTime();
	final long numberOfPrimes = countPrimes( number );
	final long end = System.nanoTime();


	System.out.printf( "Number of primes under %d is %d\n", number, numberOfPrimes );
	System.out.println( "Time (seconds) taken is " + ( end - start )/1.0e9 );
    } // timeAndCompute

    public abstract int countPrimes( final int number );

} // end class info.shelfunit.concurrency.venkatsbook.ch002.AbstractPrimeFinder 
