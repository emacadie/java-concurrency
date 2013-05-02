package de.vogella.concurrency.callables;

import java.util.concurrent.Callable;

public class MyCallable implements Callable< Long > {

    private int num;
    private int id;
    private String idString;

    public MyCallable() {
    }

    @Override
    public Long call() throws Exception {
	long sum = 0;
	for ( long i = 0; i <= 100; i++ ) {
	    sum += i;
	}
	return sum;
    } // end method call

} // end class de.vogella.concurrency.callables.MyCallable
