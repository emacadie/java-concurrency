package info.shelfunit.concurrency.venkatsbook.ch008.primes;

import akka.dispatch.OnSuccess;

public final class IntegerFuture< T > extends OnSuccess< T > {

    private int holdInt;

    public IntegerFuture() {
	holdInt = 0;
    }

    public int getHoldInt() {
	return holdInt;
    }

    @Override public final void onSuccess(T t) {
	Integer tInt = Integer.class.cast(t);
	holdInt += tInt.intValue();
    }
}
