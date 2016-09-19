(ns adventofcodeclojure.day11
  (:require [clojure.string :as string]
            [adventofcodeclojure.day5 :as day5]))

(def characters (zipmap "abcdefghijklmnopqrstuvwxyz" (range)))
(def numbers (zipmap (range) "abcdefghijklmnopqrstuvwxyza"))

(defn increment-char [c]
  (->> (get characters c)
       inc
       (get numbers)))

(defn increment [password]
  (loop [result (list)
         remaining (reverse password)
         carry? false]
    (let [c (first remaining)]
      (condp = c
        nil (apply str (conj result (when carry? \a)))
        \z (recur (conj result \a)
                  (rest remaining)
                  true)
        (str (apply str (butlast (reverse remaining)))
             (increment-char c)
             (apply str result))))))

(defn contains-increasing-3-characters? [s]
  (->> s
       (partition 3 1)
       (some (fn increasing-characters? [[a b c]]
               (= (-> a increment-char increment-char)
                  (increment-char b)
                  c)))))

(defn contains-only-allowed-chars [s]
  (not (some (set "iol") s)))

(defn- different-non-overlapping-pairs-of-letters [s]
  (let [pairs-at-indices (->> (partition 2 1 s)
                              (map vector (range))
                              (filter (fn [[index pair]]
                                        (apply = pair))))]
    (for [[index pair] pairs-at-indices
          [index2 pair2] pairs-at-indices
          :when (and (not= pair pair2)
                     (> (Math/abs (- index index2)) 1))]
      [[index pair]
       [index2 pair2]])))

(defn contains-two-pairs-of-letters [s]
  (let [pairs (different-non-overlapping-pairs-of-letters s)]
    (not (empty? pairs))))

(defn valid-password? [p]
  (true? (and (contains-increasing-3-characters? p)
              (contains-only-allowed-chars p)
              (contains-two-pairs-of-letters p))))

(defn next-valid-password [p]
  (let [passwords (iterate increment p)]
    (->> passwords
         (drop 1)
         (filter valid-password?)
         first)))

(comment
  ;; solve part 1
  (time (next-valid-password "hepxcrrq"))

  ;; "Elapsed time: 5606.350067 msecs"
  ;; hepxxyzz

  ;; to solve part 2 (password expired again):
  (time (next-valid-password "hepxxyzz"))
  ;; answer (incorrect):
  ;; "Elapsed time: 7091.335433 msecs"
  ;; hepyyzaa
  )
