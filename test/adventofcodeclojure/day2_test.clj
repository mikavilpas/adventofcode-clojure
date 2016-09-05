(ns adventofcodeclojure.day2-test
  (:require [adventofcodeclojure.day2 :as sut]
            [clojure.test :as t :refer [deftest is]]))

(deftest wrapping
  (is (= 58 (sut/wrapping 2 3 4)))
  (is (= 43 (sut/wrapping 1 1 10))))

(deftest ribbon
  (is (= 10 (sut/ribbon 2 3 4)))
  (is (= 4 (sut/ribbon 1 1 10))))

(deftest bow
  (is (= 24 (sut/bow 2 3 4)))
  (is (= 10 (sut/bow 1 1 10))))
