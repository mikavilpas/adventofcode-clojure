(ns adventofcodeclojure.day6
  (:require [adventofcodeclojure.input-reader :as input-reader]
            [clojure.edn :as edn]))

(defrecord Range [xa ya xb yb])
(defrecord TurnOn [range])
(defrecord TurnOff [range])
(defrecord Toggle [range])

(defn get-empty-grid []
  (let [row (into [] (repeat 1000 false))]
    (loop [grid (transient [])
           row-number 1]
      (if (<= row-number 1000)
        (recur (conj! grid row) (inc row-number))
        (persistent! grid)))))

(defn- parse-point-range [line]
  (let [point-range-regexp #"(\d+),(\d+) through (\d+),(\d+)$"
        [[_ xa ya xb yb]] (re-seq point-range-regexp line)]
    (apply ->Range (map edn/read-string [xa ya xb yb]))))

(defn parse-instruction [line]
  (let [turn-on #"^turn on"
        turn-off #"^turn off"
        toggle #"^toggle"]
    (let [pr (parse-point-range line)]
      (cond (re-find toggle line) (->Toggle pr)
            (re-find turn-on line) (->TurnOn pr)
            (re-find turn-off line) (->TurnOff pr)))))

(defn parse-input []
  (let [input (input-reader/read-input-lines "Day6.txt")]
    (map parse-instruction input)))

(defn switch-lights [grid instruction]
  (let [{:keys [xa ya xb yb]} (:range instruction)
        target-cell-indices (for [row (range ya (inc yb))
                                  cell (range xa (inc xb))]
                              [row cell])
        update-grid-cells (fn [updater]
                            (reduce (fn [result [row cell]]
                                      (update-in result [row cell] updater))
                                    grid
                                    target-cell-indices))]
    (condp = (type instruction)
      TurnOn (update-grid-cells (constantly true))
      TurnOff (update-grid-cells (constantly false))
      Toggle (update-grid-cells (fn [light] (not light))))))

(defn solve-amount-of-lit-lights []
  (->> (parse-input)
       (reduce switch-lights (get-empty-grid))
       (mapcat #(filter true? %))
       count))

(comment
  (solve-amount-of-lit-lights)
  ;; 543903
  )
