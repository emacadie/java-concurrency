package info.shelfunit.concurrency.venkatsbook.ch008;

import akka.dispatch.OnSuccess;

public final class PrintResult<T> extends OnSuccess<T> {
    @Override public final void onSuccess(T t) {
	System.out.println(t);
    }
}
