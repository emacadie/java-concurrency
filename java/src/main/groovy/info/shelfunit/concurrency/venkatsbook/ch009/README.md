HollywoodActor.groovy extends DefaultActor. It has a constructor that takes an argument. The act() has a loop closure, with a react closure within it. The react closure is where things happen.    

HollywoodActorRunner.groovy instantiates a couple of HollywoodActors. We see a couple of different ways of sending messages: Using the send method, using the left shift operator, or just having the string after the name of the actor.   

HollywoodActorRunner also instantiates CustomActor.groovy, which just takes a message and prints it. You need to join at the end (and put in a time limit), otherwise you will never see the messages.   

OtherRunner.groovy defines an Actor in a closure using the Actors.actor method (like HollywoodActor), but this time the loop has a condition that it will only run 3 times. You can put other conditions on loops. I try to send a fourth message, and it is ignored.     

Fortune.groovy creates an actor in a closure. This actor sends a reply to its sender. I do not think the sender property works in DynamicDispatchActor, so this is one disadvantage to DynamicDispatchActor. The first message is sent via the sendAndWait() method with a TimeOut instead of just send(). Later we use sendAndContinue() with a CountDownLatch to wait for the returned messages.  

Next we have a few files that emulate stock trading. Messages should be immutable, so in the next few examples we use the @Immutable annotation to make a class immutable. 

The first is MultiMessage001.groovy. It has an immutable Lookup class that holds a stock ticker, and an immutable Buy class that holds a ticker and a quantity. Then we create an Actor called trader with "loop" and "react" closures. The "react" closure can take messages of type Buy, Lookup as well as an else at the end to catch anything else. Ultimately we just print a random number for Lookup and a String for Buy.   

I did not like the "if instanceof" construction, so I made a copy in MultiMessage004.groovy that uses the "begin" closure with "when" closures for each type of message.    

Next in MultiMessage002.groovy instead of defining the trader Actor with the Actors.actor closure, we make a class that extends DynamicDispatchActor. For some reason using the sender property in a DynamicDispatchActor works here. I could not get it to work in my Groovy file size example in chapter 8. In MultiMessage003.groovy, the DynamicDispatchActor is defined by sending a class to the constructor of DynamicDispatchActor. In the book, he sends a closure to the constructor. I did this in MultiMessage005.groovy. In MultiMessage003.groovy, I tried out using the "become" and "when" closures. I also put stuff into methods and classes. I guess it is not quite idiomatic Groovy.    

DataFlow001.groovy
fileSize/
FileSize.groovy





README.md
