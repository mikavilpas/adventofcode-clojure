(ns adventofcodeclojure.day6-test
  (:require [adventofcodeclojure.day6 :as day]
            [clojure.test :as t :refer [deftest is]]))

(deftest parse-instruction-test
  (is (= (day/->Toggle (day/->Range 461, 550, 564, 900))
         (day/parse-instruction "toggle 461,550 through 564,900")))
  (is (= (day/->TurnOn (day/->Range 461, 550, 564, 900))
         (day/parse-instruction "turn on 461,550 through 564,900")))
  (is (= (day/->TurnOff (day/->Range 461, 550, 564, 900))
         (day/parse-instruction "turn off 461,550 through 564,900"))))

(def two-by-two-grid [[false false]
                      [false false]])

(deftest switch-lights-test
  ;; switch on at correct locations
  (is (= [[true false]
          [false true]]
         (-> two-by-two-grid
             (day/switch-lights (day/->TurnOn (day/->Range 0 0 0 0)))
             (day/switch-lights (day/->TurnOn (day/->Range 1 1 1 1))))))

  (is (= [[true true]
          [true true]]
         (day/switch-lights two-by-two-grid (day/->TurnOn (day/->Range 0 0 1 1)))))

  ;; turn on already turned on lights
  (is (= [[true true]
          [true true]]
         (day/switch-lights [[true true]
                             [true true]] (day/->TurnOn (day/->Range 0 0 1 1)))))

  (is (= [[true true]
          [true false]]
         (-> [[true true]
              [true true]]
             (day/switch-lights (day/->TurnOn (day/->Range 1 1 1 1)))
             (day/switch-lights (day/->TurnOff (day/->Range 1 1 1 1))))))
  ;; turning off turned off lights
  (is (= two-by-two-grid
         (day/switch-lights two-by-two-grid (day/->TurnOff (day/->Range 0 0 1 1)))))

  ;; toggle lights
  (is (= [[false false]
          [false true]]
         (day/switch-lights two-by-two-grid (day/->Toggle (day/->Range 1 1 1 1))))))

(def two-by-two-brightness-grid [[0 0]
                                 [0 0]])

(deftest change-light-brightness-test
  (is (= [[0 1]
          [0 0]]
         (day/change-light-brightness two-by-two-brightness-grid
                                      (day/->TurnOn (day/->Range 1 0 1 0)))))
  (is (= two-by-two-brightness-grid
         (day/change-light-brightness [[0 1]
                                       [0 0]]
                                      (day/->TurnOff (day/->Range 1 0 1 0)))))
  ;; will not go under 0
  (is (= two-by-two-brightness-grid
         (day/change-light-brightness two-by-two-brightness-grid
                                      (day/->TurnOff (day/->Range 0 0 0 0)))))
  (is (= [[0 2] [0 0]]
         (day/change-light-brightness two-by-two-brightness-grid
                                      (day/->Toggle (day/->Range 1 0 1 0))))))
