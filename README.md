##Development

### Requisites

You'll need:
- MongoDB v4.0.9
    - Should be configured as a replica[1], due to transactions are only supported in replica sets.
    - You may want to use a docker container to run your mongo instance, for that you could execute the script located 
    in `scripts/docker.sh`
        - Once you've done that it's time to convert it to a replica set, to do that execute 
        `docker exec -it mongo-service-replica mongo`, once in the the container mongo console execute `rs.initiate` 
        and `exit`.
        - Since the replica set is running inside a container and we are going execute the application outside a docker 
        network it is necessary that the hostnames used int the replica are reachable.
            - Go to `/etc/hosts` and add this line `127.0.0.1	mongo-service-replica` 
            - What is left is to flush the network config with `sudo killall -HUP mDNSResponder`
        
   
    
    
    
### References
[1] https://docs.mongodb.com/manual/tutorial/convert-standalone-to-replica-set/


## Terminology
Causal Consistency: When an operation depends on a preceding operation
- Read more: https://docs.mongodb.com/manual/core/read-isolation-consistency-recency/#causal-consistency 