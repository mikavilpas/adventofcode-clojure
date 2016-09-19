(ns adventofcodeclojure.day12-test
  (:require [adventofcodeclojure.day12 :as day]
            [clojure.test :as t :refer [deftest is]]))

(deftest get-integers-test
  (is (= [1 2 3] (day/get-numbers [1 2 3])))
  (is (= [1 2 3] (day/get-numbers [1 [2] 3])))
  (is (= [1 2 3] (day/get-numbers {:a 1 :b 2 :c 3})))

  (is (= [1 2 3] (day/get-numbers {:a [1] :b [2] :c [3]}))))
