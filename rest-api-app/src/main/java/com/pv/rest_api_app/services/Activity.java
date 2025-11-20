package com.pv.rest_api_app.services;

import lombok.Data;

@Data
public class Activity {
    public Activity(){

    }
    public Activity(String description){
        this.description = description;
    }
    String description;
}
