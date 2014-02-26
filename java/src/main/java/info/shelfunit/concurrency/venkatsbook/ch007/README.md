Chapter 7 is about STM libraries in other JVM languages. I worked with the parts that were using the Clojure libraries in Groovy and Java.    

I did not deal with the Akka, Scala or JRuby parts of the chapter. The Groovy part is a lot like this part.    

AccountJava.java has a balance that is a clojure.lang.Ref. There are methods for depositing and withdrawing money. They both use clojure.lang.LockingTransaction.runInTransaction, which takes an inline java.util.concurrency.Callable. Transfer.java creates a couple of accounts, and tries to do a couple of transfers. One of them will fail due to insufficient funds. This transfer is done with a call to LockingTransaction.runInTransaction with an inline Callable.    

I prefer my Callables to be in separate classes. So the S* classes are doing the same thing. The "S" stands for "standalone" or "separate", I cannot remember. SAccountJava.java calls both SAccountJavaDeposit.java for the deposit and SAccountJavaWithdraw.java for the withdrawal. STransfer.java makes a couple of SAccount objects, and then calls STransferCallable.java to so the work. It seems to work, although now that I think about it I guess it might be passing the variables or their references around, which might not be all that thread safe.    



