(ns adventofcodeclojure.day7
  (:require [adventofcodeclojure.day7-parsing :as parsing]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn read-input []
  (slurp (io/resource "Day7.txt")))

(defn parse-input []
  (let [input (str/split-lines (read-input))]
    (pmap parsing/parse-instruction input)))
