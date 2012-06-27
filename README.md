# Rotary

A Clojure client for Amazon's [DynamoDB][1] database.

[1]: http://aws.amazon.com/dynamodb/

## Installation

Add the following dependency to your Clojure project:

    [org.clojars.crowdflower/rotary "0.3.3"]

## Simple Example

    (def aws-credential {:access-key "myAccessKey", :secret-key "mySecretKey"})
    (get-item aws-credential "MyTable" "somePrimaryKey")
    (query aws-credential "AnotherTable" 22 `(> 13392) :limit 100 :count true)
    (update-item aws-credential "MyFavoriteTable" [36 263] {"awesomeness" [:add 20] "updated" [:put 1339529420]})
    (put-item aws-credential "RandomTable" {"myHashKey" 777 "theRangeKey" 3843 "someOtherAttribute" 33} :return-values "ALL_OLD")

## Documentation

* [API Docs](http://mrgordon.github.com/rotary)

## License

Copyright (&copy;) 2012 James Reeves  
Modifications (&copy;) 2012 Matthew Gordon (CrowdFlower)  
Modifications (&copy;) 2012 Ivan Toshkov  

Distributed under the Eclipse Public License, the same as Clojure.
