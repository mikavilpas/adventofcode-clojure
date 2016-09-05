(ns adventofcodeclojure.input-reader
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn read-input [file-name]
  (slurp (io/resource file-name)))

(defn read-input-lines [file-name]
  (str/split-lines (read-input file-name)))
