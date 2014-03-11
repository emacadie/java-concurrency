This is another set of files to get the file size of a directory tree. It is a refactoring of the Akka code in Chapter 8.    

I prefer extending the DynamicDispatchActor over DefaultActor. Some would say that DefaultActor is more idiomatic Groovy. Groovy DSLs are nice, but sometimes the indentation can get pretty crazy. You go three or four or more levels in and it looks a bit crazy. Plus it can just be hard to keep track of stuff in my opinion.    

This is pretty much the same as the file size program with Akka from chapter 8. I refactored it to use the then-latest version of Akka, and then I refactored it here with GPars. While completing this refactoring, I did some digging, and saw that there is another GPars refactoring in chapter 9.   

This version seems about as fast as the Akka version. I think that using Scala APIs in Java can wind up being more trouble than it is worth. You wind up writing a lot of inner and inline classes, and doing a lot more of the things in Java that people do not like. It is nice that Scala devs are making things available to Java developers, but sometimes I wonder if it is worth it. I just do not like Scala. It looks like Perl on the JVM. So I have to look at garbage in Java, or learn garbage in Scala.    





