package info.shelfunit.concurrency.venkatsbook.multithreadtests;

// import com.sun.xml.internal.bind.v2.util.QNameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
We did two things in this step.

1. Added a method for getting value for a non-existent key.
2. Added a method to put a value for a key.

We drove it test first, minimum code next.

We're starting out slow, we will not even address multithreading
for a few steps, but that's OK. Let's evolve this code.
 */
public class MultiValueMap002<K, V> {
  private Map<K, List<V>> _map = new HashMap<K, List<V>>();

  public int getSize() {
    return 0;
  }

  public List<V> getValues(K key) {
    List<V> values = _map.get(key);
    if (values == null) {
      values = new ArrayList<V>();
    }

    return values;
  }

  public void put(K key, V value) {
    List<V> values = new ArrayList<V>();
    values.add(value);
    _map.put(key, values);
  }
}
