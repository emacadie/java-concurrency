This chapter deals with actors.    

The files in the main chapter 8 directory use the Akka library: http://akka.io/     

HollywoodActor.java defines a simple actor that takes a message and prints it out. It also prints out the name of the calling thread. UseHollywoodActor.java starts an actor system and then instantiates the actor class in HollywoodActor.java and calls it a few times. Starting an Akka actor system had changed quite a bit from when the book was written to when I tried out the code. In fact, I think it changed while I was going through it. Unlike in the book, you simply call actorOf(). You do not need to call start(). Also, you send a message with the tell() method.     



fileSize/
FortuneTeller.java
gpars/

HollywoodActorWithParams.java
primes/
PrintResult.java
README.md
transactors/
typed01/
typed02/
UseFortuneTeller002.java
UseFortuneTeller.java

UseHollywoodActorWithParams.java
