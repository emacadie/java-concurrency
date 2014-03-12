Planet.java is an immutable object, based on the one found at http://www.javapractices.com/topic/TopicAction.do;?Id=29    
  
The GPars docs are unclear as to whether or not the messages must be immutable.  

FortuneTellerGPars.java is an actor class that extends DynamicDispatchActor. In both Java and Groovy, extending DefaultActor seems to result in code with lots of nested loops, while extending DynamicDispatchActor looks a lot cleaner. It can take messages of type Double, Planet, Object or String.    

UseFortuneTellerGPars.java instantiates a couple of FortuneTellerGPars objects, and sends messages in a method for Double and String messages, and a method for Planet messages.    

There is also some use of GPars Promises, which are similar to JDK Futures objects. You can send a message with the call DynamicDispatchActor.sendAndPromise( X ), which returns a Promise. You can get the result by calling Promise.get(), similar to Future.get(). You can see if the Promise is finished by calling Promise.isBound(), similar to Future.isDone().    


