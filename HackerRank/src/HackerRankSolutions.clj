(ns HackerRankSolutions)

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

; For a given integer K, print the first K rows of Pascal's Triangle.
; Print each row with each value separated by a single space.
; The value at the Nth row and Rth column of the triangle is equal to n!/(r! * (n-r)!) where indexing starts from 0.
; These values are the binomial coefficients.


(defn pascalTriangle [n]
  (loop [cnt 1]
    (if (= cnt n)
      (println "last row")
      (doseq [r (range 1 cnt)]
        (println r))
      (recur (inc cnt)))))

(pascalTriangle 5)