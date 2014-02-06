This deals with commit and rollback events for transactions.    

In the version of Akka that he was using, akka.stm.Atomic had a couple of methods that were coded in the transaction. Both of them took a Runnable as an argument. deferred allowed you to code for something if the transaction was successful, like sending an email or logging. compensating was run if the transaction failed. I guess you could also use that to email and log as well.    

Counter.java uses Scala STM. It has a decrement method which is called by UseCounter.java. This uses STM.afterCommit for events are a successful transaction, and STM.afterRollback for events after an unsuccessful transaction. Both of them take java.lang.Runnable as an arg.     

MultiverseCounter.java uses Multiverse, and it is called by UseMultiverseCounter.java. If the transaction is successful, it calls StmUtils.scheduleDeferredTask, and if it is not it calls StmUtils.scheduleCompensatingTask. Both of these take java.lang.Runnable.   


