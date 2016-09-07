(ns adventofcodeclojure.day4)

;; adapted from https://gist.github.com/jizhang/4325757
(defn md5 [s]
  (let [algorithm (java.security.MessageDigest/getInstance "MD5")
        raw-bytes (.digest algorithm (.getBytes s))]
    (vec raw-bytes)))

;; for testing
(defn bytes-as-hexadecimal-string [bytes-vector]
  (let [sig (.toString (BigInteger. 1 (byte-array bytes-vector)) 16)
        algorithm (java.security.MessageDigest/getInstance "MD5")
        size (* 2 (.getDigestLength algorithm))
        padding (apply str (repeat (- size (count sig)) "0"))]
    (str padding sig)))

(defn split-byte-to-4-bit-groups
  "Note: if you give this more than a byte, it will ignore anything > 8 bits"
  [b]
  (vector (bit-and b 0xF0)
          (bit-and b 0x0F)))

(defn first-hexadecimal-numbers [number-count bytes-seq]
  (assert (> number-count 2))
  ;; each number in hexadecimal is worth 4 bits
  (let [numbers (mapcat split-byte-to-4-bit-groups bytes-seq)]
    (take number-count numbers)))

(def puzzle-input "iwrupvqb")

(defn hash-for-secret-key [number]
  (md5 (str puzzle-input number)))

(defn starts-with-n-zeroes [n hash-bytes-seq]
  (let [bs (first-hexadecimal-numbers n hash-bytes-seq)]
    (every? zero? bs)))

(defn solve [leading-zero-count]
  (let [hashes-starting-with-zeroes (pmap (juxt hash-for-secret-key identity)
                                          (range))
        starts-with-5-zeroes #(starts-with-n-zeroes leading-zero-count (first %))
        first-solution (->> hashes-starting-with-zeroes
                            (filter starts-with-5-zeroes)
                            first)]
    {:hash (bytes-as-hexadecimal-string (first first-solution))
     :answer (second first-solution)}))

(comment
  (solve 5)
  ;; returns
  ;; {:hash "0000045c5e2b3911eb937d9d8c574f09",
  ;;  :answer 346386}

  (solve 6)
  ;; "Elapsed time: 51120.984289 msecs"
  ;; {:hash "00000094434e1914548b3a1af245fb27", :answer 9958218}

  ;; on my couple-year-old 4 core machine
  )
