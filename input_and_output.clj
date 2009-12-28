;; http://rosettacode.org/wiki/File/Input_and_Output#Clojure

(use '[clojure.contrib.duck-streams :only (copy)])

(copy (java.io.File. "input.txt") (java.io.File. "output.txt"))
