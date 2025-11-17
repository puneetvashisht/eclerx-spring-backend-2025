package com.pv.rest_api_app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String activity;
    String location;
    String time;
}
