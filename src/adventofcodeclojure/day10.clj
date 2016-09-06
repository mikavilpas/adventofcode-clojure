(ns adventofcodeclojure.day10)

(defn look-and-say [s]
  (->> s
       (partition-by identity)
       (mapcat (juxt count first))
       (apply str)))

(defn solve []
  ;; 3579328
  (count
   (reduce (fn [result _] (look-and-say result))
           "1113222113"
           (range 50))))
