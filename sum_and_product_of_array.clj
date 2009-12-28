;; http://rosettacode.org/wiki/Sum_and_product_of_array#Clojure


(defn sum [vals] (reduce + vals))

(defn product [vals] (reduce * vals))
