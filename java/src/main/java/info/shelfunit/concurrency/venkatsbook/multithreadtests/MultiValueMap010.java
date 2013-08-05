package info.shelfunit.concurrency.venkatsbook.multithreadtests;

// import com.sun.xml.internal.bind.v2.util.QNameMap;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**

OK, now that we're refactored the code, we can easily check if
put is doing the right thing, that is, calls lock, then does its work,
then calls unlock.
 */

public class MultiValueMap010<K, V> {
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
      System.out.println("In putValueForAKey(K key, V value)  in the real class");
    List<V> values = _map.get(key);

    if (values == null) {
      values = new ArrayList<V>();
      _map.put(key, values);
    }

    values.add(value);
  }

  protected void setLock(Lock lock) {
      System.out.println( "Called set lock" );
    _lock = lock;
  }
}
