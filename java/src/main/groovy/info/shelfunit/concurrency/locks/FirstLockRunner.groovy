package info.shelfunit.concurrency.locks

class FirstLockRunner { 

  def doStuff() { 
    ClosureLock cLock = new ClosureLock()
    def x = 0
    println( "before: X is ${x}" )
    cLock.lockSomeCode( { x++ } )
    println( "after: X is ${x}" )
    x = 3
    println( "before: X is ${x}" )
    cLock.lockSomeCode{  
        x = x * 4
        x = x + 1
    }
    
    println( "after: X is ${x}" )

    x = 2
    println( "before: X is ${x}" )
    cLock.lockSomeCode( { x++; x = x + 3 } )
    println( "after: X is ${x}" ) // should be 6

    println( "\nAbout to start multiline closure\n" )
    cLock.lockSomeCode {
        println( "In multiline closure" )
        def list = [ 5, 6, 7, 8 ]
        println( "list.get(2): ${list.get( 2 )}" )
        println( "Done with multiline" )
    }
    

  }

  def static void main( String[] args ) { 
    FirstLockRunner flr = new FirstLockRunner()
    flr.doStuff()
  }
} // end class
