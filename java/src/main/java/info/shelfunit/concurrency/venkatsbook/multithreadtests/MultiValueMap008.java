package info.shelfunit.concurrency.venkatsbook.multithreadtests;

// import com.sun.xml.internal.bind.v2.util.QNameMap;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
Fortunately Java 5.0 introduced a Lock interface. Unlike synchronized, which
is a keyword in the language, Lock is an interface that can be easily mocked
out for testing purposes. Lets use that. Don't try to attain perfection
at this point. Take small steps. We will not write a single line of code
without having a test for it.

For put method to be mutually exclusive, it should claim a lock (or monitor)
and then release it. Let's ensure it does that.
 */

public class MultiValueMap008<K, V> {
  private Map<K, List<V>> _map = new HashMap<K, List<V>>();
  private Lock _lock = new ReentrantLock();

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
    _lock.lock();
    List<V> values = _map.get(key);

    if (values == null) {
      values = new ArrayList<V>();
      _map.put(key, values);
    }

    values.add(value);
    _lock.unlock();
  }

  protected void setLock(Lock lock) {
    _lock = lock;
  }
}
