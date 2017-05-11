(ns HackerRankSolutions
  (:use clojure.pprint
        clojure.walk))

(defn test01[delim lst]
  (doall (map #(print % " ") (remove #( >= delim %) lst))))

(test01 3 [1 2 3 4 5])


; For a given list with  integers, return a new list removing the elements at odd positions.
; The input and output portions will be handled automatically.
; You need to write a function with the recommended method signature.
;(defn test02 [lst]
;  (->> lst
;       (map #(vector %1 %2) lst (range))
;       ;(filter #(= (mod 0 (second %))))
;       ))
(defn removeOddElements [lst]
  (->> lst
       (map #(vector %2 %1) (range))
       (remove #(= 0 (mod (second %) 2)))
       (map #(first %))))

(println (removeOddElements [4 3 5 7 8 10 1 9]))


; You are given a list. Return the sum of odd elements from the given list.
; The input and output portions will be handled automatically.
; You need to write a function with the recommended method signature.
(defn sumOddElements [lst]
  (->> lst
       (filter #(not (= 0 (mod % 2))))
       (reduce +)))

(println (sumOddElements [3 2 4 6 5 7 8  0 1]))

; Count the number of elements in an array without using count, size or length operators (or their equivalents).
; The input and output portions will be handled automatically by the grader.
; You only need to write a function with the recommended method signature.
(defn countElements [lst]
  (->> lst
       (map #(vector %1 %2) (repeat 1))
       (map #(first %))
       (reduce +)))

(println (countElements [3 2 4 6 5 7 8  0 1]))

; Update the values of a list with their absolute values.
; The input and output portions will be handled automatically during grading.
; You only need to write a function with the recommended method signature.
(defn absList [lst]
  (map #(Math/abs %) lst))

(println (absList [-1 2 4 -5 8 9 -32]))

; The series expansion of  is given by:
; 1 + x + (x^2)/2! + (x^3)/3! + ... + (x^n)/n!
; Evaluate  for given values of  by using the above expansion for the first  terms.
(defn factorial [n]
  (loop [cnt n acc 1]
    (if (not (zero? cnt))
      (recur (dec cnt) (* acc cnt))
      acc)))

(defn pow [x n]
  (reduce * (repeat n x)))

(defn calcE [x]
  (reduce +
          (map #(/ (pow x %1) (factorial %1)) (range 0 10))))

(defn calculate [num lst]
  (map #(calcE %) lst))

(println (calculate 4 [20.0 5.0 0.5 -0.5]))
;(doseq [line (line-seq (java.io.BufferedReader. *in*))]
;(println line))

; You are given a list of  elements. Reverse the list without using the reverse function.
; The input and output portions will be handled automatically.
; You need to write a function with the recommended method signature.
(defn reverseList [lst]
  (reduce conj () lst))

(println (reverseList [1 2 3 4 5]))

; The Fibonacci sequence begins with  and . These are the first and second terms, respectively.
; After this, every element is the sum of the preceding elements:
; Fibonacci(n) = Fibonacci(n-1) + Fibonacci(n-2)

(println (conj [1 2 3] 5))

(defn fibonacci [n]
  (loop [lst [0 1]]
    (if (< (count lst) n)
      (recur (conj lst (+ (nth lst (dec (count lst))) (nth lst (- (count lst) 2) ))))
      lst)))

(println (fibonacci 6))


; String-o-permute

(defn swap [str]
  (loop [[a b & rest] (clojure.string/split str #"")
         result []]
    (if (empty? rest)
      (conj result b a)
      (recur rest (conj result b a)))))

(defn run-swap [strList]
  (doall (map #(println (clojure.string/join (swap %))) strList)))

(defn read-lines [num]
  (loop [n num lst []]
    (if (zero? n)
      lst
      (recur (dec n) (conj lst (read-line))))))

;(def input (read-lines (Integer/parseInt (read-line))))
;(run-swap input)

;(defn matrix-builder [n m]
;  (loop [row n matrix (transient [])]
;    (if (> row n)
;      matrix
;      (recur (inc row) (conj matrix (repeat m 0))))))

;(matrix-builder 5 5)

;(prewalk #((inc %) %) matrix)
;(pprint matrix)

(let [the-array (make-array Long/TYPE 2 3) ]
  (dotimes [nn 6]
    (let [ii    (quot nn 3)
          jj    (rem  nn 3) ]
      (aset the-array ii jj nn)
      ))
  (pprint the-array)
  )

;(pprint matrix)

(defn randomize-matrix [M n]
  (dotimes [nn n]
    (let [ii (rand 9)
          jj (rand 9)]
      (aset-int M ii jj 1))))

(def matrix (make-array Long/TYPE 10 10))
(randomize-matrix matrix 30)
(pprint matrix)



; Convex Hull with Graham-scan
(def points [[1 1] [2 5] [3 3] [5 3] [3 2] [2 2]])
(defn find-min
  "Finds the minimum x or y coordinate point in points"
  [points order]
  (apply min-key order points))

(defn find-P
  ""
  [points]
  (find-min (filter #(= (second %) (second (find-min points second))) points) first))

;- (x - x1) / (y - y1) (where (x1, y1)

(def P (find-P points))
(defn cosine
  [x]
  (+ (* (first P) (first x)) (* (second P) (second x))))

(defn sort-points [points]
  (sort-by cosine points))

(println (sort-points points))

(defn ccw [p1 p2 p3]
  "Three points are a counter-clockwise turn if ccw > 0, clockwise if ccw < 0,
   and collinear if ccw = 0 because ccw is a determinant that gives twice the signed
   area of the triangle formed by p1, p2 and p3."
  (- (* (- (first p2) (first p1)) (- (second p3) (second p1))))
     (* (- (second p2) (second p1)) (- (first p3) (first p1))))

(println (ccw [1 1] [2 3] [3 2]))

(println (find-P points))
