package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import junit.framework.TestCase;

/**
We did two things in this step.

1. Added a method for getting value for a non-existent key.
2. Added a method to put a value for a key.

We drove it test first, minimum code next.

We're starting out slow, we will not even address multithreading
for a few steps, but that's OK. Let's evolve this code.
 */
public class MultiValueMap002Test extends TestCase {

  MultiValueMap002<String, String> _map;

  @Override protected void setUp() throws Exception {
    _map = new MultiValueMap002<String, String>();
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
}
