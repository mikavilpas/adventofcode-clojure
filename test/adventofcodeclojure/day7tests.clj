(ns adventofcodeclojure.day7tests
  (:require [adventofcodeclojure
             [day7 :as day]
             [day7-parsing :as parsing]
             [day7-types :as types]]
            [clojure.test :as t :refer [deftest is]]))

(deftest read-wire-or-value-test
  (is (= 3
         (parsing/read-wire-or-value "3")))
  (is (= "wireName"
         (parsing/read-wire-or-value "wireName"))))

(deftest parsing
  (is (= (types/->LiteralValue 3 "wireA")
         (parsing/parse-instruction "3 -> wireA")))
  (is (= (types/->LiteralValue "wire" "anotherWire")
         (parsing/parse-instruction "wire -> anotherWire")))

  (is (= (types/->And "wire" 3 "anotherWire")
         (parsing/parse-instruction "wire AND 3 -> anotherWire")))
  (is (= (types/->And "a" "b" "c")
         (parsing/parse-instruction "a AND b -> c")))

  (is (= (types/->Or "wire" 3 "anotherWire")
         (parsing/parse-instruction "wire OR 3 -> anotherWire")))
  (is (= (types/->Or "a" "b" "c")
         (parsing/parse-instruction "a OR b -> c")))

  (is (= (types/->Not 3 "anotherWire")
         (parsing/parse-instruction "NOT 3 -> anotherWire")))
  (is (= (types/->Not "a" "b")
         (parsing/parse-instruction "NOT a -> b")))

  (is (= (types/->LeftShift "a" 3 "anotherWire")
         (parsing/parse-instruction "a LSHIFT 3 -> anotherWire")))
  (is (= (types/->LeftShift "a" "b" "targetWire")
         (parsing/parse-instruction "a LSHIFT b -> targetWire")))

  (is (= (types/->RightShift "a" 3 "anotherWire")
         (parsing/parse-instruction "a RSHIFT 3 -> anotherWire")))
  (is (= (types/->RightShift "a" "b" "targetWire")
         (parsing/parse-instruction "a RSHIFT b -> targetWire"))))

(deftest parse-all-input
  (is (= 339
         (count (day/parse-input)))))
