package info.shelfunit.concurrency.cookbook.chapter08;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.TimeUnit;

// from Java 7 Concurrency Cookbook by Javier Fernández González

public class Task implements Runnable {
    private Lock lock;

    public Task( Lock lock ) {
	this.lock = lock;
    }

    @Override
    public void run() {
	for ( int i = 0; i < 5; i++ ) {
	    lock.lock();
	    System.out.printf( "%s: Get the Lock\n", Thread.currentThread().getName() );
	    try {
		TimeUnit.MILLISECONDS.sleep( 500 );
		System.out.printf( "%s: Free the Lock\n", Thread.currentThread().getName() );
	    } catch ( InterruptedException e ) {
		e.printStackTrace();
	    } finally {
		lock.unlock();
	    }
	} // for ( int i = 0; i < 5; i++ ) 
    } // end run

} // end Task
