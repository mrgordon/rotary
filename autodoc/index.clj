{:namespaces
 ({:source-url nil,
   :wiki-url "rotary.client-api.html",
   :name "rotary.client",
   :doc "Amazon DynamoDB client functions."}),
 :vars
 ({:arglists ([cred {:keys [name hash-key range-key throughput]}]),
   :name "create-table",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/rotary.client-api.html#rotary.client/create-table",
   :doc "Create a table in DynamoDB with the given map of properties.",
   :var-type "function",
   :line 71,
   :file "src/rotary/client.clj"}
  {:arglists
   ([cred
     table
     [hash-key range-key :as key]
     &
     {:keys [expected return-values]}]),
   :name "delete-item",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/rotary.client-api.html#rotary.client/delete-item",
   :doc
   "Delete an item from a DynamoDB table specified by its key, if the\ncoditions are met. Takes the following options:\n  :expected - a map from attribute name to:\n     :rotary.client/exists - checks if the attribute exists\n     :rotary.client/not-exists - checks if the attribute doesn't exist\n     anything else - checks if the attribute is equal to it\n  :return-values - specify what to return:\n     \"NONE\", \"ALL_OLD\", \"UPDATED_OLD\", \"ALL_NEW\", \"UPDATED_NEW\"\n\nThe metadata of the return value contains:\n  :consumed-capacity-units - the consumed capacity units",
   :var-type "function",
   :line 360,
   :file "src/rotary/client.clj"}
  {:arglists ([cred name]),
   :name "delete-table",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/rotary.client-api.html#rotary.client/delete-table",
   :doc "Delete a table in DynamoDB with the given name.",
   :var-type "function",
   :line 145,
   :file "src/rotary/client.clj"}
  {:arglists ([cred name]),
   :name "describe-table",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/rotary.client-api.html#rotary.client/describe-table",
   :doc
   "Returns a map describing the table in DynamoDB with the given name, or nil\nif the table does not exist.",
   :var-type "function",
   :line 123,
   :file "src/rotary/client.clj"}
  {:arglists
   ([cred
     {:keys [name hash-key range-key throughput], :as properties}]),
   :name "ensure-table",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/rotary.client-api.html#rotary.client/ensure-table",
   :doc
   "Creates the table if it does not already exist, updates the provisioned\nthroughput if it does.",
   :var-type "function",
   :line 136,
   :file "src/rotary/client.clj"}
  {:arglists
   ([cred
     table
     hash-key
     &
     {:keys [consistent attributes-to-get], :or {consistent false}}]),
   :name "get-item",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/rotary.client-api.html#rotary.client/get-item",
   :doc
   "Retrieve an item from a DynamoDB table by its hash key.\n\nThe key can be: hash-key, [hash-key], or [hash-key range-key]\n\nOptions can be:\n  :consistent - consistent read\n  :attributes-to-get - a list of attribute names to return\n\nThe metadata of the return value contains:\n  :consumed-capacity-units - the consumed capacity units",
   :var-type "function",
   :line 341,
   :file "src/rotary/client.clj"}
  {:arglists ([cred]),
   :name "list-tables",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/rotary.client-api.html#rotary.client/list-tables",
   :doc "Return a list of tables in DynamoDB.",
   :var-type "function",
   :line 152,
   :file "src/rotary/client.clj"}
  {:arglists ([cred table item-coll & [timeout]]),
   :name "multiple-batch-write",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/rotary.client-api.html#rotary.client/multiple-batch-write",
   :doc
   "Batch writes the items (Clojure maps) in groups of 25 to a DynamoDB table. Retries failing groups once.",
   :var-type "function",
   :line 278,
   :file "src/rotary/client.clj"}
  {:arglists ([cred table item & {:keys [expected return-values]}]),
   :name "put-item",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/rotary.client-api.html#rotary.client/put-item",
   :doc
   "Add an item (a Clojure map) to a DynamoDB table.\nTakes the following options:\n  :expected - a map from attribute name to:\n     :rotary.client/exists - checks if the attribute exists\n     :rotary.client/not-exists - checks if the attribute doesn't exist\n     anything else - checks if the attribute is equal to it\n  :return-values - specify what to return:\n     \"NONE\", \"ALL_OLD\"\n\nThe metadata of the return value contains:\n  :consumed-capacity-units - the consumed capacity units",
   :var-type "function",
   :line 233,
   :file "src/rotary/client.clj"}
  {:arglists
   ([cred
     table
     hash-key
     range-clause
     &
     {:keys
      [order
       limit
       count
       consistent
       attributes-to-get
       exclusive-start-key],
      :as options}]),
   :name "query",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/rotary.client-api.html#rotary.client/query",
   :doc
   "Return the items in a DynamoDB table matching the supplied hash key.\nCan specify a range clause if the table has a range-key ie. `(>= 234)\nTakes the following options:\n  :order - may be :asc or :desc (defaults to :asc)\n  :limit - should be a positive integer\n  :count - return a count if logical true\n  :consistent - return a consistent read if logical true\n  :attributes-to-get - a list of attribute names\n  :exclusive-start-key - primary key of the item from which to\n       continue an earlier query\n\nThe metadata of the return value contains:\n  :count - the number of items in the response\n  :consumed-capacity-units - the consumed capacity units\n  :last-evaluated-key - the primary key of the item where the query\n       operation stopped, or nil if the query is fully completed. It\n       can be used to continue the operation by supplying it as a\n       value to :exclusive-start-key",
   :var-type "function",
   :line 409,
   :file "src/rotary/client.clj"}
  {:arglists
   ([cred
     table
     scan-filter
     &
     {:keys [limit count attributes-to-get exclusive-start-key]}]),
   :name "scan",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/rotary.client-api.html#rotary.client/scan",
   :doc
   "Return the items in a DynamoDB table.\n\nThe scan-filter is a map from attribute name to condition in the\nform [op param1 param2 ...].\n\nTakes the following options:\n  :limit - should be a positive integer\n  :count - return a count if logical true\n  :attributes-to-get - a list of attribute names\n  :exclusive-start-key - primary key of the item from which to\n       continue an earlier query\n\nThe metadata of the return value contains:\n  :count - the number of items in the response\n  :scanned-count - number of items in the complete scan before any\n       filters are applied\n  :consumed-capacity-units - the consumed capacity units\n  :last-evaluated-key - the primary key of the item where the scan\n       operation stopped, or nil if the scan is fully completed. It\n       can be used to continue the operation by supplying it as a\n       value to :exclusive-start-key",
   :var-type "function",
   :line 447,
   :file "src/rotary/client.clj"}
  {:arglists
   ([cred
     table
     [hash-key range-key :as key]
     update-map
     &
     {:keys [expected return-values]}]),
   :name "update-item",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/rotary.client-api.html#rotary.client/update-item",
   :doc
   "Update an item (a Clojure map) in a DynamoDB table.\n\nThe key can be: hash-key, [hash-key], or [hash-key range-key]\n\nUpdate map is a map from attribute name to [action value], where\naction is one of :add, :put, or :delete.\n\nTakes the following options:\n  :expected - a map from attribute name to:\n     :rotary.client/exists - checks if the attribute exists\n     :rotary.client/not-exists - checks if the attribute doesn't exist\n     anything else - checks if the attribute is equal to it\n  :return-values - specify what to return:\n     \"NONE\", \"ALL_OLD\", \"UPDATED_OLD\", \"ALL_NEW\", \"UPDATED_NEW\"",
   :var-type "function",
   :line 314,
   :file "src/rotary/client.clj"}
  {:arglists ([cred {:keys [name throughput]}]),
   :name "update-table",
   :namespace "rotary.client",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/rotary.client-api.html#rotary.client/update-table",
   :doc "Update a table in DynamoDB with the given name.",
   :var-type "function",
   :line 82,
   :file "src/rotary/client.clj"})}
