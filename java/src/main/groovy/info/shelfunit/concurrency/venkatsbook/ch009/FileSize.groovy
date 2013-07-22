package info.shelfunit.concurrency.venkatsbook.ch009

import groovy.transform.Immutable

import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor
import groovyx.gpars.dataflow.DataflowQueue
import groovyx.gpars.group.DefaultPGroup
import java.util.concurrent.TimeUnit

// from Programming Concurrency on the JVM by Venkat Subramaniam

class FileSize {
  
  private final pendingFiles = new DataflowQueue()
  private final sizes = new DataflowQueue()
  private final group = new DefaultPGroup()

  def findSize(File file) { 
    def size = 0
    if(!file.isDirectory()) { 
      size = file.length()
    } else { 
      def children = file.listFiles()
      if (children != null) { 
        children.each {  child ->
          if(child.isFile()) { 
            size += child.length()
          } else { 
            pendingFiles << 1
            group.task {  findSize(child) }
          }
        }
      }
    }

    pendingFiles << -1
    sizes << size
  } // def findSize(File file)

  def findTotalFileSize(File file) { 
    pendingFiles << 1
    group.task {  findSize(file) }

    int filesToVisit = 0
    long totalSize = 0
    while(true) { 
      totalSize += sizes.val
      if(!(filesToVisit += (pendingFiles.val + pendingFiles.val))) break
    }
    
    totalSize
  } // def findTotalFileSize(File file) 
  
  def static void main( String[ ] args ) { 
    FileSize fs = new FileSize()

    def start = System.nanoTime()
    println("Calling findTotalFileSize with ${args[0]}")
    def totalSize = fs.findTotalFileSize(new File(args[0]))
    println("Total size ${totalSize}")
    println("Time taken: ${(System.nanoTime() - start) / 1.0e9}")
    Thread.sleep(2000)
  } // end main

} // FileSize

