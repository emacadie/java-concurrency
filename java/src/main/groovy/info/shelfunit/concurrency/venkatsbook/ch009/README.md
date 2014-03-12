HollywoodActor.groovy has a constructor that takes an argument. The act() has a loop closure, with a react closure within it. The react closure is where things happen.    

HollywoodActorRunner.groovy instantiates a couple of HollywoodActors. We see a couple of different ways of sending messages: Using the send method, using the left shift operator, or just having the string after the name of the actor.   

HollywoodActorRunner also instantiates CustomActor.groovy, which just takes a message and prints it. You need to join at the end (and put in a time limit), otherwise you will never see the messages.   

OtherRunner.groovy defines an actor in a closure using the Actors.actor method (like HollywoodActor), but this time the loop has a condition that it will only run 3 times. You can put other conditions on loops. I try to send a fourth message, and it is ignored.     

DataFlow001.groovy
fileSize/
FileSize.groovy
Fortune.groovy
MultiMessage001.groovy
MultiMessage002.groovy
MultiMessage003.groovy

README.md
