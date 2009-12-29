;; http://rosettacode.org/wiki/Heapsort#Clojure

(defn- swap [a i j]
  (assoc a i (nth a j) j (nth a i)))

(defn- sift [a pred k l]
  (loop [a a x k y (inc (* 2 k))]
    (if (< (inc (* 2 x)) l)
      (let [ch (if (and (< y (dec l)) (pred (nth a y) (nth a (inc y))))
                 (inc y)
                 y)]
        (if (pred (nth a x) (nth a ch))
          (recur (swap a x ch) ch (inc (* 2 ch)))
          a))
      a)))

(defn heapsort
  ([a pred]
   (let [len (count a)]
     (reduce (fn [c term] (sift (swap c term 0) pred 0 term))
             (reduce (fn [c i] (sift c pred i len))
                     (vec a)
                     (range (dec (int (/ len 2))) -1 -1))
             (range (dec len) 0 -1))))
  ([a]
     (heapsort a <)))


(comment "
--- OCAML ORIGINAL ---

let heapsort a =

  let swap i j =
    let t = a.(i) in a.(i) <- a.(j); a.(j) <- t in

  let sift k l =
    let rec check x y =
      if 2*x+1 < l then
        let ch =
          if y < l-1 && a.(y) < a.(y+1) then y+1 else y in
        if a.(x) < a.(ch) then (swap x ch; check ch (2*ch+1)) in
    check k (2*k+1) in

  let len = Array.length a in

  for start = (len/2)-1 downto 0 do
    sift start len;
  done;

  for term = len-1 downto 1 do
    swap term 0;
    sift 0 term;
  done;;
")
