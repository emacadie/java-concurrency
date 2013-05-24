package info.shelfunit.concurrency.callable;
 
import java.util.List;
import java.util.LinkedList;
import java.util.UUID;
 
import java.util.concurrent.Callable;
import info.shelfunit.util.ShelfLogger;
import org.apache.log4j.Logger;
 
public class CallableWorker implements Callable< Double > {
 
    private final int num;
    private final String idString;
    private static Logger logger;
 
    public CallableWorker(int num, String idString) {
        logger = ShelfLogger.getInstance().getLogger();
        this.num = num;
        this.idString = idString;
        logger.info("Starting CallableWorker " + num + ", " + idString);
    }
 
    @Override
    public Double call() throws Exception {
        logger.info("Starting CallableWorker.call in " + num + ", " + idString);
        this.hello();
        double sum = 0;
        Thread.sleep( num * 1000 );

        for ( double i = 0; i <= 100; i++ ) {
            sum += ( i * num );
        }
 
        Thread.sleep( num * 1000 );
        logger.info("Ending CallableWorker.call in " + num + ", " + idString);
        return sum;
    } // end method call
 
    private void hello() {
        logger.info("in CallableWorker.hello in " + num + ", " + idString);
    }
 
} // end class info.shelfunit.concurrency.callable.CallableWorker

