(ns adventofcodeclojure.day3-test
  (:require [adventofcodeclojure.day3 :as sut]
            [clojure.test :as t :refer [deftest is]]))

(deftest move-santa
  (is (= [0 1] (sut/move-santa [0 0] sut/up)))
  (is (= [1 0] (sut/move-santa [0 0] sut/right)))
  (is (= [0 -1] (sut/move-santa [0 0] sut/down)))
  (is (= [-1 0] (sut/move-santa [0 0] sut/left))))
