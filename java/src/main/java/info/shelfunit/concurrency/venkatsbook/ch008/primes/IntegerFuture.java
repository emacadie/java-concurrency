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
	// System.out.println( "t is a " + t.getClass().getName() );
	// Class integerClass = Class.forName("java.lang.Integer");
	Integer tInt = Integer.class.cast(t);
	// System.out.println(tInt);
	holdInt += tInt.intValue();
	// System.out.println( "value of holdInt: " + holdInt );
    }
}
