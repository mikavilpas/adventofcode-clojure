(ns adventofcodeclojure.day7
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

;; (defn parse-operation [input-string]
;;   (cond ))

(defn read-input []
  (slurp (io/resource "Day7.txt")))

;; value can be a number or a target-wire
(defrecord LiteralValue [value target-wire])
(defrecord And [value target-wire])
(defrecord Or [value target-wire])
(defrecord Not [value target-wire])
(defrecord LeftShift [value target-wire])
(defrecord RightShift [value target-wire])

;; string interpolation from https://gist.github.com/blacktaxi/1676575
(defmacro fmt [^String string]
  (let [-re #"#\{(.*?)\}"
        fstr (clojure.string/replace string -re "%s")
        fargs (map #(read-string (second %)) (re-seq -re string))]
    `(format ~fstr ~@fargs)))

(defmacro fmt-re [string]
  `(re-pattern (fmt ~string)))

(defn read-wire-or-value [input]
  (let [value (edn/read-string input)]
    (cond
      (symbol? value) (name value)
      (integer? value) value)))

(defn parse-instruction [input]
  (let [sym "([0-9a-zA-Z]+)"
        wireName "([a-zA-Z]+)"
        literal (fmt-re "#{sym} -> #{wireName}")]
    (or (when-let [[[_ a b]] (re-seq literal input)]
          (LiteralValue. (read-wire-or-value a) b)))))
