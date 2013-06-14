package info.shelfunit.concurrency.venkatsbook.ch006.events;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class UseMultiverseCounter {

    public static void main(final String[] args) {
	MultiverseCounter counter = new MultiverseCounter();
	System.out.println("About to call decrement");
	counter.decrement();

	System.out.println("---------------------\nLet's try again");

	try {
	    counter.decrement();
	} catch ( Exception e ) {
	    System.out.println( "Here is the message: " + e.getMessage() );
	}

    } // end main
    
} // end class UseMultiverseCounter
