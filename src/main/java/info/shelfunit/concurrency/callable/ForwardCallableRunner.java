package info.shelfunit.concurrency.callable;
 
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
 
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
 
import info.shelfunit.util.ShelfLogger;
import org.apache.log4j.Logger;
 
public class ForwardCallableRunner {
 
    private static final int NTHREDS = 10;
    private static int iterations;
 
    private static ShelfLogger shelfLogger;
    private static Logger logger;
 
    public static void setIterations() {
        try {
            iterations = new Integer( System.getProperty( "iterations" ) );
        } catch ( Exception e) {
            e.printStackTrace();
            iterations = 5; // some default value
        }
    }
 
    public static void main( String[] args ) throws InterruptedException {
        logger = ShelfLogger.getInstance().getLogger();
 
        setIterations();
        String idString;
        ExecutorService executor = Executors.newFixedThreadPool( NTHREDS );
        List< Future< Double > > futureList = new ArrayList< Future< Double > >();
 
        for ( int i = 0; i <= iterations; i++ ) {
            idString = UUID.randomUUID().toString();
 
            logger.info( "Starting a new MyCallable: " + i + ", " + idString );
            Callable< Double > worker = new CallableWorker(i, idString);
            Thread.sleep( 2 * 1000 );
 
            logger.info( "About to submit MyCallable: " + i + ", " + idString );
            Future< Double > submit = executor.submit( worker );
 
            logger.info( "About to add to futureList MyCallable: " + i + ", " + idString );
            logger.info( " ------ " );
 
            futureList.add( submit );
        }
 
        long sum = 0;
        logger.info( "The size of the list is: " + futureList.size() );
 
        for ( Future< Double > future : futureList ) {
            try {
                sum += future.get();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            } catch ( ExecutionException e ) {
                e.printStackTrace();
            }
        } // for ( Future< Double > future : futureList )
 
        logger.info( "The sum is: " + sum );
        executor.shutdown();
    } // end method main
 
} // info.shelfunit.concurrency.callable.ForwardCallableRunner
