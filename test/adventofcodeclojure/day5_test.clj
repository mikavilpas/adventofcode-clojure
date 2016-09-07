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
