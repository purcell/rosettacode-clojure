;; http://rosettacode.org/wiki/Long_Multiplication#Clojure

;; INCOMPLETE

(defn digits [n]
  (if (> n 9)
    (conj (digits (int (/ n 10))) (mod n 10))
    [n]))

(defn mult_table [xs ys]
  (for [y ys]
    (for [x xs] (* x y))))

(defn enumerate [s]
  (map vector (iterate inc 0) s))

(defn polymul [xs ys]
  (apply map (fn [& vs] (reduce + (filter identity vs)))
         (map (fn [i zs] (concat (repeat i 0) zs))
              (enumerate (mult_table xs ys)))))

(defn longmult [x y]
  (reduce (fn [result v]
            (+ v (* 10 result)))
          0
          (polymul (digits x) (digits y))))

(println (format "%s" (longmult 18446744073709551616
                                18446744073709551616)))
