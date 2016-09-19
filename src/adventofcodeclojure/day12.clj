(ns adventofcodeclojure.day12
  (:require [cheshire.core :as cheshire]
            [adventofcodeclojure.input-reader :as input-reader]
            [clojure.walk :as walk]
            [clojure.edn :as edn]))

(defn read-input []
  (input-reader/read-input "Day12.txt"))

(defn get-numbers [edn-object]
  (let [result (atom (vector))]
    (walk/postwalk #(do (when (number? %)
                          (swap! result conj %))
                        %)
                   edn-object)
    @result))

(defn sum-of-numbers [edn-object]
  (->> edn-object
       get-numbers
       (reduce + 0)))

(comment
  ;; "Elapsed time: 8.481371 msecs"
  ;; 191164
  (time
   (-> (read-input)
       cheshire/decode
       sum-of-numbers)))
