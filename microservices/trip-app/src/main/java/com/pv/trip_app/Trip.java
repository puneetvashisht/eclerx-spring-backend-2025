package com.pv.trip_app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
class Trip {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    int id;
    private String destination;
    private String startDate;
    private String endDate;
    private Double price;
     public Trip(){

     }

    public Trip(String destination, String startDate, String endDate) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Double getPrice() {
        return price;
    }   
    public void setPrice(Double price) {
        this.price = price;
    }
}