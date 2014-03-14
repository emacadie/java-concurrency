package info.shelfunit.concurrency.venkatsbook.ch009;

import groovyx.gpars.MessagingRunnable;
import groovyx.gpars.actor.DefaultActor;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class MyActor extends DefaultActor {

    private int counter       = 0;
    private int stringCounter = 0;
    private int intCounter    = 0;
    private int otherCounter  = 0;

    public int getCounter(){ return counter; }
    public int getStringCounter(){ return stringCounter; }
    public int getIntCounter(){ return intCounter; }
    public int getOtherCounter(){ return otherCounter; }

    @Override
    protected void act() {

	loop(new Runnable() {

		// Run will be executed with first message sent to actor
            @Override
	    public void run() {

		// Schedule process to retrieve second message from queue and MessagingRunnable to process it
		react( new MessagingRunnable< Object >( this ) {
                    @Override
		    protected void doRun( final Object s ) {
			counter++;
			System.out.println( "\nReceived in react: " + s +
			    " s is a " + s.getClass().getName() +
			    " in " + this.hashCode() + " in thread " +
			    Thread.currentThread().getName() );
			if ( s instanceof String ) {
			    stringCounter++;
			    System.out.println( "s is a String: " + s );
			} else if ( s instanceof Integer) {
			    intCounter++;
			    System.out.println( "s is an Integer: " + s );
			} else if ( s instanceof Object ) {
			    System.out.println( "s is a Object: it is a " + s.getClass().getName() + ", " + s.toString() );
			    otherCounter++;
			}
			// perform if (s instanceof here)
                        
		    }
		    });
            } // end run
	});
    } // end act
} // end class