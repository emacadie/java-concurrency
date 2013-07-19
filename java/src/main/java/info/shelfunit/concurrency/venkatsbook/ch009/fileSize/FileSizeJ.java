package info.shelfunit.concurrency.venkatsbook.ch009.fileSize;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public final class FileSizeJ {
 
    private final long size;
    public FileSizeJ(long argSize) {
	size = argSize;
    }

    public long getSize() {
	return size;
    }
} // end FileSizeJ
