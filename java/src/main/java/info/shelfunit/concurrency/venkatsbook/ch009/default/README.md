MyActorRunner.java calls MyActor and sends some messages.   
MyActor.java extends DefaultActor. That is why this is the "default" directory. It is based on one of the classes in the GPars sample tests. That Java class has a lot of nested loops, and someone on the list said that they messages have to come in a specific order. I changed it so that the Actor can handle messages in any order. 

I also added a couple of private variables and some getters and setters. I did this because I thought this might be a way to test Actor classes. You send a message, which updates a variable, and then you get it to see if it passed. I guess this would work, but it does not seem very clean. At least, I feel like there should be a better way.    

Another idea is you could make a class that is instantiated when an Actor receives a message, and then discarded when it is done. Then you could test that class.   

DefaultActors are "stateful" actors, and DynamicDispatchActors are "stateless" actors. What this means is that a DefaultActor will remember what messages it has been sent. One example is that you may have an Actor that decrypts messages, but you may only want it to do so after getting the keys. I guess that for a stateless Actor you could still do that, but you would need to keep a variable in the Actor, like a boolean, and check it every time you get a message.     

Someone on the list said that the "stateless" Actors can still hold variables. I will have to look more at this.      




