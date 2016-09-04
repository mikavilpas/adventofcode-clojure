(ns adventofcodeclojure.day7-types)

;; value can be a number or a target-wire
(defrecord LiteralValue [value target-wire])
(defrecord And [a b target-wire])
(defrecord Or [a b target-wire])
(defrecord Not [value target-wire])
(defrecord LeftShift [a b target-wire])
(defrecord RightShift [a b target-wire])
