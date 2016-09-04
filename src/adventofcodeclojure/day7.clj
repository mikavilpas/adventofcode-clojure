(ns adventofcodeclojure.day7
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.string :as str]))

(defn read-input []
  (slurp (io/resource "Day7.txt")))

;; value can be a number or a target-wire
(defrecord LiteralValue [value target-wire])
(defrecord And [a b target-wire])
(defrecord Or [a b target-wire])
(defrecord Not [value target-wire])
(defrecord LeftShift [a b target-wire])
(defrecord RightShift [a b target-wire])

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

(def sym "([0-9a-zA-Z]+)")
(def wireName "([a-zA-Z]+)")

(defn- parse-literal [input]
  (let [literal (fmt-re "^#{sym} -> #{wireName}$")]
    (when-let [[[_ a target-wire]] (re-seq literal input)]
      (->LiteralValue (read-wire-or-value a) target-wire))))

(defn- parse-and [input]
  (let [and (fmt-re "^#{sym} AND #{sym} -> #{wireName}$")]
    (when-let [[[_ a b target-wire]] (re-seq and input)]
      (->And (read-wire-or-value a)
             (read-wire-or-value b)
             target-wire))))

(defn- parse-or [input]
  (let [or (fmt-re "^#{sym} OR #{sym} -> #{wireName}$")]
    (when-let [[[_ a b target-wire]] (re-seq or input)]
      (->Or (read-wire-or-value a)
            (read-wire-or-value b)
            target-wire))))

(defn- parse-not [input]
  (let [not (fmt-re "^NOT #{sym} -> #{wireName}$")]
    (when-let [[[_ a target-wire]] (re-seq not input)]
      (->Not (read-wire-or-value a)
             target-wire))))

(defn- parse-left-shift [input]
  (let [not (fmt-re "^#{sym} LSHIFT #{sym} -> #{wireName}$")]
    (when-let [[[_ a b target-wire]] (re-seq not input)]
      (->LeftShift (read-wire-or-value a)
                   (read-wire-or-value b)
                   target-wire))))

(defn- parse-right-shift [input]
  (let [not (fmt-re "^#{sym} RSHIFT #{sym} -> #{wireName}$")]
    (when-let [[[_ a b target-wire]] (re-seq not input)]
      (->RightShift (read-wire-or-value a)
                    (read-wire-or-value b)
                    target-wire))))

(defn parse-instruction [input]
  (or (parse-literal input)
      (parse-and input)
      (parse-or input)
      (parse-not input)
      (parse-left-shift input)
      (parse-right-shift input)
      (throw (new Exception (fmt "unable to parse '#{input}'")))))

(defn parse-input []
  (let [input (str/split-lines (read-input))]
    (pmap parse-instruction input)))
