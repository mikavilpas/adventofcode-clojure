(ns adventofcodeclojure.day2-test
  (:require [adventofcodeclojure.day2 :as sut]
            [clojure.test :as t :refer [deftest is]]))

(deftest wrapping
  (is (= 58 (sut/wrapping 2 3 4)))
  (is (= 43 (sut/wrapping 1 1 10))))
