(ns rotary.test.client
  (:use [rotary.client])
  (:use [clojure.test]))

(def aws-credential {:access-key "myAccessKey", :secret-key "mySecretKey"})
(def test-table "TestTable")

; The tests rely on having a DynamoDB table named "TestTable" with the following three items
; hash-key 22, range-key 1339202645
; hash-key 22, range-key 1339202640
; hash-key 25, range-key 1339202645

(deftest query-with-range-key-can-find-rows
  (is (count (query aws-credential test-table 25 {:range-key 1339 :operator "GT"})) 1))

(deftest query-with-range-key-can-exclude-rows
  (is (count (query aws-credential test-table 25 {:range-key 13392029999 :operator "GT"})) 0))

(deftest query-with-range-key-can-handle-greater-than
  (is (count (query aws-credential test-table 22 {:range-key 1339202642 :operator "GT"})) 1))

(deftest query-with-range-key-can-handle-less-than
  (is (count (query aws-credential test-table 22 {:range-key 1339202642 :operator "LT"})) 1))

(deftest query-with-range-key-less-than
  (is (count (query aws-credential test-table 22 {:range-key 1339202640 :operator "LT"})) 0))

(deftest query-with-range-key-less-than-or-equals-and-count
  (is (query aws-credential test-table 22 {:range-key 1339202640 :operator "LE" :count true}) 1))

(deftest query-with-range-key-can-handle-greater-than-with-multiple-items
  (is (query aws-credential test-table 22 {:range-key 13392 :operator "GT" :count true}) 2))

(deftest query-with-range-key-can-handle-greater-than-with-multiple-items-and-limit
  (is (query aws-credential test-table 22 {:range-key 13392 :operator "GT" :limit 1 :count true})) 1)

(deftest query-with-range-key-can-exclude-with-between
  (is (count (query aws-credential test-table 22 {:range-key 13392 :operator "BETWEEN" :range-end 13399})) 0))

(deftest query-with-range-key-can-include-with-between
  (is (count (query aws-credential test-table 22 {:range-key 13392 :operator "BETWEEN" :range-end 999999999})) 2))

(deftest query-with-range-key-can-include-and-exclude-with-between
  (is (count (query aws-credential test-table 22 {:range-key 13392 :operator "BETWEEN" :range-end 1339202643})) 1))
