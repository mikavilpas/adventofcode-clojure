(ns adventofcodeclojure.day7
  (:require [adventofcodeclojure.day7-parsing :as parsing]
            [adventofcodeclojure.input-reader :as input-reader]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [adventofcodeclojure.day7-types :as types])
  (:import [adventofcodeclojure.day7_types
            LiteralValue And Not Or LeftShift RightShift]))

(defn parse-input []
  (let [input (input-reader/read-input-lines "Day7.txt")]
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

(comment
  (solve)
  ;; 46065
  )

;; part 2
(defn solve-part-2 []
  (let [instructions (parse-input)
        wire-a-signal (get-wire-value "a" instructions)
        new-instructions (update instructions "b"
                                 (constantly (types/->LiteralValue wire-a-signal)))]
    (get-wire-value "a" new-instructions)))

(comment
  (solve-part-2)
  ;; very, very fast, again! yay!
  ;; gives 14134
  )
