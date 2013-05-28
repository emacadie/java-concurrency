;;  from Programming Concurrency on the JVM by Venkat Subramaniam
;; I may need to set a ref for balance
(defn deposit [balance amount]
  (dosync
   (println "Ready to deposit..." amount)
   (let [current-balance @balance]
     (println "simulating delay in deposit...")
     (. Thread sleep 2000)
     (alter balance + amount)
     (println "done with deposit of " amount))))

(defn withdraw [balance amount]
  (dosync
   (println "Ready to withdraw..." amount)
   (let [current-balance @balance]
     (println "simulating delay in withdraw...")
     (. Thread sleep 2000)
     (alter balance - amount)
     (println "done with withdraw of " amount))))

(def balance1 (ref 100))
(println "balance1 is " @balance1)
(future (deposit balance1 20))
(future (withdraw balance1 10))

(. Thread sleep 10000)

(println "balance1 is now " @balance1)

; EOF