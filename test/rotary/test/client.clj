(ns rotary.test.client
  (:use [rotary.client]
        [clojure.test])
  (:import (com.amazonaws.auth BasicAWSCredentials)))

(def aws-credential {:access-key (get (System/getenv) "AMAZON_SECRET_ID")
                     :secret-key (get (System/getenv) "AMAZON_SECRET_ACCESS_KEY")})
(def test-table "TestTable")
(def value1 13392)

(defn setup-table [f]
  ;; TODO: ensure-table doesn't block long enough so it is
  ;; possible you will get a ResourceNotFound 400 on put-item
  ;; if the table did not previously exist
  (ensure-table aws-credential {:name test-table
                                :hash-key {:name "value" :type :n}
                                :range-key {:name "time" :type :n}
                                :throughput {:write 1 :read 1}})
  (put-item aws-credential test-table {"value" 22 "time" 1339202645})
  (put-item aws-credential test-table {"value" 22 "time" 1339202640})
  (put-item aws-credential test-table {"value" 25 "time" 1339202645})
  (f))

(defn cleanup-table [f]
  (f)
  (delete-table aws-credential test-table))

(use-fixtures :each setup-table)
(use-fixtures :once cleanup-table)

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
