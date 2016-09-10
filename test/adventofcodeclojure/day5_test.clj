(ns adventofcodeclojure.day5-test
  (:require [adventofcodeclojure.day5 :as day]
            [clojure.test :as t :refer [deftest is]]))

(deftest has-at-least-three-vowels
  (is (= true
         (day/has-at-least-three-vowels "hello mother")))
  (is (= true
         (day/has-at-least-three-vowels "aei")))

  (is (= false
         (day/has-at-least-three-vowels "hello"))))

(deftest has-at-least-one-letter-twice-in-a-row
  (is (= true
         (day/has-at-least-one-letter-twice-in-a-row "aa")))
  (is (= true
         (day/has-at-least-one-letter-twice-in-a-row "abccde")))

  (is (= nil
         (day/has-at-least-one-letter-twice-in-a-row "abcde"))))

(deftest doesnt-contain-naughty-strings
  (is (= true
         (day/doesnt-contain-naughty-strings "hello")))

  (is (= false
         (day/doesnt-contain-naughty-strings "abba"))))

(deftest nice-string?
  (is (day/nice-string? "ugknbfddgicrmopn"))
  (is (day/nice-string? "aaa"))

  (is (not (day/nice-string? "jchzalrnumimnmhp")))
  (is (not (day/nice-string? "haegwjzuvuyypxyu")))
  (is (not (day/nice-string? "dvszwmarrgswjxmb"))))

(deftest get-pairs-at-indices
  (is (= {[\x \y] [0 3],
          [\y \z] [1],
          [\z \x] [2]}
         (day/get-pairs-at-indices "xyzxy"))))

(deftest indices-not-too-close?
  (is (day/indices-not-too-close? [1 2 3]))
  (is (not (day/indices-not-too-close? [1 2]))))

(deftest has-pair-of-letters-appearing-twice
  (is (day/has-pair-of-letters-appearing-twice "aaaa"))
  (is (day/has-pair-of-letters-appearing-twice "aabaa"))
  (is (day/has-pair-of-letters-appearing-twice "uurcxstgmygtbstg"))

  ;; pairs overlap -> no
  (is (not (day/has-pair-of-letters-appearing-twice "aaa"))))

(deftest has-letter-which-repeats-with-one-letter-in-between-test
  (is (= true (day/has-letter-which-repeats-with-one-letter-in-between "aba")))
  (is (= true (day/has-letter-which-repeats-with-one-letter-in-between "abcdefgfed")))

  (is (nil? (day/has-letter-which-repeats-with-one-letter-in-between "abb"))))

(deftest nice-string?-part-2
  (is (day/nice-string?-part-2 "qjhvhtzxzqqjkmpb"))
  (is (day/nice-string?-part-2 "xxyxx"))
  (is (not (day/nice-string?-part-2 "uurcxstgmygtbstg")))
  (is (not (day/nice-string?-part-2 "ieodomkazucvgmuy"))))
