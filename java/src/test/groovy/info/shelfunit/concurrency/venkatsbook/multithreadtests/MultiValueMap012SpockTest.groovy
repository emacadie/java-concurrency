package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import junit.framework.TestCase;

import java.util.Collections;
import java.util.List;
// import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
Now let's fix the put method to make the failing test pass.

Hope this gives you an idea of how to approach unit testing multithreaded
code that requires synchronization. I will leave it as homework for you to
test the thread-safety of other methods in the class. :)
 */

public class MultiValueMap012Test extends TestCase {

  MultiValueMap012<String, String> _map;

  @Override protected void setUp() throws Exception {
    _map = new MultiValueMap012<String, String>();
  }

  public void testMapEmptyUponCreate() {
    assertEquals(0, _map.getSize());
  }

  public void testGetValueForNonExistentKey() {
    assertEquals(0, _map.getValues("nope").size());
  }
  
  public void testPutOneValueForAKey() {
    _map.put("1", "one");
    assertEquals("one", _map.getValues("1").get(0));
  }

  public void testPutValueForAnotherKey() {
    _map.put("2", "two");
    assertEquals("two", _map.getValues("2").get(0));
  }

  public void testTwoValuesForOneKey() {
    _map.put("1", "one");
    _map.put("1", "uno");

    assertEquals("one", _map.getValues("1").get(0));
    assertEquals("uno", _map.getValues("1").get(1));
  }
  
  public void testCheckSizeAfterPuts() {
    _map.put("1", "one");
    _map.put("2", "two");
    _map.put("1", "uno");

    assertEquals(2, _map.getSize());
  }
  
  public void testEnsureGetValuesReturnsSynchronizedList() {
    _map.put("1", "one");
    List<String> values = _map.getValues("1");

    assertEquals(Collections.synchronizedList(values).getClass(), values.getClass());
  }

  class MockLock extends ReentrantLock {
    public boolean locked;
    public boolean unlocked;

    @Override public void lock() {
      locked = true;
    }

    @Override public void unlock() {
      unlocked = true;
    }
  }
  
  public void testPutIsMutuallyExclusive() {
    final MockLock mockLock = new MockLock();
    MultiValueMap012<String, String> map = new MultiValueMap012<String, String>() {
      @Override protected void putValueForAKey(String key, String value) {
        assertTrue(mockLock.locked);
        assertFalse(mockLock.unlocked);
      }
    };
    
    map.setLock(mockLock);

    assertFalse(mockLock.locked);
    assertFalse(mockLock.unlocked);

    map.put("3", "three");

    assertTrue(mockLock.locked);
    assertTrue(mockLock.unlocked);
  }
  
  public void testPutLockAndUnlockAreGuardedByTryFinally() {
    MultiValueMap012<String, String> map = new MultiValueMap012<String, String>() {
      @Override protected void putValueForAKey(String key, String value) {
        throw new RuntimeException("simulated exception");
      }
    };

    MockLock mockLock = new MockLock();
    map.setLock(mockLock);

    try {
      map.put("4", "four");
      fail("Expected the simulated exception!");
    } catch(RuntimeException ex) {
      // :) Expected
    }

    assertTrue(mockLock.locked);
    assertTrue(mockLock.unlocked);
  }
}
