;; http://rosettacode.org/wiki/Testing_a_Function#Clojure


(defn palindrome? [s]
  (= s (apply str (reverse s))))


(use 'clojure.test)

(deftest test-palindrome?
  (is (= true (palindrome? "amanaplanacanalpanama")))
  (is (= false (palindrome? "Test 1, 2, 3"))))

(run-tests)
