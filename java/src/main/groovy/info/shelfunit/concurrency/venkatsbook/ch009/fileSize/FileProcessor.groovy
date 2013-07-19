package info.shelfunit.concurrency.venkatsbook.ch009.fileSize

import groovy.transform.Immutable

import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor
import java.util.concurrent.TimeUnit

// from Programming Concurrency on the JVM by Venkat Subramaniam



class FileProcessor extends DefaultActor{
  def sizeCollector

  public FileProcessor( theSizeCollector ) { 
    sizeCollector = theSizeCollector
  }

  void afterStart() { 
    registerToGetFile()
  }

  void registerToGetFile() { 
    sizeCollector.send(new RequestAFile())
  }

  void act() {
    loop { 
      react { message ->
	def file = new File(message.fileName)
	def size = 0
	if ( !file.isDirectory() ) { 
	  size = file.length()
	} else { 
	  def children = file.listFiles()
	  if ( children != null ) { 
	    children.each { child ->
	      if (child.isFile()) {
		size += child.length()
	      } else { 
		sizeCollector.send( new FileToProcess(child.path) )
	      }
	    }
	  }
	  sizeCollector.send(new FileSize(size))
	  registerToGetFile()
	}
      } // end react
    } // end loop
  } // end act


} // end FileProcessor





