package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import junit.framework.TestCase;

import java.util.Collections;
import java.util.List;
// import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
Fortunately Java 5.0 introduced a Lock interface. Unlike synchronized, which
is a keyword in the language, Lock is an interface that can be easily mocked
out for testing purposes. Lets use that. Don't try to attain perfection
at this point. Take small steps. We will not write a single line of code
without having a test for it.

For put method to be mutually exclusive, it should claim a lock (or monitor)
and then release it. Let's ensure it does that.
 */
public class MultiValueMap008Test extends TestCase {

  MultiValueMap008<String, String> _map;

  @Override protected void setUp() throws Exception {
    _map = new MultiValueMap008<String, String>();
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
    MockLock mockLock = new MockLock();
    _map.setLock(mockLock);
    System.out.println( "In Java, mockLock.locked: " + mockLock.locked + " mockLock.unlocked: " + mockLock.unlocked  );
    assertFalse(mockLock.locked);
    System.out.println( "In Java, mockLock.locked: " + mockLock.locked + " mockLock.unlocked: " + mockLock.unlocked  );
    assertFalse(mockLock.unlocked);
    System.out.println( "In Java, mockLock.locked: " + mockLock.locked + " mockLock.unlocked: " + mockLock.unlocked  );
    
    _map.put("3", "three");
    System.out.println( "In Java, mockLock.locked: " + mockLock.locked + " mockLock.unlocked: " + mockLock.unlocked  );
    assertTrue(mockLock.locked);
    System.out.println( "In Java, mockLock.locked: " + mockLock.locked + " mockLock.unlocked: " + mockLock.unlocked  );
    assertTrue(mockLock.unlocked);
  }
}
