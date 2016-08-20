package info.shelfunit.concurrency.locks

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class ClosureLock { 
    final Lock lock = new ReentrantLock()
    final Random r  = new Random()
    
    // prevent lock from being changed
    def setLock( arg ) {}
    
    def lockSomeCode( block ) {
        
        try { 
            println( "about to try to get the lock" )
            if ( lock.tryLock() || lock.tryLock( ( Math.abs( r.nextInt() % 1000 ) ), TimeUnit.MILLISECONDS ) ) {
                block.call()
            }
        } finally {
            println( "About to try to unlock" )
            lock.unlock()
        }
    }
}

