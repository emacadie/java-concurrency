package info.shelfunit.concurrency.venkatsbook.multithreadtests;

// import com.sun.xml.internal.bind.v2.util.QNameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
In this step, we'll write code that allows us to put two values for one
key.
 */
public class MultiValueMap004<K, V> {
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
    List<V> values = _map.get(key);

    if (values == null) {
      values = new ArrayList<V>();
      _map.put(key, values);
    }

    values.add(value);
  }
}
