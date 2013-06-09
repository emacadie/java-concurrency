package info.shelfunit.concurrency.venkatsbook.ch006.stm;

// import scala.concurrent.stm.Ref;
// import scala.concurrent.stm.Ref.View;
// import scala.concurrent.stm.japi.STM;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
// import java.util.concurrent.TimeUnit;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class UseEnergySource {
    private static final EnergySource energySource = EnergySource.create();

    public static void main( final String[] args ) 
    throws InterruptedException, ExecutionException {
	System.out.println( "Energy level at start: " + energySource.getUnitsAvailable() );
	List< Callable< Object > > tasks = new ArrayList< Callable< Object > >();
	for ( int i = 0; i < 10; i++ ) {
	    tasks.add( new Callable< Object >() {
		public Object call() {
		    for ( int j = 0; j < 7; j++ ) {
			energySource.useEnergy( 1 );
		    }
		    return null;
		}
	    }
            );
	} // for ( int i = 0; i < 10; i++ ) 

	final ExecutorService service = Executors.newFixedThreadPool( 10 );
	service.invokeAll( tasks );
	
	System.out.println("Energy available at end: " + energySource.getUnitsAvailable());
	System.out.println( "Usage: " + energySource.getUsageCount() );

	energySource.stopEnergySource();

	
	service.shutdown();
	System.out.println( "Just called service.shutdown" );

	energySource.shutdownScheduledExecutorService();
	System.out.println( "Just called energySource.shutdownScheduledExecutorService()" );

	Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
	Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
	System.out.println( "threadArray.length: " + threadArray.length );
	for ( int x = 0; x < threadArray.length; x++) {
	    System.out.println( "Thread name: " + threadArray[x].getName() );
	}
    } // end main

} // end UseEnergySource
