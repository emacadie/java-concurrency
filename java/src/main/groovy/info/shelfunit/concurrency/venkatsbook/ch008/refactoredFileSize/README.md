This is another set of files to get the file size of a directory tree. It is a refactoring of the Akka code in Chapter 8.    

This is a refactoring of what is in .../fileSize. I will try to just call getSender() in SizeCollector and see if it works.

So far, this has turned out to be a bust. I have not been able to determine why I cannot get the sender in SizeCollector.

-------------------------

I prefer extending the DynamicDispatchActor over DefaultActor. Some would say that DefaultActor is more idiomatic Groovy. Groovy DSLs are nice, but sometimes the indentation can get pretty crazy. You go three or four or more levels in and it looks a bit crazy. Plus it can just be hard to keep track of stuff in my opinion.    

This is pretty much the same as the file size program with Akka from chapter 8. I refactored it to use the then-latest version of Akka, and then I refactored it here with GPars. While completing this refactoring, I did some digging, and saw that there is another GPars refactoring in chapter 9.   

This version seems about as fast as the Akka version. I think that using Scala APIs in Java can wind up being more trouble than it is worth. You wind up writing a lot of inner and inline classes, and doing a lot more of the things in Java that people do not like. It is nice that Scala devs are making things available to Java developers, but sometimes I wonder if it is worth it. I just do not like Scala. It looks like Perl on the JVM. So I have to look at garbage in Java, or learn garbage in Scala.    

I did some refactoring. FileProcessorGroovy sends a message of type RequestAFileGroovy to SizeCollectorGroovy. It did not work too well, since SizeCollectorGroovy needs to add the sender of that message to a list. This is refactored in the ch009 package with FileProcessor extending DefaultActor. There, SizeCollector can get the sender without a problem. Here, it did not work. For a while, I was having FileProcessorGroovy send itself. I did not like that, since it is not immutable. Then I refactored RequestAFileGroovy to be final and contain a FileProcessorGroovy as a final variable. Then in SizeCollectorGroovy, it was able to extract it from the message.      

I also tried this by having FileProcessorGroovy extend DefaultActor. When it is a DefaultActor, SizeCollectorGroovy can get the sender without any problem. I also tried this by having FileProcessorGroovy extend SingleDispatchActor. For that I also had to use the refactored RequestAFileGroovy. For some reason, sender only works with DefaultActor.     

I thought that using StaticDispatchActor would be faster than DynamicDispatchActor, but DDA was faster than SDA. Both were faster than DA.   




