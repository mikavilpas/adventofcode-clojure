(ns adventofcodeclojure.day8-test
  (:require [adventofcodeclojure.day8 :as day]
            [clojure.test :as t :refer [deftest is]]
            [adventofcodeclojure.input-reader :as input-reader]
            [clojure.string :as string]))

;; to read these tests you need to open Day8TestInput.txt
(defn- get-test-input []
  (->> (input-reader/read-input-lines "Day8TestInput.txt")
       (map string/trim)))

(deftest string-length-in-code-test
  (is (= [2 5 10 6 6]
         (mapv day/string-length-in-code (get-test-input)))))

(deftest string-length-in-computer-memory-test
  (is (= [0 3 7 1 1]
         (mapv day/string-length-in-computer-memory (get-test-input)))))

(deftest solve-code-length-minus-computer-memory-length-test
  (is (= 17
         (day/solve-code-length-minus-computer-memory-length (get-test-input)))))
