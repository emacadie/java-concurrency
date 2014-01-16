From chapter 4 Programming Concurrency on the JVM by Venkat Subramaniam   


TotalFileSizeSequential.java just gets the size of all the files in a directory tree without using threads.   

NaivelyConcurrentTotalFileSize.java is the first attempt at using concurrency. It uses Executors and Futures.    

ConcurrentTotalFileSize.java is a bit much to explain in a sentence. Even Dr S says it is complex.    

ConcurrentTotalFileSizeWLatch.java uses a CountdownLatch. As it goes through each directory, it adds the directory size to an AtomicLong variable. Another AtomicLong keeps track of how many directories there are left to visit. When that count gets to 0, the latch is released. The issue is you are using a shared, local variable, and Dr S says that is a bad idea.   

ConcurrentTotalFileSizeWQueue.java uses an ArrayBlockingQueue. A method is called from a Runnable that looks at a directory, and puts the size of files in that dir into a Long in the ArrayBlockingQueue. The main thread loops through the queue.     

FileSize.java uses Fork-Join. There is a static inner class called FileSizeFinder. ForkJoin seems to be a lot like ExecutorService, except you do not have to decide ahead of time how big your pool is. You just extend java.util.concurrent.RecursiveTask or java.util.concurrent.RecursiveAction, and call them from java.util.concurrent.ForkJoinPool. From what I have read, one advantage over ExecutorService is that for ExecutorService thread pools you have to specify ahead of time how many threads are in your pool. But ForkJoin seems to be more efficient.    

If you extend RecursiveTask, you need a method called compute which returns a value, and if you extend RecursiveAction, you need a method called compute which returns void. With Callables, you make a call() method, but you do not actually invoke it, you just instantiate the object and put it into the ExecutorPool. With ForkJoin, you do not write code that explicitly invokes compute(). You just instantiate the object, and add it to the ForkJoin pool.   
   
I made a couple of classes that split FileSize.java. FileSizeWorker.java has the compute method, and FileSizeCaller.java has the main method and invokes FileSizeCaller.   
   
Next he covers concurrent collections. AccessingMap.java goes over concurrent collections. It uses a regular HashMap, a synchronized map, and a ConcurrentHashMap.  

Next he covers Locks and the Lock interface, and explains why it is better than using synchronized. One way it is better because for one thing you can call a method called tryLock(), which can take arguments for a time limit. This will prevent threads from deadlocking, but still allow you to wait to get a lock on something.   

For the Lock example, there is Account.java, which has a balance, as well as methods for depositing and withdrawing, as well as Locks to keep things safe. AccountService.java has a method that takes a couple of Account objects and performs a transfer. AccountRunner.java creates the objects and invokes everything. A couple of these may call LockException.java.     








