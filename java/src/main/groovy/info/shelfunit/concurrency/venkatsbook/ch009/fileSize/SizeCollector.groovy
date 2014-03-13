package info.shelfunit.concurrency.venkatsbook.ch009.fileSize

import groovy.transform.Immutable
import groovyx.gpars.actor.DynamicDispatchActor

// from Programming Concurrency on the JVM by Venkat Subramaniam

class SizeCollector extends DynamicDispatchActor{
  
  def toProcessFileNames = []
  def idleFileProcessors = []
  def pendingNumberOfFilesToVisit = 0
  def totalSize = 0L
  final def start = System.nanoTime()

  def sendAFileToProcess() {
    if ( toProcessFileNames && idleFileProcessors ) {
      def ifpSize01 = idleFileProcessors.size
      idleFileProcessors.first() << new FileToProcess(toProcessFileNames.first())
      def ifpSize02 = idleFileProcessors.size
      idleFileProcessors = idleFileProcessors.tail()
      def ifpSize03 = idleFileProcessors.size
      toProcessFileNames = toProcessFileNames.tail()
      // println("idleFileProcessors is a ${idleFileProcessors.class.name}")
      // println("idleFileProcessors sizes: 1: ${ifpSize01} 2: ${ifpSize02} 3: ${ifpSize03}")
      // println("toProcessFileNames is a ${toProcessFileNames.class.name}")
    }
  } // sendAFileToProcess() 

  void onMessage( RequestAFile message ) {
    // println( "idleFileProcessors sender is a " + sender.class.name )
    idleFileProcessors.add( sender )
    sendAFileToProcess()
  }

  void onMessage( FileToProcess message ) {
    toProcessFileNames.add( message.fileName )
    pendingNumberOfFilesToVisit += 1
    sendAFileToProcess()
  }

  void onMessage( FileSize message ) {
    totalSize += message.size
    pendingNumberOfFilesToVisit -= 1
    if ( pendingNumberOfFilesToVisit == 0 ) {
      def end = System.nanoTime()
      println( "Total size is ${totalSize}" )
      println( "Time taken is ${(end - start)/1.0e9}" )
      terminate()
    }
  }

  void onMessage( Object message ) { 
    println( "Handling default message" )
  }

} // end class SizeCollector 





