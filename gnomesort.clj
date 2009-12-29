;; http://rosettacode.org/wiki/Gnome_sort#Clojure


(defn gnomesort
  ([c] (gnomesort c <))
  ([c pred]
     (if (empty? c)
       c
       (loop [x [] y c]
         (cond (empty? y) x
               (empty? x) (recur (take 1 y) (rest y))
               true (let [y1 (first y) ys (rest y) zx (last x)]
                      (if (pred y1 zx)
                        (recur (butlast x) (concat (list y1 zx) ys))
                        (recur (concat x (list y1)) ys))))))))


(comment "
gnomeSort [] = []
gnomeSort xs = gs [] xs 
   where
      gs xs []     = xs
      gs [] (y:ys) = gs [y] ys
      gs xs (y:ys) | y < zx    = gs (init xs) (y:zx:ys)
                   | otherwise = gs (xs++[y]) ys 
            where zx = last xs")
