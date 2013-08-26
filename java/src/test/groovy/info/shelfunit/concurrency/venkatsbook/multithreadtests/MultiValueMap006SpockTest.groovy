package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import info.shelfunit.util.MyGroovyAssert
import info.shelfunit.util.MyGroovyUtil
import spock.lang.Specification

import java.util.Collections;
import java.util.List;

/**
Ensure that the list returned by getValues is thread safe.

How do we do that?

Unfortunately, collections that support concurrency don't implement
any interfaces. So, there is no interface you can check instanceof
for.

One ugly way to write this test is to compare the class name of
the list returned by the get method.

Alternately, as suggested by the audience in a workshop, you could
create a synchronized list and check if the classes are equal.

If you're worried this reveals a bit more detail about the implementation
than you expected, remember unit testing is white-box testing. So, this
shouldn't be totally unexpected.
 */

public class MultiValueMap006SpockTest extends Specification {

  MultiValueMap006<String, String> _map;
  def mgu = new MyGroovyUtil()
  def mga = new MyGroovyAssert()


  def setup() throws Exception {
    _map = new MultiValueMap006<String, String>();
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
}
