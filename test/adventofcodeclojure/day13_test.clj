(ns adventofcodeclojure.day13-test
  (:require [adventofcodeclojure.day13 :as day]
            [clojure.test :as t :refer [deftest is]]))

(deftest seating-orders-test
  (is (= '((3 1 2 3)
           (2 1 3 2)
           (3 2 1 3)
           (1 2 3 1)
           (2 3 1 2)
           (1 3 2 1))
         (day/seating-orders [1 2 3]))))

(deftest happiness-test
  (is (= 63
         (day/count-happiness {["Aaron" "Bertha"] 1
                               ["Bertha" "Aaron"] 2
                               ["Bertha" "Cecilia"] 4
                               ["Cecilia" "Bertha"] 8
                               ["Cecilia" "Aaron"] 16
                               ["Aaron" "Cecilia"] 32}
                              ["Aaron" "Bertha" "Cecilia" "Aaron"]))))
