package info.shelfunit.concurrency.venkatsbook.multithreadtests;

// import com.sun.xml.internal.bind.v2.util.QNameMap;

import java.util.*;

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
public class MultiValueMap006<K, V> {
  private Map<K, List<V>> _map = new HashMap<K, List<V>>();

  public int getSize() {
    return _map.keySet().size();
  }

  public List<V> getValues(K key) {
    List<V> values = _map.get(key);
    if (values == null) {
      values = new ArrayList<V>();
    }

    return Collections.synchronizedList(values);
  }

  public void put(K key, V value) {
    List<V> values = _map.get(key);

    if (values == null) {
      values = new ArrayList<V>();
      _map.put(key, values);
    }

    values.add(value);
  }
}
