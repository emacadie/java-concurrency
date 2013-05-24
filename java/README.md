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

Venkat's book: up to page 89   

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



Writing tests in Groovy: http://hamletdarcy.blogspot.com/2008/04/testing-java-from-groovy-2.html   

Java API: http://docs.oracle.com/javase/7/docs/api/    

A good presentation on Spock: http://www.slideshare.net/elizhender/spock-pres-15236797    

assertEquals becomes mgu.equals  
assertNull(XXX) becomes mgu.equals(null, XXX)   
assertTrue becomes mga.assertTrue
assertFalse(x == y) becomes mga.that( x != y)    
assertFalse(XX.YY) becomes mga.that(!XX.YY)   
assertNotSame(m_h, h2) becomes !mgu.equals(m_h, h2)    
assertSame(x, y) becomes mgu.equals(x, y)    

