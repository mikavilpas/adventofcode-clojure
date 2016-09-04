(ns adventofcodeclojure.day7-types)

;; value can be a number or a target-wire
(defrecord LiteralValue [value])
(defrecord And [a b])
(defrecord Or [a b])
(defrecord Not [value])
(defrecord LeftShift [a b])
(defrecord RightShift [a b])

(apply hash-map [1 2 3 4])
