package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import junit.framework.TestCase;

import java.util.Collections;
import java.util.List;
// import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
OK, now that we're refactored the code, we can easily check if
put is doing the right thing, that is, calls lock, then does its work,
then calls unlock.
 */

public class MultiValueMap010Test extends TestCase {

  MultiValueMap010<String, String> _map;

  @Override protected void setUp() throws Exception {
    _map = new MultiValueMap010<String, String>();
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
    MultiValueMap010<String, String> map = new MultiValueMap010<String, String>() {
      @Override protected void putValueForAKey(String key, String value) {
	  System.out.println( "In putValueForAKey in Java test, mockLock.locked: " + mockLock.locked + " mockLock.unlocked: " + mockLock.unlocked  );
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
}
