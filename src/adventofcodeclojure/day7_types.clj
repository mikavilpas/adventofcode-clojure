(ns adventofcodeclojure.day7-types)

;; value can be a number or a target-wire
(defrecord LiteralValue [value])
(defrecord And [a b])
(defrecord Or [a b])
(defrecord Not [value])
(defrecord LeftShift [a count])
(defrecord RightShift [a count])
