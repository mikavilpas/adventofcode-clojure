(ns adventofcodeclojure.day11-test
  (:require [adventofcodeclojure.day11 :as day]
            [clojure.test :as t :refer [deftest is]]))

(deftest increment-char-test
  (is (= \b (day/increment-char \a)))
  (is (= \a (day/increment-char \z))))

(deftest increment-test
  (is (= "b" (day/increment "a")))
  (is (= "aa" (day/increment "z")))
  (is (= "ba" (day/increment "az")))
  (is (= "baaaaa" (day/increment "azzzzz")))
  (is (= "abcdefgi" (day/increment "abcdefgh"))))

(deftest contains-increasing-3-characters?-test
  (is (true? (day/contains-increasing-3-characters? "abc")))
  (is (true? (day/contains-increasing-3-characters? "xyz")))
  (is (nil? (day/contains-increasing-3-characters? "abx")))

  (is (true? (day/contains-increasing-3-characters? "yza"))))

(deftest contains-only-allowed-chars-test
  (is (true? (day/contains-only-allowed-chars "abc")))
  (is (false? (day/contains-only-allowed-chars "i")))
  (is (false? (day/contains-only-allowed-chars "o")))
  (is (false? (day/contains-only-allowed-chars "l"))))

(deftest contains-two-pairs-of-letters-test
  (is (true? (day/contains-two-pairs-of-letters "aa,-bb")))

  ;; pairs are not different
  (is (false? (day/contains-two-pairs-of-letters "aaaa")))
  ;; overlapping pairs
  (is (false? (day/contains-two-pairs-of-letters "aaa"))))

(deftest valid-password?-test
  (is (false? (day/valid-password? "hijklmmn")))
  (is (false? (day/valid-password? "abbceffg")))
  (is (false? (day/valid-password? "abbcegjk"))))

(deftest next-valid-password-test
  (is (= (day/next-valid-password "abcdefgh") "abcdffaa")))
