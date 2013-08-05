package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import junit.framework.TestCase;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import info.shelfunit.util.MyGroovyAssert
import info.shelfunit.util.MyGroovyUtil
import spock.lang.Specification

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
public class MultiValueMap009SpockTest extends Specification {

  MultiValueMap009<String, String> _map;
  def mgu = new MyGroovyUtil()
  def mga = new MyGroovyAssert()

  def setup() throws Exception {
    _map = new MultiValueMap009<String, String>();
  }

  def "test Map Empty Upon Create"() { 
    expect:
    mgu.equals(0, _map.getSize());
  }

  def "test Get Value For Non Existent Key"() { 
    expect:
    mgu.equals(0, _map.getValues("nope").size());
  }
  
  def "test Put One Value For A Key"() {
    _map.put("1", "one");
  
    expect:
    mgu.equals("one", _map.getValues("1").get(0));
  }

  def "test Put Value For Another Key"() {
    _map.put("2", "two");
    
    expect:
    mgu.equals("two", _map.getValues("2").get(0));
  }

  def "test Two Values For One Key"() {
    _map.put("1", "one");
    _map.put("1", "uno");

    expect:
    mgu.equals("one", _map.getValues("1").get(0));
    mgu.equals("uno", _map.getValues("1").get(1));
  }
  
  def "test Check Size After Puts"() {
    _map.put("1", "one");
    _map.put("2", "two");
    _map.put("1", "uno");

    expect:
    mgu.equals(2, _map.getSize());
  }
  
  def "test Ensure Get Values Returns Synchronized List"() {
    _map.put("1", "one");
    List<String> values = _map.getValues("1");
    
    expect:
    mgu.equals(Collections.synchronizedList(values).getClass(), values.getClass());

  }

  class MockLock009 extends ReentrantLock {
    public boolean locked;
    public boolean unlocked;

    @Override public void lock() {
      locked = true;
    }

    @Override public void unlock() {
      unlocked = true;
    }
  }
  
  def "test Put Is Mutually Exclusive"() {
    MockLock009 mockLock = new MockLock009();
    _map.setLock(mockLock);

    expect:
    mga.that(!mockLock.locked);
    mga.that(!mockLock.unlocked);

    _map.put("3", "three");
  
    // mga.that(mockLock.locked);
    mga.that(mockLock.unlocked);
  }
}
