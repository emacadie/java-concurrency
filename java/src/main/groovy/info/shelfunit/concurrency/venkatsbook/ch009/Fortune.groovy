package info.shelfunit.concurrency.venkatsbook.ch009

import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

// from Programming Concurrency on the JVM by Venkat Subramaniam

class Fortune {

  def doStuff() { 
    def fortuneTeller = Actors.actor{ 
      loop { 
	react { name ->
	  println( "$name runs in ${ Thread.currentThread() }" )
	  sender.send( "${name}, you have a bright future" )
	}
      }
    } // end fortuneTeller

    def message = fortuneTeller.sendAndWait( "Joe", 1, TimeUnit.SECONDS )
    println message
    println "message is a ${message.class.name}\n"

    println( "main stuff runs in ${ Thread.currentThread() } \n" )

    Thread.sleep( 1000 )

    def latch = new CountDownLatch( 2 )
    fortuneTeller.sendAndContinue( "Rob" ) { println "Rob: ${it}"; latch.countDown() }
    fortuneTeller.sendAndContinue( "Fred" ) { println it; latch.countDown() }
    println("Rob and Fred are keeping their fingers crossed")

    if ( !latch.await( 1, TimeUnit.SECONDS ) ) {
      println( "Fortune teller did not respond before timeout" )
    } else { 
      println( "Bob and Fred are happy campers" )
    }

  } // end doStuff

  static void main( String [] args ) { 
    println "Starting main"
    Fortune fortune = new Fortune()
    fortune.doStuff()
    println "Ending main"
  } // end main

} // end Fortune

