;;  from Programming Concurrency on the JVM by Venkat Subramaniam
(defn add-item [wishlist item]
  (dosync (alter wishlist conj item)))

(def family-wishlist (ref '("iPad")))
(def original-wishlist @family-wishlist)

(println "Original wishlist is " original-wishlist)

(future (add-item family-wishlist "MPP"))
(future (add-item family-wishlist "Bike"))

(. Thread sleep 10000)

(println "Original wishlist is " original-wishlist)
(println "Udated list is " @family-wishlist)



; EOF