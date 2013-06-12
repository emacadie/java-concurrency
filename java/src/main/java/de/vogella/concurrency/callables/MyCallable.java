package de.vogella.concurrency.callables;

import java.util.concurrent.Callable;
// import java.util.UUID;
import info.shelfunit.util.ShelfLogger;
import org.apache.log4j.Logger;


public class MyCallable implements Callable< Long > {

    private final int num;
    // private final int id;
    private final String idString;
    // private static ShelfLogger shelfLogger;
    private static Logger logger;

    public MyCallable(int num, String idString) {
	logger = ShelfLogger.getInstance().getLogger();
	this.num = num;
	this.idString = idString;
	logger.info("Starting MyCallable " + num + ", " + idString);
    }

    @Override
    public Long call() throws Exception {
	logger.info("Starting MyCallable.call in " + num + ", " + idString);
	this.hello();
	long sum = 0;
	for ( long i = 0; i <= 100; i++ ) {
	    sum += i;
	}
	return sum;
    } // end method call

    private void hello() {
	logger.info("in MyCallable.hello in " + num + ", " + idString);
    }


} // end class de.vogella.concurrency.callables.MyCallable
