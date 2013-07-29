package info.shelfunit.concurrency.venkatsbook.multithreadtests;

// import com.sun.xml.internal.bind.v2.util.QNameMap;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
The final step is to ensure that the lock and unlock are safe guarded by
try and finally. In this step, we'll write a failing test. In the next
step, we'll fix the code to pass that test.

When you run the code, ensure the test is failing:
at MultiValueMapTest.testPutLockAndUnlockAreGuardedByTryFinally(MultiValueMapTest.java:108)
 */

public class MultiValueMap011<K, V> {
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
    putValueForAKey(key, value);
    _lock.unlock();
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


