(ns adventofcodeclojure.day8
  (:require [clojure.string :as string]
            [adventofcodeclojure.input-reader :as input-reader]))

(defn string-length-in-code [s]
  (count s))

(defn string-length-in-computer-memory [s]
  (-> s
      ;; remove starting and ending quotes
      (subs 1 (dec (count s)))
      ;; collapse escaped characters, e.g. \"
      ;; (the replacement character doesn't matter)
      (string/replace (str \\ \") ".")
      (string/replace #"\\x[0-9a-f]{1,2}" ".")
      (string/replace (str \\ \\) ".")
      count))

(defn read-input []
  (->> (input-reader/read-input-lines "Day8.txt")
       (map string/trim)))

(defn solve-code-length-minus-computer-memory-length [input]
  (- (->> input
          (pmap string-length-in-code)
          (reduce +))
     (->> input
          (pmap string-length-in-computer-memory)
          (reduce +))))

(comment
  (solve-code-length-minus-computer-memory-length (read-input))
  ;; instant result.
  ;; 1371
  )
