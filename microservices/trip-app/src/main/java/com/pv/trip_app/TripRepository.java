package com.pv.trip_app;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {

    public Trip findByDestination(String destination);
    
}
