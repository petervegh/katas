(ns FizzBuzz
    (use clojure.test))

(defn fizz-buzz
      [input]
      (doall (map #(cond
                    (= (mod % 15) 0) "FizzBuzz"
                    (= (mod % 3) 0)  "Fizz"
                    (= (mod % 5) 0)  "Buzz"
                    :else %) input)))

(is (= (fizz-buzz []) []))
(is (= (fizz-buzz [1]) [1]))
(is (= (fizz-buzz [1 2 3]) [1 2 "Fizz"]))
(is (= (fizz-buzz [1 2 3 4 5]) [1 2 "Fizz" 4 "Buzz"]))
(is (= (fizz-buzz [1 2 3 4 5 6 15]) [1 2 "Fizz" 4 "Buzz" "Fizz" "FizzBuzz"]))