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
