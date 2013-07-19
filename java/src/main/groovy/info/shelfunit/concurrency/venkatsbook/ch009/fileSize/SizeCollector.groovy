package info.shelfunit.concurrency.venkatsbook.ch009.fileSize

import groovy.transform.Immutable

// import groovyx.gpars.actor.Actors
// import groovyx.gpars.actor.DefaultActor
import groovyx.gpars.actor.DynamicDispatchActor
// import java.util.concurrent.TimeUnit

// from Programming Concurrency on the JVM by Venkat Subramaniam

class SizeCollector extends DynamicDispatchActor{
  
  def toProcessFileNames = []
  def idleFileProcessors = []
  def pendingNumberOfFilesToVisit = 0
  def totalSize = 0L
  final def start = System.nanoTime()

  def sendAFileToProcess() {
    if (toProcessFileNames && idleFileProcessors) {
      idleFileProcessors.first() << new FileToProcess(toProcessFileNames.first())
      idleFileProcessors = idleFileProcessors.tail()
      toProcessFileNames = toProcessFileNames.tail()
      println("idleFileProcessors is a ${idleFileProcessors.class.name}")
      println("toProcessFileNames is a ${toProcessFileNames.class.name}")
    }
  } // sendAFileToProcess() 

  void onMessage(RequestAFile message) {
    idleFileProcessors.add(sender)
    sendAFileToProcess()
  }

  void onMessage(FileToProcess message) {
    toProcessFileNames.add(message.fileName)
    pendingNumberOfFilesToVisit += 1
    sendAFileToProcess()
  }

  void onMessage(FileSize message) {
    totalSize += message.size
    pendingNumberOfFilesToVisit -= 1
    if (pendingNumberOfFilesToVisit == 0) {
      def end = System.nanoTime()
      println("Total size is ${totalSize}")
      println("Time taken is ${(end - start)/1.0e9}")
      terminate()
    }
  }

  void onMessage(Object message) { 
    println("Handling default message")
  }

} // end class SizeCollector 





