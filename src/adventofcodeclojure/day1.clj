(ns adventofcodeclojure.day1
  (:require [clojure.java.io :as io]))

(def up? #{\( "("})
(def down? #{\) ")"})

(defn read-input []
  (slurp (io/resource "Day1.txt")))

(defn walk-floor [floor-number direction]
  (cond (some? (up? direction)) (inc floor-number)
        (some? (down? direction)) (dec floor-number)))

(defn get-final-floor [input]
  (reduce walk-floor 0 input))

(defn solve-final-floor []
  (get-final-floor (read-input))
  ;; returns 74
  )
