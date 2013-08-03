package info.shelfunit.concurrency.venkatsbook.multithreadtests;

import junit.framework.TestCase;

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

public class MultiValueMap006Test extends TestCase {

  MultiValueMap006<String, String> _map;

  @Override protected void setUp() throws Exception {
    _map = new MultiValueMap006<String, String>();
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
  
  public void testCheckSizeAfterPuts() {
    _map.put("1", "one");
    _map.put("2", "two");
    _map.put("1", "uno");

    assertEquals(2, _map.getSize());
  }
  
  public void testEnsureGetValuesReturnsSynchronizedList() {
    _map.put("1", "one");
    List<String> values = _map.getValues("1");

    assertEquals(Collections.synchronizedList(values).getClass(), values.getClass());
  }
}
