package info.shelfunit.concurrency.venkatsbook.ch009

import groovy.transform.Immutable

import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor
import java.util.concurrent.TimeUnit

// from Programming Concurrency on the JVM by Venkat Subramaniam

class MultiMessage001 {

  @Immutable
  class LookUp { 
    String ticker
  }

  @Immutable
  class Buy { 
    String ticker
    int quantity
  }

  def trader = Actors.actor { 
    loop { 
      react { message ->
	
	if ( message instanceof Buy ) { 
	  println( "Buying ${message.quantity} shares of ${message.ticker}" )
	} else if ( message instanceof LookUp ) {
	  sender.send( ( int ) ( Math.random() * 1000 ) )
	} else { 
	  println( "message is something else: ${message}" )
	}

	}
    }
  } // end trader

  def doStuff() { 
    trader.sendAndContinue( new LookUp( "XYZ" ) ) { 
      println( "Price of XYZ stock is ${it}" )
    }
    trader.send( new Buy( "XYZ", 200 ) )
    trader.join( 1, TimeUnit.SECONDS )
    trader.send( "Sending a string" )
    println("Done")
  } // end doStuff

  def static void main( String[ ] args ) { 
    MultiMessage001  mm001 = new MultiMessage001()
    mm001.doStuff()
    Thread.sleep( 2000 )
  } // end main

} // MultiMessage001 

