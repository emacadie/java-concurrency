package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import info.shelfunit.util.MyGroovyAssert
import info.shelfunit.util.MyGroovyUtil
import spock.lang.Specification

/**
We did two things in this step.

1. Added a method for getting value for a non-existent key.
2. Added a method to put a value for a key.

We drove it test first, minimum code next.

We're starting out slow, we will not even address multithreading
for a few steps, but that's OK. Let's evolve this code.
 */
public class MultiValueMap002SpockTest extends Specification {

  MultiValueMap002<String, String> _map;
  def mgu = new MyGroovyUtil()
  def mga = new MyGroovyAssert()

  def setup() throws Exception {
    _map = new MultiValueMap002<String, String>();
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
}
