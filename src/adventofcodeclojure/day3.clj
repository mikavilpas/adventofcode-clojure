(ns adventofcodeclojure.day3
  (:require [adventofcodeclojure.input-reader :as input-reader]))

;; probably overkill for this simple task
(def up :up)
(def right :right)
(def down :down)
(def left :left)

(defn parse-direction [d]
  (condp = d
    \^ up
    \> right
    \v down
    \< left))

(defn parse-input []
  (let [input (input-reader/read-input "Day3.txt")]
    (map parse-direction input)))

(defn move-santa [santa-coordinates direction]
  (let [[x y] santa-coordinates]
    (condp = direction
      up [x (inc y)]
      right [(inc x) y]
      down [x (dec y)]
      left [(dec x) y])))

(defn solve-visited-houses-count []
  (let [directions (parse-input)
        santa [0 0]
        all-visited-positions (reductions move-santa
                                          santa
                                          directions)]
    (-> all-visited-positions
        set
        count)))

(comment
  (solve-visited-houses-count)
  ;; 2081
  )
