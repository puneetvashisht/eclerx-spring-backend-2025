package com.pv.rest_api_app.services;

import lombok.Data;

@Data
public class Activity {
    public Activity(){

    }
    public Activity(String id, String description){
        this.id = id;
        this.description = description;
    }
    String id;
    String description;
}
