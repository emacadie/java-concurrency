This chapter STM: Software Transactional Memory. It starts out using the Clojure STM functions in Groovy.    

GroovyAccount.groovy has a balance, and methods to deposit and withdraw. The balance is held in a variable that is a clojure.lang.Ref. The deposit amd withdraw methods both use clojure.lang.LockingTransaction. They use the LockingTransaction.runInTransaction method, which takes a closure as a java.util.concurrent.Callable.    
    
GroovyAccountRunner.groovy instatiates a couple of GroovyAccount objects, and transfers money between them. The transfer itself also uses a clojure.lang.LockingTransaction object, calling the runInTransaction method. So, Yo Dawg, I heard you like JVM concurrency, so I called a transaction within a transaction. It performs two transfers, one that fails, and one that succeeds.    

There was a section to use Akka STM in Groovy, but since there have been a lot of changes in the way Akka handles STM, I decided I did not want to deal with it.    
