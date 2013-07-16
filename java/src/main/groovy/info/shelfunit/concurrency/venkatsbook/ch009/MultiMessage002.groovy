package info.shelfunit.concurrency.venkatsbook.ch009

import groovy.transform.Immutable

import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor
import groovyx.gpars.actor.DynamicDispatchActor
import java.util.concurrent.TimeUnit

// from Programming Concurrency on the JVM by Venkat Subramaniam

class MultiMessage002 {

  @Immutable
  class LookUp { 
    String ticker
  }

  @Immutable
  class Buy { 
    String ticker
    int quantity
  }

  class Trader extends DynamicDispatchActor { 
    void onMessage( Buy message ) { 
      println("Processing Buy")
      println( "Buying ${message.quantity} shares of ${message.ticker}" )
    }

    void onMessage( LookUp message ) { 
      println("Processing Lookup")
      sender.send( ( int ) (Math.random() * 1000 ) )
    }

    void onMessage(Object message) { 
      println("This was something else: ${message}")
    }

  } // end class Trader



  def doStuff() { 
    def trader = new Trader().start()
    trader.sendAndContinue( new LookUp("XYZ") ) { 
      println( "Price of XYZ stock is ${it}" )
    }

    trader.send( new Buy( "XYZ", 200 ) )
    trader.join( 1, TimeUnit.SECONDS )
    trader.send("This is a String")
  } // end doStuff

  def static void main( String[ ] args ) { 
    MultiMessage002  mm002 = new MultiMessage002()
    mm002.doStuff()
    Thread.sleep(2000)
  } // end main

} // MultiMessage001 

