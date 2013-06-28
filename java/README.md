This project will have code for concurrency in Java and a few other JVM languages.   

To run a single test, do this:  
gradle -Dtest.single=HelloSpock test -info   
gradle -Dtest.single=DSRecordSpockTest test -info  
   

To run with Groovy:   
gradle runGroovy -PmainClass="org.lookup"    

To run with Java:       
gradle runJava -PmainClass="de.vogella.concurrency.callables.CallableFutures" -Diterations=10    

gradle runJava -PmainClass="info.shelfunit.concurrency.callable.ForwardCallableRunner" -Diterations=10    

gradle runJava -PmainClass="info.shelfunit.concurrency.callable.BackwardCallableRunner" -Diterations=10    

Code in package info.shelfunit.concurrency.venkatsbook.ch002 from Programming Concurrency on the JVM by Venkat Subramaniam  
Venkat, chapter 2:   
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch002.SequentialNAV   
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch002.ConcurrentNAV   
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch002.ConcurrentCallableNAV    
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch002.SequentialPrimeFinder -PmainArgs=10000000     
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch002.ConcurrentPrimeFinder -PmainArgs="10000000 2 2"  


Venkat, chapter 4:  
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch004.TotalFileSizeSequential  -PmainArgs="/home/ericm/github"  
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch004.NaivelyConcurrentTotalFileSize  -PmainArgs="/home/ericm/github"     
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch004.ConcurrentTotalFileSize  -PmainArgs="/home/ericm/github"     
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch004.ConcurrentTotalFileSizeWLatch -PmainArgs="/home/ericm/github"     
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch004.ConcurrentTotalFileSizeWQueue -PmainArgs="/home/ericm/github"     
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch004.FileSize -PmainArgs="/home/ericm/github"     
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch004.FileSizeCaller -PmainArgs="/home/ericm/github"     
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch004.AccessingMap     
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch004.AccountRunner     

gradle runJava -PmainClass=info.shelfunit.concurrency.comparison.ComparisonRunner -PmainArgs="SynchronizedHolder 10000"      
gradle runJava -PmainClass=info.shelfunit.concurrency.comparison.ComparisonRunner -PmainArgs="LockHolder 10000"      
gradle runJava -PmainClass=info.shelfunit.concurrency.comparison.ComparisonRunner -PmainArgs="SingleLockHolder 10000"      

Venkat, chapter 6:   
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch006.stm.UseEnergySource    
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch006.stm.UseSeparateEnergySource    
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch006.stm.UseMultiverseEnergySource    
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch006.nested.AccountService   
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch006.nested.MultiverseAccountService   
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch006.events.UseCounter   
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch006.events.UseMultiverseCounter   
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch006.collections.UseScores   
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch006.collections.UseMultiverseScores   
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch006.writeSkew.UsePortfolio      

Venkat, chapter 7:   
gradle runGroovy -PmainClass=info.shelfunit.concurrency.venkatsbook.ch007.GroovyAccountRunner    
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch007.Transfer    
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch007.STransfer    

Venkat, chapter 8:  
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch008.UseHollywoodActor     
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch008.UseHollywoodActorWithParams     
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch008.UseFortuneTeller     
gradle runJava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch008.UseFortuneTeller002     
gradle runjava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch008.primes.UsePrimes -PmainArgs="10000000 100"      
gradle runjava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch008.primes.UsePrimes002 -PmainArgs="10000000 100"      
gradle runjava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch008.primes.UsePrimesWithFinder -PmainArgs="10000000 100"      
That should give  664579

/home/ericm/github/java-concurrency/java/src/main/java/info/shelfunit/concurrency/venkatsbook/ch008/UseHollywoodActor.java:15: warning: [deprecation] tell(Object) in ActorRef has been deprecated    
      scottBakula.tell("Captain Archer");     
It would have been nice to see that in the Javadoc.    


Writing tests in Groovy: http://hamletdarcy.blogspot.com/2008/04/testing-java-from-groovy-2.html   

Java API: http://docs.oracle.com/javase/7/docs/api/    

Clojure Javadoc: http://www.jarvana.com/jarvana/view/org/clojure/clojure/1.4.0-alpha4/clojure-1.4.0-alpha4-javadoc.jar!/index.html   

Also look into this: https://github.com/krukow/clj-ds  

Akka Javadoc: http://doc.akka.io/japi/akka/2.1.4/   

Akka is now using ScalaSTM for software transactional memory. See example here:    
http://doc.akka.io/docs/akka/2.0.4/project/migration-guide-1.3.x-2.0.x.html    
Home of ScalaSTM: http://nbronson.github.io/scala-stm/index.html     
Home of Multiverse: http://multiverse.codehaus.org/60second.html    
http://www.scala-lang.org/archives/downloads/distrib/files/nightly/docs/library/index.html#scala.concurrent.Future    

A good presentation on Spock: http://www.slideshare.net/elizhender/spock-pres-15236797    



