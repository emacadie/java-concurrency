package info.shelfunit.concurrency.venkatsbook.ch009

import groovy.transform.Immutable

import groovyx.gpars.actor.Actors
import groovyx.gpars.actor.DefaultActor
import groovyx.gpars.actor.DynamicDispatchActor
import java.util.concurrent.TimeUnit

// from Programming Concurrency on the JVM by Venkat Subramaniam

class MultiMessage005 {

  @Immutable
  class LookUp { 
    String ticker
  }

  @Immutable
  class Buy { 
    String ticker
    int quantity
  }

  def trader = new  DynamicDispatchActor({ 
       
	when { Buy message -> 
	  println( "Processing Buy" )
	  println( "Buying ${message.quantity} shares of ${message.ticker}" )
	}

	when { LookUp message -> 
	  println( "Processing Lookup" )
	  sender.send( ( int ) ( Math.random() * 1000 ) )
	}

	when { Object message ->
	  println( "Processing everything else: ${message}" )
	}
	
    }).start() // the call to start could go here
    

  def doStuff() { 
    // def trader = new Trader().start() // the call to start could go here
    
    trader.sendAndContinue( new LookUp( "XYZ" ) ) { 
      println( "Price of XYZ stock is ${it}" )
    }

    trader.send( new Buy( "XYZ", 200 ) )
    trader.join( 1, TimeUnit.SECONDS )

    trader.send( "This is a string message" )
    trader.send( [ 'Kirk', 'Picard', 'Sisko', 'Janeway', 'Archer' ] )
    println( "Done" )
  } // end doStuff

  def static void main( String[ ] args ) { 
    def mm003 = new MultiMessage003()
    mm003.doStuff()
    Thread.sleep( 2000 )
  } // end main

} // MultiMessage001 

