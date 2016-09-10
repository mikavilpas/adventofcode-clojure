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

;; part 2
(defn get-pairs-at-indices [s]
  (->> (partition 2 1 s)
       (zipmap (range))
       (group-by second)
       (map (fn [[pair indices-with-pairs]]
              [pair (map first indices-with-pairs)]))
       (into {})))

(defn indices-not-too-close? [indices]
  (let [indices (sort-by < (distinct indices))]
    (some (fn [i]
            (some (fn [ii]
                    (> (Math/abs (- i ii)) 1))
                  indices))
          indices)))

(defn has-pair-of-letters-appearing-twice [s]
  (let [pairs-at-indices (get-pairs-at-indices s)]
    (some (fn [[pair indices]]
            (indices-not-too-close? indices))
          pairs-at-indices)))

(defn has-letter-which-repeats-with-one-letter-in-between [s]
  (some (fn [[a b c]]
          (= a c))
        (partition 3 1 s)))

(defn nice-string?-part-2 [s]
  (and (has-pair-of-letters-appearing-twice s)
       (has-letter-which-repeats-with-one-letter-in-between s)))

(defn solve-how-many-strings-are-nice-2 []
  (->> (get-input-lines)
       (filter nice-string?-part-2)
       count)
  ;; 53
  )
