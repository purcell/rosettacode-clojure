;; http://rosettacode.org/wiki/Non_Continuous_Subsequences#Clojure


(use '[clojure.contrib.combinatorics :only (subsets)])

(defn of-min-length [min-length]
  (fn [s] (>= (count s) min-length)))


(defn non-continuous-subsequences [s]
  (let [enumerated  (map vector (iterate inc 0) s)
        continuous? (fn [c] (= (take (count c) (iterate inc (ffirst c)))
                               (map first c)))]
    (->> enumerated
         subsets
         (filter (complement continuous?))
         (map (partial map second)))))

(filter (of-min-length 2) (non-continuous-subsequences [:a :b :c :d]))


;; Naive subseq test

(defn of-min-length [min-length]
  (fn [s] (>= (count s) min-length)))

(defn runs [c l]
  (map (partial take l) (take-while not-empty (iterate rest c))))

(defn is-subseq? [c sub]
  (some identity (map = (runs c (count sub)) (repeat sub))))

(defn non-continuous-subsequences-2 [s]
  (filter (complement (partial is-subseq? s)) (subsets s)))


(filter (of-min-length 2) (non-continuous-subsequences-2 [:a :b :c :d]))

(is-subseq? [:a :b :c :d] [:a :b])
(is-subseq? [:a :b :c :d] [:b :d])


;; Translation of Python -- broken

(defn ncsub
  ([seq]
     (ncsub seq 0))
  ([seq s]
     (if (not-empty seq)
       (let [x (butlast seq)
             xs (rest seq)
             p2 (int (/ s 2))
             p1 (if (zero? p2) 1 0)]
         (lazy-seq
          (concat (for [ys (ncsub xs (+ s p1))]
                    (concat x ys))
                  (ncsub xs (+ s p2)))))
       (if (>= s 3) [[]] []))))
