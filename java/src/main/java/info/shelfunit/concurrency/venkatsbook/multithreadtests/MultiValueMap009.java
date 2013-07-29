package info.shelfunit.concurrency.venkatsbook.multithreadtests;

// import com.sun.xml.internal.bind.v2.util.QNameMap;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**

In the previous step we used a mock for the Lock interface. While the
MultiValueMap by default is using the ReentrantLock, using a protected
method, we're able to mock it out.

However, there are two concerns.

1. What if the put method called lock and unlock out of sequence or
in the wrong place.
2. As a savvy programmer, you probably realized that if an exception
is thrown, the code will not perform unlock. So, we need a try and
finally. Don't jump to write try and finally, we'll force the test
to fail before writing that piece of code. Let's get to that soon,
we'll take care of the first problem first.

How do we tell that put actually called the lock first, then did the
hard work it has to do and then finally did the unlock? We need to
intercept right within the execution of put. We can do this by
refactoring the code.

In this step, we'll only refactor the code. In the next, we'll put
that to good use. We refactored the guts of the put method into
a protected method named putValueForAKey.
 */

public class MultiValueMap009<K, V> {
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
