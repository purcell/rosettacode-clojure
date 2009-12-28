;; http://rosettacode.org/wiki/Permutation_Sort#Clojure

(use '[clojure.contrib.combinatorics :only (permutations)])

(defn permutation-sort [s]
  (first (filter (partial apply <=) (permutations s))))
