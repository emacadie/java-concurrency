I used this command to create the project:   
mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DgroupId=info.shelfunit.app.lombok  -DartifactId=lombok-examples  


A few maven commands. I will update this when I add more classes:    

mvn compile  
mvn exec:java -Dexec.mainClass="info.shelfunit.threads.App"   
mvn compile | cat > /dev/null; mvn exec:java -Dexec.mainClass="info.shelfunit.threads.App"  
mvn clean   
mvn package   

mvn exec:java -Dexec.mainClass="info.shelfunit.app.really.big.tutorial.Deadlock"  
mvn exec:java -Dexec.mainClass="info.shelfunit.app.really.big.tutorial.DeadlockLombok"  
mvn exec:java -Dexec.mainClass="info.shelfunit.app.fromlinkedin.A"  
mvn exec:java -Dexec.mainClass="info.shelfunit.app.fromlinkedin.LombokA"   
mvn exec:java -Dexec.mainClass="info.shelfunit.app.fromroseindia.DeadLockDemo"  
mvn exec:java -Dexec.mainClass="info.shelfunit.app.fromroseindia.DeadLockDemoLombok"  
mvn exec:java -Dexec.mainClass="info.shelfunit.app.newpackage2.Deadlock"  
mvn exec:java -Dexec.mainClass="info.shelfunit.app.newpackage2.DeadlockLombok"  

using the LockVsSync:  
mvn exec:java -Dexec.mainClass="info.shelfunit.app.lock.LockVsSync"  

On my system: /home/ericm/.m2/repository/org/projectlombok/lombok/0.11.4/lombok-0.11.4.jar   
To make a jar:  
mvn package   
Using the jar file:
java -cp ./target/lombok-examples.jar info.shelfunit.app.really.big.tutorial.DeadlockLombok  
java -cp ./target/lombok-examples.jar info.shelfunit.app.really.big.tutorial.Deadlock  
java -cp ./target/lombok-examples.jar info.shelfunit.app.fromlinkedin.A   
java -cp ./target/lombok-examples.jar info.shelfunit.app.fromlinkedin.LombokA  
java -cp ./target/lombok-examples.jar info.shelfunit.app.fromroseindia.DeadLockDemo   
java -cp ./target/lombok-examples.jar info.shelfunit.app.fromroseindia.DeadLockDemoLombok   
java -cp ./target/lombok-examples.jar info.shelfunit.app.newpackage2.Deadlock  
java -cp ./target/lombok-examples.jar info.shelfunit.app.newpackage2.DeadlockLombok  
  


