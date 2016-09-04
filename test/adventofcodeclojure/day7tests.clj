(ns adventofcodeclojure.day7tests
  (:require [adventofcodeclojure.day7 :as day]
            [clojure.test :as t :refer [deftest is]]))

(deftest read-wire-or-value-test
  (is (= 3
         (day/read-wire-or-value "3")))
  (is (= "wireName"
         (day/read-wire-or-value "wireName"))))

(deftest parsing
  (is (= (day/->LiteralValue 3 "wireA")
         (day/parse-instruction "3 -> wireA")))
  (is (= (day/->LiteralValue "wire" "anotherWire")
         (day/parse-instruction "wire -> anotherWire")))

  (is (= (day/->And "wire" 3 "anotherWire")
         (day/parse-instruction "wire AND 3 -> anotherWire")))
  (is (= (day/->And "a" "b" "c")
         (day/parse-instruction "a AND b -> c")))

  (is (= (day/->Or "wire" 3 "anotherWire")
         (day/parse-instruction "wire OR 3 -> anotherWire")))
  (is (= (day/->Or "a" "b" "c")
         (day/parse-instruction "a OR b -> c")))

  (is (= (day/->Not 3 "anotherWire")
         (day/parse-instruction "NOT 3 -> anotherWire")))
  (is (= (day/->Not "a" "b")
         (day/parse-instruction "NOT a -> b")))

  (is (= (day/->LeftShift "a" 3 "anotherWire")
         (day/parse-instruction "a LSHIFT 3 -> anotherWire")))
  (is (= (day/->LeftShift "a" "b" "targetWire")
         (day/parse-instruction "a LSHIFT b -> targetWire")))

  (is (= (day/->RightShift "a" 3 "anotherWire")
         (day/parse-instruction "a RSHIFT 3 -> anotherWire")))
  (is (= (day/->RightShift "a" "b" "targetWire")
         (day/parse-instruction "a RSHIFT b -> targetWire"))))
