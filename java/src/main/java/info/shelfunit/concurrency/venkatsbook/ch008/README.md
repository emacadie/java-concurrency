This chapter deals with actors.    

The files in the main chapter 8 directory use the Akka library: http://akka.io/     

HollywoodActor.java defines a simple actor that takes a message and prints it out. It also prints out the name of the calling thread. UseHollywoodActor.java starts an actor system and then instantiates the actor class in HollywoodActor.java and calls it a few times. Starting an Akka actor system had changed quite a bit from when the book was written to when I tried out the code. In fact, I think it changed while I was going through it. Unlike in the book, you simply call actorOf(). You do not need to call start(). Also, you send a message with the tell() method.     

HollywoodActorWithParams.java is the same as HollywoodActor.java which takes an argument in the constructor. It is invoked by UseHollywoodActorWithParams.java. UseHollywoodActor.java also sends an argument, but it seems to be ignored.    


Next is the actor FortuneTeller.java. It calls getSender() to get the caller, and then invokes the tell() method. UseFortuneTeller.java and UseFortuneTeller002.java pretty much do the same thing, except UseFortuneTeller.java instantiates more FortuneTeller instances. It creates an ArrayList of Acala Futures, which seems like more code than calling a list of Java Futures and Callables. Each message to an Actor instance is done with a call to the static method akka.pattern.Patterns.ask, which takes an Actor and the message as arguments. In each case, I print a line of dashes to the console, to see when different methods are invoked. You get the result by calling scala.concurrent.Await.result.



primes/ are next.    

fileSize/

gpars/



PrintResult.java

transactors/
typed01/
typed02/



