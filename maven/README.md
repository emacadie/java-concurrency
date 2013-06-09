A sample for the Typesafe Stack (http://typesafe.com/stack).

Akka 2.0 sample project using Java and Maven.

To run and test it use Maven invoke: 'mvn compile exec:java'  

I took the code as-is, and it is in the info.shelfunit.akka package.  

I put the classes into separate files in the info.shelfunit.akka.separate package. Still works.  

mvn compile

mvn exec:java -Dexec.mainClass="info.shelfunit.akka.Pi"  

mvn exec:java -Dexec.mainClass="info.shelfunit.akka.separate.Pi2"  
mvn exec:java -Dexec.mainClass="info.shelfunit.akka.separate.PiRunner"  

java -cp /home/ericm/.m2/repository/org/scala-lang/scala-library/2.9.1-1/scala-library-2.9.1-1.jar:\
/home/ericm/.m2/repository/com/typesafe/akka/akka-actor/2.0.1/akka-actor-2.0.1.jar:\
/home/ericm/github/akka-tutorial/target/akka-tutorial-0.1-SNAPSHOT.jar \
info.shelfunit.akka.Pi   

java -cp /home/ericm/.m2/repository/org/scala-lang/scala-library/2.9.1-1/scala-library-2.9.1-1.jar:\
/home/ericm/.m2/repository/com/typesafe/akka/akka-actor/2.0.1/akka-actor-2.0.1.jar:\
/home/ericm/github/akka-tutorial/target/akka-tutorial-0.1-SNAPSHOT.jar \
info.shelfunit.akka.separate.Pi2

java -cp /home/ericm/.m2/repository/org/scala-lang/scala-library/2.9.1-1/scala-library-2.9.1-1.jar:\
/home/ericm/.m2/repository/com/typesafe/akka/akka-actor/2.0.1/akka-actor-2.0.1.jar:\
/home/ericm/github/akka-tutorial/target/akka-tutorial-0.1-SNAPSHOT.jar \
info.shelfunit.akka.separate.PiRunner


Akka Migration Guide: http://doc.akka.io/docs/akka/2.1.0/project/migration-guide-2.0.x-2.1.x.html   
