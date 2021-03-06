package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import junit.framework.TestCase;

/**
In this step, we'll write code that allows us to put two values for one
key.

 */

public class MultiValueMap004Test extends TestCase {

  MultiValueMap004<String, String> _map;

  @Override protected void setUp() throws Exception {
    _map = new MultiValueMap004<String, String>();
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
}
