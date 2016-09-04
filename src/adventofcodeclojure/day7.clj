(ns adventofcodeclojure.day7
  (:require [adventofcodeclojure.day7-parsing :as parsing]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:import [adventofcodeclojure.day7_types
            LiteralValue And Not Or LeftShift RightShift]))

(defn read-input []
  (slurp (io/resource "Day7.txt")))

(defn parse-input []
  (let [input (str/split-lines (read-input))]
    (into {}
          (map parsing/parse-instruction input))))

(def get-wire-value
  (memoize (fn [sym instructions]
             (if (number? sym)
               sym
               (let [operation (get instructions sym)]
                 (condp = (type operation)
                   ;; reference to another wire
                   java.lang.String (get-wire-value operation instructions)
                   LiteralValue (get-wire-value (:value operation) instructions)
                   And (bit-and (get-wire-value (:a operation) instructions)
                                (get-wire-value (:b operation) instructions))
                   Or (bit-or (get-wire-value (:a operation) instructions)
                              (get-wire-value (:b operation) instructions))
                   Not (bit-not (get-wire-value (:value operation) instructions))
                   LeftShift (bit-shift-left (get-wire-value (:a operation) instructions)
                                             (:count operation))
                   RightShift (bit-shift-right (get-wire-value (:a operation) instructions)
                                               (:count operation))
                   (str "no match for operation " operation)))))))

(defn solve []
  (let [instructions (parse-input)]
    ;; gives the answer 46065 in about a second on a single cpu core
    (get-wire-value "a" instructions)))
