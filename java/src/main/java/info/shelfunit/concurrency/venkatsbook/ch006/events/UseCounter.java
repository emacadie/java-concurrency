package info.shelfunit.concurrency.venkatsbook.ch006.events;

// import scala.concurrent.stm.Ref.View;
// import scala.concurrent.stm.japi.STM;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class UseCounter {

    public static void main(final String[] args) {
	Counter counter = new Counter();
	System.out.println("About to call decrement");
	counter.decrement();

	System.out.println("---------------------\nLet's try again");

	try {
	    counter.decrement();
	} catch (Exception e) {
	    System.out.println( e.getMessage() );
	}

    } // end main
    
} // end class Counter
