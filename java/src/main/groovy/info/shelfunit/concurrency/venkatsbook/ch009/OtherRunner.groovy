package info.shelfunit.concurrency.venkatsbook.ch009

import groovyx.gpars.actor.Actors
import java.util.concurrent.TimeUnit
// from Programming Concurrency on the JVM by Venkat Subramaniam

class OtherRunner { 

  def doStuff() { 
    def depp = Actors.actor { 
      loop( 3, { println "Done acting" } ) { 
	react { 
	  println "Depp playing role ${it}"
	}
      }
    }
    
    depp.send( 'Pretentious' )
    depp << "More predictable than he thinks"
    depp.send( "Trying to send 3" )
    depp.send( "Trying to send 4" )
    depp.join( 1, TimeUnit.SECONDS )
    println( "Done" )
  } // end doStuff

  def public static void main ( String [] args ) {  
    OtherRunner oRunner = new OtherRunner()
    oRunner.doStuff()
    println('Done in main')
    // It seems to need this to print stuff to console
    // Thread.sleep( 1000 )
  } // end method main

} // end HollywoodActorRunner

