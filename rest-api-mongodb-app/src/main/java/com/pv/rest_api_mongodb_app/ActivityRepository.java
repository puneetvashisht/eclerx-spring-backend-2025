package com.pv.rest_api_mongodb_app;

import org.springframework.data.mongodb.repository.MongoRepository;

// mongodb repository for activities

// mongodb repository for activities
public interface ActivityRepository extends MongoRepository<Activity, String> {
    
}
