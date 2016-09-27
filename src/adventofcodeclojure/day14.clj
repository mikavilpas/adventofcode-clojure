(ns adventofcodeclojure.day14
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(def input
  (str/split-lines "Vixen can fly 19 km/s for 7 seconds, but then must rest for 124 seconds.
Rudolph can fly 3 km/s for 15 seconds, but then must rest for 28 seconds.
Donner can fly 19 km/s for 9 seconds, but then must rest for 164 seconds.
Blitzen can fly 19 km/s for 9 seconds, but then must rest for 158 seconds.
Comet can fly 13 km/s for 7 seconds, but then must rest for 82 seconds.
Cupid can fly 25 km/s for 6 seconds, but then must rest for 145 seconds.
Dasher can fly 14 km/s for 3 seconds, but then must rest for 38 seconds.
Dancer can fly 3 km/s for 16 seconds, but then must rest for 37 seconds.
Prancer can fly 25 km/s for 6 seconds, but then must rest for 143 seconds."))

(defn parse-reindeers [input]
  (for [reindeer input]
    (let [re #"(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds."
          [[_ name speed fly-time rest-time]] (re-seq re reindeer)]
      {:name name
       :speed (edn/read-string speed)
       :fly-time (edn/read-string fly-time)
       :rest-time (edn/read-string rest-time)})))

;; a lazy infinite race using mutual recursion
(declare sleep)

(defn- fly [reindeer distance]
  (let [flying (->> (iterate (partial + (:speed reindeer))
                             distance)
                    (drop 1)
                    (take (:fly-time reindeer)))]
    (lazy-cat flying (sleep reindeer (last flying)))))

(defn- sleep [reindeer distance]
  (lazy-cat (repeat (:rest-time reindeer) distance)
            (fly reindeer distance)))

(defn race [reindeer]
  (concat [0]
          (fly reindeer 0)))

(defn race-at [reindeers time]
  (for [reindeer reindeers
        :let [distances (race reindeer)]]
    {:reindeer reindeer
     :distance (nth distances time)}))

(defn winner-at [reindeers time]
  (->> (race-at reindeers time)
       (apply max-key :distance)))

(comment
  (winner-at (parse-reindeers input)
             2503))
