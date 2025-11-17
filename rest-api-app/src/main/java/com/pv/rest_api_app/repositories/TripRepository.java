package com.pv.rest_api_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pv.rest_api_app.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    
}
