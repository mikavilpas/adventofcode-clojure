(ns adventofcodeclojure.day5
  (:require [adventofcodeclojure.input-reader :as input-reader]))

(defn get-input-lines []
  (input-reader/read-input-lines "Day5.txt"))
;; 1000 different lines of input

(def input (get-input-lines))

(defn has-at-least-three-vowels [s]
  (let [vowels-in-s (filter #(contains? (set "aeiou") %) s)]
    (>= (count vowels-in-s) 3)))

(defn has-at-least-one-letter-twice-in-a-row [s]
  (let [letter-groups (partition-by identity s)]
    (some #(>= (count %) 2)
          letter-groups)))

(defn doesnt-contain-naughty-strings [s]
  (let [naughty-strings ["ab" "cd" "pq" "xy"]]
    (not-any? #(.contains s %)
              naughty-strings)))

(defn nice-string? [s]
  (and (has-at-least-three-vowels s)
       (has-at-least-one-letter-twice-in-a-row s)
       (doesnt-contain-naughty-strings s)))

(defn solve-how-many-strings-are-nice []
  (let [input (get-input-lines)]
    (count (filter nice-string? input))))

(comment
  (solve-how-many-strings-are-nice)
  ;; 258
  )
