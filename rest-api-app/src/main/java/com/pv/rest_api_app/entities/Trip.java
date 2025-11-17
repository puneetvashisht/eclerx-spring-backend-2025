package com.pv.rest_api_app.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(unique = true)
    String destination;
    LocalDate startDate;
    LocalDate endDate;

    // relationship with Itinerary
    @OneToMany(cascade = CascadeType.ALL)
    List<Itinerary> itineraries;
}
