package info.shelfunit.concurrency.venkatsbook.ch009;

import java.net.URL;
import java.util.Date;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class MyActorRunner {
  
    public void doStuff001() throws InterruptedException, Throwable {
	MyActor actor = new MyActor();
        actor.start();
        actor.send( "Hello" );
	actor.send( new Date() );
        actor.send( "Hello again" );
        actor.send( 10 );
	
        System.out.println("----------------------------------------");
        Thread.sleep( 2000L );
	System.out.println( "actor.getCounter: " + actor.getCounter() + " actor.getStringCounter: " + actor.getStringCounter() +
			    " actor.getIntCounter: " + actor.getIntCounter() + " actor.getOtherCounter: " + actor.getOtherCounter()
	);
        actor.send( "Bye" );
        actor.send( "Bye again" );
        actor.send( new URL("http://example.com/") );
	actor.send( 10 );

        Thread.sleep( 2000L );
	System.out.println( "actor.getCounter: " + actor.getCounter() + " actor.getStringCounter: " + actor.getStringCounter() +
	    " actor.getIntCounter: " + actor.getIntCounter() + " actor.getOtherCounter: " + actor.getOtherCounter()
	);
    } // end doStuff001

    public static void main( String[ ] args ) { 
	MyActorRunner  maR = new MyActorRunner();
	
	try {
	    maR.doStuff001();
	    Thread.sleep( 2000 );
	} catch ( Exception e ) {
	} catch ( Throwable t) {}
    } // end main

} // end MyActorRunner
