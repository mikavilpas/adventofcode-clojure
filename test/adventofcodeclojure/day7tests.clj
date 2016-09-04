(ns adventofcodeclojure.day7tests
  (:require [adventofcodeclojure
             [day7 :as d]
             [day7-parsing :as parsing]
             [day7-types :as types]]
            [clojure.test :as t :refer [deftest is]]))

(deftest read-wire-or-value-test
  (is (= 3
         (parsing/read-wire-or-value "3")))
  (is (= "wireName"
         (parsing/read-wire-or-value "wireName"))))

(deftest parsing
  (is (= {"wireA" (types/->LiteralValue 3)}
         (parsing/parse-instruction "3 -> wireA")))
  (is (= {"anotherWire" (types/->LiteralValue "wire")}
         (parsing/parse-instruction "wire -> anotherWire")))

  (is (= {"anotherWire" (types/->And "wire" 3)}
         (parsing/parse-instruction "wire AND 3 -> anotherWire")))
  (is (= {"c" (types/->And "a" "b")}
         (parsing/parse-instruction "a AND b -> c")))

  (is (= {"anotherWire" (types/->Or "wire" 3)}
         (parsing/parse-instruction "wire OR 3 -> anotherWire")))
  (is (= {"c" (types/->Or "a" "b")}
         (parsing/parse-instruction "a OR b -> c")))

  (is (= {"anotherWire" (types/->Not 3)}
         (parsing/parse-instruction "NOT 3 -> anotherWire")))
  (is (= {"b" (types/->Not "a")}
         (parsing/parse-instruction "NOT a -> b")))

  (is (= {"anotherWire" (types/->LeftShift "a" 3)}
         (parsing/parse-instruction "a LSHIFT 3 -> anotherWire")))
  (is (= {"targetWire" (types/->LeftShift "a" "b")}
         (parsing/parse-instruction "a LSHIFT b -> targetWire")))

  (is (= {"anotherWire" (types/->RightShift "a" 3)}
         (parsing/parse-instruction "a RSHIFT 3 -> anotherWire")))
  (is (= {"targetWire" (types/->RightShift "a" "b")}
         (parsing/parse-instruction "a RSHIFT b -> targetWire"))))

(deftest parse-all-input
  (is (= 339
         (count (d/parse-input)))))

(deftest get-wire-value-simple
  (is (= 3 (d/get-wire-value "a" {"a" (types/->LiteralValue 3)})))
  (is (= 1 (d/get-wire-value "a" {"a" (types/->And 3 1)})))
  (is (= 3 (d/get-wire-value "a" {"a" (types/->Or 2 1)})))
  (is (= 0 (d/get-wire-value "a" {"a" (types/->Not -1)})))
  (is (= 2 (d/get-wire-value "a" {"a" (types/->LeftShift 1 1)})))
  (is (= 1 (d/get-wire-value "a" {"a" (types/->RightShift 2 1)}))))

(deftest get-wire-value-reference
  (is (= 100 (d/get-wire-value "b" {"b" (types/->LiteralValue "a")
                                    "a" (types/->LiteralValue 100)}))))
