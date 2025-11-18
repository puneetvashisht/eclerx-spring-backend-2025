package com.pv.rest_api_mongodb_app;
// Activity entity for mongodb

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Activity {

    private String id;
    private String description;

    public Activity() {
    }

    public Activity(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
