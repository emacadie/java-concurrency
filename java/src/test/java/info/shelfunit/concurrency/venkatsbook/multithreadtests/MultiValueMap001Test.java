package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import junit.framework.TestCase;

/**
In this step, we're starting out with a test class for the
MultiValueMap and have one passing test with no real implementation.
 */

public class MultiValueMap001Test extends TestCase {

  MultiValueMap001<String, String> _map;

  @Override protected void setUp() throws Exception {
    _map = new MultiValueMap001<String, String>();
  }

  public void testMapEmptyUponCreate() {
    assertEquals(0, _map.getSize());
  }
}

