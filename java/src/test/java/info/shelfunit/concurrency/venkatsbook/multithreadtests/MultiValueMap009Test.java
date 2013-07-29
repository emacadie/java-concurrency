package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import junit.framework.TestCase;

import java.util.Collections;
import java.util.List;
// import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
In the previous step we used a mock for the Lock interface. While the
MultiValueMap by default is using the ReentrantLock, using a protected
method, we're able to mock it out.

However, there are two concerns.

1. What if the put method called lock and unlock out of sequence or
in the wrong place.
2. As a savvy programmer, you probably realized that if an exception
is thrown, the code will not perform unlock. So, we need a try and
finally. Don't jump to write try and finally, we'll force the test
to fail before writing that piece of code. Let's get to that soon,
we'll take care of the first problem first.

How do we tell that put actually called the lock first, then did the
hard work it has to do and then finally did the unlock? We need to
intercept right within the execution of put. We can do this by
refactoring the code.

In this step, we'll only refactor the code. In the next, we'll put
that to good use. We refactored the guts of the put method into
a protected method named putValueForAKey.
 */
public class MultiValueMap009Test extends TestCase {

  MultiValueMap009<String, String> _map;

  @Override protected void setUp() throws Exception {
    _map = new MultiValueMap009<String, String>();
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

    assertFalse(mockLock.locked);
    assertFalse(mockLock.unlocked);

    _map.put("3", "three");

    assertTrue(mockLock.locked);
    assertTrue(mockLock.unlocked);
  }
}
