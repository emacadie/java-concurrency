package info.shelfunit.concurrency.venkatsbook.ch004;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class LockException extends Exception {
    public LockException(final String message) {
	super(message);
    }
}
