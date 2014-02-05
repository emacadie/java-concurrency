package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import junit.framework.TestCase;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import info.shelfunit.util.MyGroovyAssert
import info.shelfunit.util.MyGroovyUtil
import spock.lang.Specification


/**
Fortunately Java 5.0 introduced a Lock interface. Unlike synchronized, which
is a keyword in the language, Lock is an interface that can be easily mocked
out for testing purposes. Lets use that. Don't try to attain perfection
at this point. Take small steps. We will not write a single line of code
without having a test for it.

For put method to be mutually exclusive, it should claim a lock (or monitor)
and then release it. Let's ensure it does that.
 */
public class MultiValueMap008SpockTest extends Specification {

  MultiValueMap008<String, String> _map;
  def mgu = new MyGroovyUtil()
  def mga = new MyGroovyAssert()

  def setup() throws Exception {
    _map = new MultiValueMap008<String, String>();
  }


  def "test Map Empty Upon Create"() { 
    expect:
    0 == _map.getSize()
  }

  def "test Get Value For Non Existent Key"() { 
    expect:
    0 == _map.getValues("nope").size()
  }
  
  def "test Put One Value For A Key"() {
    _map.put("1", "one");
    expect:
    "one" == _map.getValues("1").get(0)
  }

  def "test Put Value For Another Key"() {
    _map.put("2", "two");
    expect:
    "two" == _map.getValues("2").get(0)
  }

  def "test Two Values For One Key"() {
    _map.put("1", "one");
    _map.put("1", "uno");

    expect:
    "one" == _map.getValues("1").get(0)
    "uno" == _map.getValues("1").get(1)
  }
  
  def "test Check Size After Puts"() {
    _map.put("1", "one");
    _map.put("2", "two");
    _map.put("1", "uno");

    expect:
    2 == _map.getSize()
  }
  
  def "test Ensure Get Values Returns Synchronized List"() {
    _map.put("1", "one");
    List<String> values = _map.getValues("1");
    
    expect:
    Collections.synchronizedList(values).getClass() == values.getClass()

  }

  /*
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
*/  
  def "test Put Is Mutually Exclusive"() {
    MockLockSeparate mockLock = new MockLockSeparate();
    _map.setLock(mockLock);
    println("mockLock.locked before put: " + mockLock.locked )
    expect:
    !mockLock.locked
    !mockLock.unlocked
    
    when:
    println("mockLock.locked before put: " + mockLock.locked )
    _map.put("3", "three");
    println("mockLock.locked after put: " + mockLock.locked )
    then:
    // mockLock.locked
    mockLock.unlocked
  }
}
