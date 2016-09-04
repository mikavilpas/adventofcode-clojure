(ns adventofcodeclojure.day7-parsing
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [adventofcodeclojure.day7-types :as types]
            [clojure.string :as str]))

;; string interpolation from https://gist.github.com/blacktaxi/1676575
(defmacro fmt [^String string]
  (let [-re #"#\{(.*?)\}"
        fstr (clojure.string/replace string -re "%s")
        fargs (map #(read-string (second %)) (re-seq -re string))]
    `(format ~fstr ~@fargs)))

(defmacro fmt-re [s]
  `(re-pattern (fmt ~s)))

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
      {target-wire (types/->LiteralValue (read-wire-or-value a))})))

(defn- parse-and [input]
  (let [and (fmt-re "^#{sym} AND #{sym} -> #{wireName}$")]
    (when-let [[[_ a b target-wire]] (re-seq and input)]
      {target-wire (types/->And (read-wire-or-value a)
                                (read-wire-or-value b))})))

(defn- parse-or [input]
  (let [or (fmt-re "^#{sym} OR #{sym} -> #{wireName}$")]
    (when-let [[[_ a b target-wire]] (re-seq or input)]
      {target-wire (types/->Or (read-wire-or-value a)
                               (read-wire-or-value b))})))

(defn- parse-not [input]
  (let [not (fmt-re "^NOT #{sym} -> #{wireName}$")]
    (when-let [[[_ a target-wire]] (re-seq not input)]
      {target-wire (types/->Not (read-wire-or-value a))})))

(defn- parse-left-shift [input]
  (let [not (fmt-re "^#{sym} LSHIFT #{sym} -> #{wireName}$")]
    (when-let [[[_ a b target-wire]] (re-seq not input)]
      {target-wire (types/->LeftShift (read-wire-or-value a)
                                      (read-wire-or-value b))})))

(defn- parse-right-shift [input]
  (let [not (fmt-re "^#{sym} RSHIFT #{sym} -> #{wireName}$")]
    (when-let [[[_ a b target-wire]] (re-seq not input)]
      {target-wire (types/->RightShift (read-wire-or-value a)
                                       (read-wire-or-value b))})))

(defn parse-instruction [input]
  (or (parse-literal input)
      (parse-and input)
      (parse-or input)
      (parse-not input)
      (parse-left-shift input)
      (parse-right-shift input)
      (throw (new Exception (fmt "unable to parse '#{input}'")))))
