(ns adventofcodeclojure.day13
  (:require [adventofcodeclojure.input-reader :as input-reader]
            [clojure.edn :as edn]
            [clojure.math.combinatorics :as combo]))

(defn read-input []
  (input-reader/read-input-lines "Day13.txt"))

(defn- parse-happiness
  [regex lines]
  (for [line lines]
    (let [[[_ sitter gain-or-lose
            happiness sittee]] (re-seq regex line)
          happiness (edn/read-string happiness)]
      {[sitter sittee]
       (if (= "lose" gain-or-lose)
         (- happiness)
         happiness)})))

(defn parse-input [lines]
  (let [regex #"(\w+) would (\w+) (\d+) happiness units by sitting next to (\w+)."]
    (->> (parse-happiness regex lines)
         (into {}))))

(defn seating-orders [names]
  (assert (> (count names) 2))
  (let [combinations (combo/permutations names)
        add-first-and-last (fn [people]
                             (concat [(last people)] people))]
    (map add-first-and-last combinations)))

(defn count-happiness [happinesses seating-order]
  (let [neighbors (partition 2 1 seating-order)]
    (reduce (fn [total [a b]]
              (+ total
                 (get happinesses [a b])
                 (get happinesses [b a])))
            0
            neighbors)))

(defn seating-order-with-max-happiness [happinesses]
  (let [attendees (->> (keys happinesses)
                       (mapcat flatten)
                       set)
        sitting-orders (seating-orders attendees)]
    (->> sitting-orders
         (apply max-key (partial count-happiness happinesses)))))

(comment
  (let [happinesses (-> (read-input) parse-input)
        best-sitting-order (seating-order-with-max-happiness
                            happinesses)]
    (count-happiness happinesses best-sitting-order))
  ;; 618
  )
