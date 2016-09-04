(ns adventofcodeclojure.day7tests
  (:require [adventofcodeclojure.day7 :as day]
            [clojure.test :as t :refer [deftest is]]))

(deftest parsing
  (is (= (day/->LiteralValue 3 "wireA")
         (day/parse-instruction "3 -> wireA")))

  (is (= (day/->LiteralValue "wire" "anotherWire")
         (day/parse-instruction "wire -> anotherWire"))))

(deftest read-wire-or-value-test
  (is (= 3
         (day/read-wire-or-value "3")))
  (is (= "wireName"
         (day/read-wire-or-value "wireName"))))
