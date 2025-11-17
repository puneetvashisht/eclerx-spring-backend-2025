package com.pv.rest_api_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pv.rest_api_app.entities.Itinerary;

public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
    
}
