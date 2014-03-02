Primes.java is an actor that calculates prime numbers. It is called by UsePrimes.java. UsePrimes takes two arguments. The first is an upper limit, under which the program will find all primes. The second is the number of parts to divide the limit into. The limit is divided, and a Primes is instantiated and added to a list of Futures. In each Primes, a call is made to static methods in PrimeFinder.java. It seems that static methods is a cheat to get thread safety. There is also some calls to info.shelfunit.util.ClassUtil to ensure that the message sent to Primes implements java.util.List. The input is made into an immutable list in UsePrimes by a call to Collections.unmodifiableList.      

UsePrimes002.java calls Primes.java, but never seems to finish.   

PrimesWithFinder.java puts the static methods in the same class as the actor methods and is called from UsePrimesWithFinder.java.    

PrimesWithGuava.java uses the Guava library to make the args to the actors immutable. It also includes the methods that were static in PrimesWithFinder. It is called from UsePrimesWithGuava.java.    

The three classes do not give the same answer for the same arguments, so I guess I did something wrong. Perhaps because there is a different value in the calls to Pattern.ask for the timeout argument. I will make it a command line arg.      

That did not help. Perhaps I can finish this some other time.    

All these give 664579    
gradle runjava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch008.primes.UsePrimes -PmainArgs="10000000 100 30"     
gradle runjava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch008.primes.UsePrimesWithGuava -PmainArgs="10000000 15 30"    
gradle runjava -PmainClass=info.shelfunit.concurrency.venkatsbook.ch008.primes.UsePrimesWithFinder -PmainArgs="10000000 15 30"     

More chunks gives a lower number. At some point I could refactor it to call scala.concurrent.Future.isCompleted in the loop. Or do it with GPars.     

There are values here: http://primes.utm.edu/howmany.shtml   







