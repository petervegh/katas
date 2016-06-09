(ns BinaryChop
  (use clojure.test))

(defn binaryChop [target arr]
  (def size (count arr))
  (loop [left 0 right (- size 1)]
    (def mid (int (/ (+ left right) 2)))
    (cond
      (= size 0) -1
      (= target (nth arr mid)) mid
      (= left right) -1
      (> left right) -1
      (< target (nth arr mid)) (recur left (- mid 1))
      (> target (nth arr mid)) (recur (+ mid 1) right))))

(is (= -1 (binaryChop 3 [])))
(is (= -1 (binaryChop 3 [1])))
(is (=  0 (binaryChop 1 [1])))

(is (=  0 (binaryChop 1 [1 3 5])))
(is (=  1 (binaryChop 3 [1 3 5])))
(is (=  2 (binaryChop 5 [1 3 5])))
(is (=  -1 (binaryChop 0 [1 3 5])))
(is (=  -1 (binaryChop 2 [1 3 5])))
(is (=  -1 (binaryChop 4 [1 3 5])))
(is (=  -1 (binaryChop 6 [1 3 5])))

(is (=  0 (binaryChop 1 [1 3 5 7])))
(is (=  1 (binaryChop 3 [1 3 5 7])))
(is (=  2 (binaryChop 5 [1 3 5 7])))
(is (=  3 (binaryChop 7 [1 3 5 7])))
(is (=  -1 (binaryChop 0 [1 3 5 7])))
(is (=  -1 (binaryChop 2 [1 3 5 7])))
(is (=  -1 (binaryChop 4 [1 3 5 7])))
(is (=  -1 (binaryChop 6 [1 3 5 7])))
(is (=  -1 (binaryChop 8 [1 3 5 7])))
