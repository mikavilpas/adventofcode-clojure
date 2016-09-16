(ns adventofcodeclojure.day10)

(defn look-and-say [s]
  (->> s
       (partition-by identity)
       (mapcat (juxt count first))
       (apply str)))

(comment
  (-> (iterate look-and-say
               "1113222113")
      (nth 40)
      count)
  ;; 252594
  (-> (iterate look-and-say
               "1113222113")
      (nth 50)
      count)
  ;; 3579328
  )
