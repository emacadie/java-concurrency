package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import junit.framework.TestCase;

import info.shelfunit.util.MyGroovyAssert
import info.shelfunit.util.MyGroovyUtil
import spock.lang.Specification

/**
In this step, we're starting out with a test class for the
MultiValueMap and have one passing test with no real implementation.
 */

public class MultiValueMap001SpockTest extends Specification {

  MultiValueMap001<String, String> _map;
  def mgu = new MyGroovyUtil()
  def mga = new MyGroovyAssert()


  def setup() throws Exception {
    _map = new MultiValueMap001<String, String>();
  }

  def "test Map Empty Upon Create"() {
    expect:
    0 == _map.getSize()
  }
}

