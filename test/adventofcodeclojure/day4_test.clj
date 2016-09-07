(ns adventofcodeclojure.day4-test
  (:require [adventofcodeclojure.day4 :as day]
            [clojure.test :as t :refer [deftest is]]))

(deftest md5
  (is (= "000001dbbfa3a5c83a2d506429c7b00e"
         (day/bytes-as-hexadecimal-string
          (day/md5 "abcdef609043")))))

(deftest split-byte-to-4-bit-groups
  (is (= [2r0000 2r1111]
         (day/split-byte-to-4-bit-groups 2r00001111)))
  (is (= [2r10000 2r0000]
         (day/split-byte-to-4-bit-groups 2r00010000))))

(deftest first-hexadecimal-numbers
  (is (= [160 0 0]
         (day/first-hexadecimal-numbers 3 [0xa0 0x0a])))
  (is (= [0 0 0 0 0]
         ;; this is the md5 of "abcdef609043"
         (day/first-hexadecimal-numbers
          5
          [0 0 1 -37 -65 -93 -91 -56 58 45 80 100 41 -57 -80 14]))))

(deftest starts-with-zeroes
  (is (= true
         (day/starts-with-n-zeroes 5 [0x00 0x00 0x0f])))
  (is (= false
         (day/starts-with-n-zeroes 5 [0x01 0x00 0x0]))))
