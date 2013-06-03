package info.shelfunit.concurrency.venkatsbook.ch007

// from Programming Concurrency on the JVM by Venkat Subramaniam

import clojure.lang.Ref
import clojure.lang.LockingTransaction
import java.util.concurrent.Callable

public class GroovyAccount { 
  final private Ref currentBalance

  public GroovyAccount(initialBalance) { 
    currentBalance = new Ref(initialBalance)
  }

  def getBalance() { currentBalance.deref() }

  def deposit( amount ) { 
    LockingTransaction.runInTransaction( { 
      if ( amount > 0 ) { 
	currentBalance.set(currentBalance.deref() + amount)
	println("deposit ${amount}...will it stay")
      } else { 
	throw new RuntimeException("Operation invalid")
      }
    } ) as Callable
  } // end deposit

  def withdraw( amount ) { 
    LockingTransaction.runInTransaction( { 
      if ( amount > 0 && currentBalance.deref()>= amount ) { 
	currentBalance.set(currentBalance.deref() - amount)
	println("withdraw ${amount}...will it stay")
      } else { 
	throw new RuntimeException("Operation invalid")
      }
    } ) as Callable
  } // end withdraw

} // end class GroovyAccount
