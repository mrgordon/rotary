(ns rotary.test.client
  (:use [rotary.client])
  (:use [clojure.test])
  (:import (com.amazonaws.auth BasicAWSCredentials)))

(def aws-credential {:access-key "myAccessKey", :secret-key "mySecretKey"})

(def test-table "TestTable")

; The tests rely on having a DynamoDB table named "TestTable" with the following three items
; hash-key 22, range-key 1339202645
; hash-key 22, range-key 1339202640
; hash-key 25, range-key 1339202645

(def value1 13392)

(deftest query-with-range-key-can-find-rows
  (is (count (query aws-credential test-table 25 `(> 1339))) 1))

(deftest query-with-range-key-can-exclude-rows
  (is (count (query aws-credential test-table 25 `(> 13392029999))) 0))

(deftest query-with-range-key-can-handle-greater-than
  (is (count (query aws-credential test-table 22 `(> 1339202642))) 1))

(deftest query-with-range-key-can-handle-less-than
  (is (count (query aws-credential test-table 22 [:< 1339202642])) 1))

(deftest query-with-range-key-less-than
  (is (count (query aws-credential test-table 22 `(< 1339202640))) 0))

(deftest query-with-range-key-less-than-or-equals-and-count
  (is (query aws-credential test-table 22 `(<= 1339202640) :count true) 1))

(deftest query-with-range-key-can-handle-greater-than-with-multiple-items
  (is (query aws-credential test-table 22 `(> ~value1) :count true) 2))

(deftest query-with-range-key-can-handle-greater-than-with-multiple-items-and-limit
  (is (query aws-credential test-table 22 `(> ~value1) :count true :limit 1) 1))

(deftest query-with-range-key-can-exclude-with-between
  (is (count (query aws-credential test-table 22 `(between ~value1 13399))) 0))

(deftest query-with-range-key-can-include-with-between
  (is (count (query aws-credential test-table 22 `(between ~value1 999999999))) 2))

(deftest query-with-range-key-can-include-and-exclude-with-between
  (is (count (query aws-credential test-table 22 [:between value1 1339202643])) 1))

(deftest query-using-native-credentials-instead-of-keys
  (let [cred {:credentials
              (BasicAWSCredentials. (:access-key aws-credential)
                                    (:secret-key aws-credential))}]
    (is (count (query cred test-table 25 `(> 1339))) 1)))
