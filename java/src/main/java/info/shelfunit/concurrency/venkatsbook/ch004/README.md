From chapter 4 Programming Concurrency on the JVM by Venkat Subramaniam   


TotalFileSizeSequential.java just gets the size of all the files in a directory tree without using threads.   
NaivelyConcurrentTotalFileSize.java is the first attempt at using concurrency. It uses Executors and Futures.    
ConcurrentTotalFileSize.java is a bit much to explain in a sentence. Even Dr S says it is complex.   

AccessingMap.java goes over concurrent colletions.  
Account.java
AccountRunner.java
AccountService.java

ConcurrentTotalFileSizeWLatch.java
ConcurrentTotalFileSizeWQueue.java
FileSizeCaller.java
FileSize.java
FileSizeWorker.java
LockException.java


