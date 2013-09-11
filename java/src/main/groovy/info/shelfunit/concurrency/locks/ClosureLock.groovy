package info.shelfunit.concurrency.locks

import java.util.concurrent.locks.*

class ClosureLock { 
  def lockSomeCode(block) { 
    
    Lock lock = new ReentrantLock()
    try { 
      println("about to try to get the clock")
      if (lock.tryLock() || lock.tryLock(500, TimeUnit.MILLISECONDS)) {
	block.call()
      }
    } finally {
      println("About to try to unlock")
      lock.unlock()
    }
  }
}