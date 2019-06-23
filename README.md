##Development

### Requisites

- MongoDB v4.0.9
    - Should be configured as a replica[1], since transaction are only supported in replica sets.
    - If you are using docker to deploy you replica remember to 
    
    
    
### References
[1] https://docs.mongodb.com/manual/tutorial/convert-standalone-to-replica-set/


## Terminology
Causal Consistency: When an operation depends on a preceding operation
- Read more: https://docs.mongodb.com/manual/core/read-isolation-consistency-recency/#causal-consistency 