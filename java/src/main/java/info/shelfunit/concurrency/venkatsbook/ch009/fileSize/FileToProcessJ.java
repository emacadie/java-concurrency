package info.shelfunit.concurrency.venkatsbook.ch009.fileSize;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public final class FileToProcessJ {
    private final String fileName;
    
    public FileToProcessJ( String argFileName ) {
	fileName = argFileName;
    }

    public String getFileName() {
	return fileName;
    }

} // end FileToProcessJ





