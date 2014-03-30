package info.shelfunit.concurrency.venkatsbook.ch009.fileSize

import java.util.concurrent.TimeUnit

// from Programming Concurrency on the JVM by Venkat Subramaniam

class FileSizeFinder {
  
  void doStuff( filePath ) { 
    def sizeCollector = new SizeCollector().start()
    sizeCollector.send( new FileToProcess( filePath ) )
    100.times{ new FileProcessor( sizeCollector ).start() }
    sizeCollector.join( 100, TimeUnit.SECONDS )
  }

  def static void main( String[] args ) { 
    println( "ppppppppppppppppppppppppppppppp" )
    println( "Here is args[0]: ${args[0]}" )
    FileSizeFinder fsFinder = new FileSizeFinder()
    fsFinder.doStuff( args[ 0 ] )
  }

} // end class FileSizeFinder





