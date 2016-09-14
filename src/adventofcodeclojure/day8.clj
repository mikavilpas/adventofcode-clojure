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

(defn- total-length [f input]
  (->> input
       (pmap f)
       (reduce +)))

(defn solve-code-length-minus-computer-memory-length [input]
  (- (total-length string-length-in-code input)
     (total-length string-length-in-computer-memory input)))

(comment
  (solve-code-length-minus-computer-memory-length (read-input))
  ;; instant result.
  ;; 1371
  )

;; part 2
(defn encode [s]
  (-> s
      (string/escape {\" (str \\ \")
                      \\ (str \\ \\)})
      ;; add quotes
      (as-> result (str \" result \"))
      string-length-in-code))

(defn solve-encoded-length-minus-code-length [input]
  (- (total-length encode input)
     (total-length string-length-in-code input)))

(comment
  (solve-encoded-length-minus-code-length (read-input))
  ;; instant result.
  ;; 2117
  )
