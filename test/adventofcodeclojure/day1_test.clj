(ns adventofcodeclojure.day1-test
  (:require [adventofcodeclojure.day1 :as d]
            [clojure.test :as t :refer [deftest is]]))

(deftest get-final-floor-test
  (is (= 1 (d/get-final-floor "(")))
  (is (= -1 (d/get-final-floor ")"))))
