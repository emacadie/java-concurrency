package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import info.shelfunit.util.MyGroovyAssert
import info.shelfunit.util.MyGroovyUtil
import spock.lang.Specification

/**
In this final step before getting into threading related concerns, we'll
ensure the getSize() is working as expected.
 */

public class MultiValueMap005SpockTest extends Specification {

  MultiValueMap005<String, String> _map;
  def mgu = new MyGroovyUtil()
  def mga = new MyGroovyAssert()

  def setup() throws Exception {
    _map = new MultiValueMap005<String, String>();
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
}
