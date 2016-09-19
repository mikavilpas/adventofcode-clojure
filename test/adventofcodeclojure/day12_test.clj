(ns adventofcodeclojure.day12-test
  (:require [adventofcodeclojure.day12 :as day]
            [clojure.test :as t :refer [deftest is]]))

(deftest get-integers-test
  (is (= [1 2 3] (day/get-numbers [1 2 3])))
  (is (= [1 2 3] (day/get-numbers [1 [2] 3])))
  (is (= [1 2 3] (day/get-numbers {:a 1 :b 2 :c 3})))

  (is (= [1 2 3] (day/get-numbers {:a [1] :b [2] :c [3]}))))

(deftest map-with-red-value?-test
  (is (true? (day/map-with-red-value? {"foo" "bar", "baz" "red"})))
  (is (nil? (day/map-with-red-value? [3 3 4]))))

(deftest remove-red-maps-test
  (is (nil? (day/remove-red-maps {"foo" "red"})))
  (is (= {"foo" nil}
         (day/remove-red-maps {"foo" {"bar" "red"}}))))
