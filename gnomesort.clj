;; http://rosettacode.org/wiki/Gnome_sort#Clojure


(defn gnomesort
  ([c] (gnomesort c <))
  ([c pred]
     (loop [x [] [y1 & ys :as y] (seq c)]
       (cond (empty? y) x
             (empty? x) (recur (list y1) ys)
             true (let [zx (last x)]
                    (if (pred y1 zx)
                      (recur (butlast x) (concat (list y1 zx) ys))
                      (recur (concat x (list y1)) ys)))))))



(comment "
gnomeSort [] = []
gnomeSort xs = gs [] xs
   where
      gs xs []     = xs
      gs [] (y:ys) = gs [y] ys
      gs xs (y:ys) | y < zx    = gs (init xs) (y:zx:ys)
                   | otherwise = gs (xs++[y]) ys
            where zx = last xs")
