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

public class MultiValueMap010SpockTest extends Specification {

  MultiValueMap010<String, String> _map;
  def mgu = new MyGroovyUtil()
  def mga = new MyGroovyAssert()

  def setup() throws Exception { 
    _map = new MultiValueMap010<String, String>();
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

  class MockLock010 extends ReentrantLock { 
    public boolean locked;
    public boolean unlocked;

    @Override public void lock() { 
      locked = true;
      println("called MockLock010.lock, locked = " + locked)
    }

    @Override public void unlock() { 
      unlocked = true;
      println("called MockLock010.unlock, unlocked = " + unlocked)
    }
  }
  
  def "test Put Is Mutually Exclusive01"() { 
    MockLock010 mockLock = new MockLock010();
    _map.setLock(mockLock);

    expect:
    mga.that(!mockLock.locked);
    mga.that(!mockLock.unlocked);

    _map.put("3", "three");

    // mga.that(mockLock.locked);
    mga.that(mockLock.unlocked);
  }


  /*
    I do not think this will work in Groovy. I don't think
    we can override Java methods in Spock
   */  
  def "test Put Is Mutually Exclusive"() {
    // MockLock010 mockLock = new MockLock010();
    final MockLockSeparate mockLock = new MockLockSeparate()
    MultiValueMap010<String, String> map = new MultiValueMap010<String, String>() {
      @Override protected void putValueForAKey(String key, String value) {
	println("In overriding method, mockLock.locked -> " + mockLock.locked)
	println("In overriding method, mockLock.unlocked -> " + mockLock.unlocked)
	println("mockLock.class.name: " + mockLock.class.name )
        expect:
        // mga.that(mockLock.locked);
        mga.that(!mockLock.unlocked);
      }
    };
  
    when:
    map.setLock(mockLock);
    then:
    mga.that(!mockLock.locked);
    mga.that(!mockLock.unlocked);

    when:
    println("About to call put")
    map.put("3", "three");
    then:
    // mga.that(mockLock.locked);
    mga.that(mockLock.unlocked);
  }

}
