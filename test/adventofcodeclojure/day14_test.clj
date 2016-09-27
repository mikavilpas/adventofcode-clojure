(ns adventofcodeclojure.day14-test
  (:require [adventofcodeclojure.day14 :as day]
            [clojure.test :as t :refer [deftest is]]))

(deftest parse-input-test
  (is (= '({:name "Vixen",:speed 19,:fly-time 7,:rest-time 124}
           {:name "Rudolph",:speed 3,:fly-time 15,:rest-time 28}
           {:name "Donner",:speed 19,:fly-time 9,:rest-time 164}
           {:name "Blitzen",:speed 19,:fly-time 9,:rest-time 158}
           {:name "Comet",:speed 13,:fly-time 7,:rest-time 82}
           {:name "Cupid",:speed 25,:fly-time 6,:rest-time 145}
           {:name "Dasher",:speed 14,:fly-time 3,:rest-time 38}
           {:name "Dancer",:speed 3,:fly-time 16,:rest-time 37}
           {:name "Prancer",:speed 25,:fly-time 6,:rest-time 143})
         (day/parse-reindeers day/input))))

(deftest race-test
  (is (= [0 1 2 2 3 4]
         (take 6 (day/race {:name "ru-lol-ph" ; sad
                            :speed 1
                            :fly-time 2
                            :rest-time 1})))))

(def comet {:name "Comet", :speed 14, :fly-time 10, :rest-time 127})
(def dancer {:name "Dancer", :speed 16, :fly-time 11, :rest-time 162})

(deftest race-at-test
  ;; every race starts at 0
  (is (= [{:reindeer comet, :distance 0}
          {:reindeer dancer, :distance 0}]
         (day/race-at [comet dancer] 0)))

  ;; each second moves the reindeers forward
  (is (= [{:reindeer comet, :distance 14}
          {:reindeer dancer, :distance 16}]
         (day/race-at [comet dancer] 1)))

  (is (= [{:reindeer comet,:distance 140}]
         (day/race-at [comet] 10)))

  (is (= [{:reindeer comet, :distance 140}
          {:reindeer dancer, :distance 160}]
         (day/race-at [comet dancer] 10))))
