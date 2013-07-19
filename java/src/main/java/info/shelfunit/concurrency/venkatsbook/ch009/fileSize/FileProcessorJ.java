package info.shelfunit.concurrency.venkatsbook.ch009.fileSize;

import groovy.transform.Immutable;

import groovyx.gpars.actor.Actors;
import groovyx.gpars.actor.DefaultActor;
import java.util.concurrent.TimeUnit;

import java.io.File;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class FileProcessorJ extends DefaultActor {
    private SizeCollectorJ sizeCollector;

    public FileProcessorJ( SizeCollectorJ theSizeCollector ) { 
	sizeCollector = theSizeCollector;
    }

    void afterStart() { 
	this.registerToGetFile();
    }

    private void registerToGetFile() { 
	sizeCollector.send(new RequestAFileJ());
    }

    public void onMessage( final FileToProcessJ message ) {
	File file = new File(message.getFileName());
	long size = 0;
	if ( !file.isDirectory() ) { 
	    size = file.length();
	} else { 
	    File[] children = file.listFiles();
	    if ( children != null ) { 
		for (File child : children) { 
		    if (child.isFile()) {
			size += child.length();
		    } else { 
			sizeCollector.send( new FileToProcessJ(child.getPath()) );
		    }
		}
	    }
	    sizeCollector.send(new FileSizeJ(size));
	    registerToGetFile();
	} 
    } // end onMessage

} // end FileProcessorJ





