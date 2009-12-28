;; http://rosettacode.org/wiki/HTTPS_Request#Clojure

(use '[clojure.contrib.duck-streams :only (slurp*)])
(slurp* "https://sourceforge.net")
