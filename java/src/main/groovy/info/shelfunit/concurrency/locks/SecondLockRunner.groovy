package info.shelfunit.concurrency.locks

import groovy.time.Duration 

class SecondLockRunner { 

  def doStuff() { 
    DebugClosureLock cLock = new DebugClosureLock()
    def dur = new Duration(  30, // days 
        2, // hours 
        45, // minutes 
        32, // seconds 
        123 // millis 
    )
    println( "about to call dur.toString() " )
    cLock.lockSomeCode( { println "${dur.toString()}" } )
    def dur2 = new Duration(  3, // days 
        20, // hours 
        43, // minutes 
        31, // seconds 
        522 // millis 
    )
    println( "\nabout to call dur2.toString() " )
    cLock.lockSomeCode { 
        println "${dur2.toString()}"
        println "dur2 as long: ${dur2.toMilliseconds()}"
    }
    
  }

  static void main( String[] args ) { 
    SecondLockRunner slr = new SecondLockRunner()
    slr.doStuff()
  }
} // end class

