Frankly, this is where I decided I was done. To use the Scheduler, you have to call something like this:    

schedule(scala.concurrent.duration.FiniteDuration initialDelay,
                     scala.concurrent.duration.FiniteDuration interval,
                     ActorRef receiver,
                     java.lang.Object message,
                     scala.concurrent.ExecutionContext executor,
                     ActorRef sender)     

There are a lot of "scala.concurrent" classes in there. So to use Akka, you really have to use Scala. I have no interest in that.    

EnergySourceImpl.java    
EnergySource.java    
UseEnergySource.java    
