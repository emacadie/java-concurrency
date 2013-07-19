package info.shelfunit.concurrency.venkatsbook.ch009.fileSize;

import java.util.concurrent.TimeUnit;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class FileSizeFinderJ {
  
    void doStuff(String filePath) throws InterruptedException { 
	SizeCollectorJ sizeCollector = new SizeCollectorJ();
	sizeCollector.start();
	sizeCollector.send(new FileToProcessJ(filePath));
	for (int x = 0; x < 100; x++) {
	    new FileProcessorJ(sizeCollector).start();
	}
	sizeCollector.join(100, TimeUnit.SECONDS);
    }

    public static void main(String[] args) { 
	System.out.println("ppppppppppppppppppppppppppppppp");
	System.out.println("Here is args[0]: " + args[0]);
	FileSizeFinderJ fsFinder = new FileSizeFinderJ();
	try {
	    fsFinder.doStuff( args[0] );
	} catch (Exception e) {
	    e.printStackTrace();
	}
    } // end main

} // end class FileSizeFinderJ
