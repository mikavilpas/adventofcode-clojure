(ns adventofcodeclojure.day2
  (:require [adventofcodeclojure.input-reader :as input-reader]
            [clojure.edn :as edn]))

(defn surface-area [l w h]
  (+ (* 2 l w)
     (* 2 w h)
     (* 2 h l)))

(defn extra-paper [l w h]
  (let [smallest-side-edges (take 2
                                  (sort < [l w h]))
        smallest-side-area (apply * smallest-side-edges)]
    smallest-side-area))

(defn wrapping [l w h]
  (+ (surface-area l w h)
     (extra-paper l w h)))

(defn read-input []
  (input-reader/read-input-lines "Day2.txt"))

(defn parse-input []
  (let [input (read-input)
        dimensions #"^(\d+)x(\d+)x(\d+)$"]
    (map #(let [[[_ l w h]] (re-seq dimensions %)]
            (mapv edn/read-string [l w h]))
         input)))

(defn solve-amount-of-wrapping []
  (let [input (parse-input)]
    (reduce (fn [total present]
              (+ total (apply wrapping present)))
            0
            input)))

(comment
  (solve-amount-of-wrapping)
  ;; 1598415
  )
