;  from Programming Concurrency on the JVM by Venkat Subramaniam
; should there be a name space?
(def balance (ref 0))
(println "Balance is" @balance)
(dosync
 (ref-set balance 100))
(println "Balance is now" @balance)
