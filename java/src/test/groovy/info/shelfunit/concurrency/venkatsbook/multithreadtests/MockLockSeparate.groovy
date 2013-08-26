package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import info.shelfunit.util.MyGroovyAssert
import info.shelfunit.util.MyGroovyUtil
import spock.lang.Specification

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
OK, now that we're refactored the code, we can easily check if
put is doing the right thing, that is, calls lock, then does its work,
then calls unlock.
 */

class MockLockSeparate extends ReentrantLock { 
    public boolean locked;
    public boolean unlocked;

    @Override public void lock() { 
      locked = true;
      println("called MockLockSeparate.lock, locked = " + locked + " unlocked = " + unlocked)
    }

    @Override public void unlock() { 
      unlocked = true;
      println("called MockLockSeparate.unlock, locked = " + locked + " unlocked = " + unlocked)
    }
}

