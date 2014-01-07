As of 2014-01-06, CallablePawn is not finished.    

The two classes that actually do something are BackwardCallableRunner and ForwardCallableRunner. Here are the commands to run them:  

gradle runJava -PmainClass="info.shelfunit.concurrency.callable.ForwardCallableRunner" -Diterations=10    
gradle runJava -PmainClass="info.shelfunit.concurrency.callable.BackwardCallableRunner" -Diterations=10   

They both build a list of CallableWorker. The only difference is that one iterates forwards from 0 to the number of iterations, and the other goes backwards. I did this because CallableWorker calls thread sleep, and the number of seconds is dependent on which iteration the CallableWorker was instantiated in. I did this to see if I could get results from some of them before the rest were finished. It turns out you can.  


