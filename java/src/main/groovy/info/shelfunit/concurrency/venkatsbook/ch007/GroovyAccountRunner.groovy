package info.shelfunit.concurrency.venkatsbook.ch007

// from Programming Concurrency on the JVM by Venkat Subramaniam                

import clojure.lang.Ref
import clojure.lang.LockingTransaction
import java.util.concurrent.Callable

public class GroovyAccountRunner { 

  def transfer(from, to, amount) {
    LockingTransaction.runInTransaction( { 
      to.deposit(amount)
      from.withdraw(amount)
    } as Callable ) 
  } // end transfer

  def transferAndPrint(from, to, amount) { 
    try { 
      transfer(from, to, amount)
    } catch (Exception ex) {
      println("transfer failed $ex")
    }
    println("\n")
    println("Balance of from account is $from.balance")
    println("Balance of to account is $to.balance")
    println("\n-----\n")
  } // end transferAndPrint

  def doStuff() { 
    def account1 = new GroovyAccount(2000)
    def account2 = new GroovyAccount(100)
    transferAndPrint(account1, account2, 500)
    transferAndPrint(account1, account2, 5000)
  }

  def public static void main ( String [] args ) { 
    GroovyAccountRunner  gaRunner = new GroovyAccountRunner()
    gaRunner.doStuff()
  } // end method main

} // end class GroovyAccountRunner 
