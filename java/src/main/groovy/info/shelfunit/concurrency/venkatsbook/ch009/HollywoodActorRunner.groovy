package info.shelfunit.concurrency.venkatsbook.ch009

import java.util.concurrent.TimeUnit
// from Programming Concurrency on the JVM by Venkat Subramaniam

class HollywoodActorRunner { 

  def runCustomActor() { 
    def console = new CustomActor()
    console.start()
    console.send('Message')
    console << 'Message'
  }

  def doStuff() { 
    def depp = new HollywoodActor("Johnny Depp").start()
    println("Just started Depp, about to instantiate Hanks")
    def hanks = new HollywoodActor("Tom Hanks")
    println("About to start hanks")
    hanks.start()
    println("About to send messages 001")
    depp.send('Pretentious')
    hanks.send("Gump")
    println("About to send messages 002")
    depp << "More predictable than he thinks"
    hanks "Deserves another Oscar"
    [depp, hanks]*.join(1, TimeUnit.SECONDS)
    
    println("Done")
    
  } // end doStuff

  def public static void main ( String [] args ) {  
    HollywoodActorRunner haRunner = new HollywoodActorRunner()
    haRunner.doStuff()
    println('Done in main')
    // It seems to need this to print stuff to console
    Thread.sleep(1000)
  } // end method main

} // end HollywoodActorRunner

