package info.shelfunit.concurrency.venkatsbook.multithreadtests;

// import com.sun.xml.internal.bind.v2.util.QNameMap;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
   Now let's fix the put method to make the failing test pass.

Hope this gives you an idea of how to approach unit testing multithreaded
code that requires synchronization. I will leave it as homework for you to
test the thread-safety of other methods in the class. :)
 */
public class MultiValueMap012<K, V> {
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
    try {
      putValueForAKey(key, value);
    } finally {
      _lock.unlock();
    }
  }

  protected void putValueForAKey(K key, V value) {
    List<V> values = _map.get(key);

    if (values == null) {
      values = new ArrayList<V>();
      _map.put(key, values);
    }

    values.add(value);
  }

  protected void setLock(Lock lock) {
    _lock = lock;
  }
}
