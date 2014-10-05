This is a project that attempts to solve deadlock issues in examples from the web with the Lombok project.   

It can be found at http://projectlombok.org/features/index.html   

gradle runJava -PmainClass="info.shelfunit.app.really.big.tutorial.Deadlock"  
gradle runJava -PmainClass="info.shelfunit.app.really.big.tutorial.DeadlockLombok"  
gradle runJava -PmainClass="info.shelfunit.app.fromlinkedin.A"  
gradle runJava -PmainClass="info.shelfunit.app.fromlinkedin.LombokA"   
gradle runJava -PmainClass="info.shelfunit.app.fromroseindia.DeadLockDemo"  
gradle runJava -PmainClass="info.shelfunit.app.fromroseindia.DeadLockDemoLombok"  
gradle runJava -PmainClass="info.shelfunit.app.newpackage2.Deadlock"  
gradle runJava -PmainClass="info.shelfunit.app.newpackage2.DeadlockLombok"  

using the LockVsSync:  
gradle runJava -PmainClass="info.shelfunit.app.lock.LockVsSync"  


  


