HollywoodActor.groovy extends DefaultActor. It has a constructor that takes an argument. The act() has a loop closure, with a react closure within it. The react closure is where things happen.    

HollywoodActorRunner.groovy instantiates a couple of HollywoodActors. We see a couple of different ways of sending messages: Using the send method, using the left shift operator, or just having the string after the name of the actor.   

HollywoodActorRunner also instantiates CustomActor.groovy, which just takes a message and prints it. You need to join at the end (and put in a time limit), otherwise you will never see the messages.   

OtherRunner.groovy defines an Actor in a closure using the Actors.actor method (like HollywoodActor), but this time the loop has a condition that it will only run 3 times. You can put other conditions on loops. I try to send a fourth message, and it is ignored.     

Fortune.groovy creates an actor in a closure. This actor sends a reply to its sender. I do not think the sender property works in DynamicDispatchActor, so this is one disadvantage to DynamicDispatchActor. The first message is sent via the sendAndWait() method with a TimeOut instead of just send(). Later we use sendAndContinue() with a CountDownLatch to wait for the returned messages.  

Next we have a few files that emulate stock trading. Messages should be immutable, so in the next few examples we use the @Immutable annotation to make a class immutable. 

The first is MultiMessage001.groovy. It has an immutable Lookup class that holds a stock ticker, and an immutable Buy class that holds a ticker and a quantity. Then we create an Actor called trader with "loop" and "react" closures. The "react" closure can take messages of type Buy, Lookup as well as an else at the end to catch anything else. Ultimately we just print a random number for Lookup and a String for Buy.   

I did not like the "if instanceof" construction, so I made a copy in MultiMessage004.groovy that uses the "begin" closure with "when" closures for each type of message.    

Next in MultiMessage002.groovy instead of defining the trader Actor with the Actors.actor closure, we make a class that extends DynamicDispatchActor. For some reason using the sender property in a DynamicDispatchActor works here. I could not get it to work in my Groovy file size example in chapter 8. In MultiMessage003.groovy, the DynamicDispatchActor is defined by sending a class to the constructor of DynamicDispatchActor. In the book, he sends a closure to the constructor. I did this in MultiMessage005.groovy. In MultiMessage003.groovy, I tried out using the "become" and "when" closures. I also put stuff into methods and classes. I guess it is not quite idiomatic Groovy. I also added some extra messages.      

Next we go into the fileSize/ directory.    

Next is DataFlow001.groovy which introduces Dataflow Variables. We will fetch the front pages of two web sites, and print the sizes of them to the screen. We will use Dataflow Variables for this. Dataflow variables can only be set once, but read many times. The first read will be blocked until the variable is set. The variables are declared, and then set in a method. The method to set them is called by the static method Dataflow.task(). The calls to task are run concurrently. Then the results are printed to the screen. Dataflow.task() returns a Promise, but it looks like the Promise is not used in this program.   

FileSize.groovy is next. It is another example of the infamous file tree size program that we have been making here again and again. We create two DataflowQueues: one to hold the sizes of files (called "sizes"), and one to keep a count of pending files and directories (called "pendingFiles"). We also define a DefaultPGroup for the threads. They will be accessed from multiple methods. These should be defined at the top of the class.    

We start in a method to get the total size called findTotalFileSize. We set a few variables. We call findSize by sending it to the method DefaultPGroup.task(). Like Dataflow.task(), this will run code in a thread and return a Promise. But in this program the Promise is never captured.  

We update totalSize by calling sizes.val, or sizes.getVal(). This will act like java.util.Stack.pop(): It will return and remove the top value in the DataflowQueue. We keep continuing in findTotalFileSize until the first two values pulled from pendingSizes add up to 0.    

In findSize(), if the file sent to the method is a file, we just add its size to sizes. If it is a directory, we cycle through the child files, and add up their total to sizes. If one of them is a directory, we add -1 to the pendingFiles queue, and use DefaultPGroup.task() to call recursively call findFiles on the directory. Then at the end we add 1 to pendingFiles.    

The author himself explains it best: We push 1 to pendingFiles when we begin a task, and -1 when we are done with a task.   

It seems to take longer that the Actor-based solutions.    

I am not too sure I get Dataflow Variables. "They can only be set once, so they are thread-safe!" That sounds like regular old immutable variables.   



README.md
