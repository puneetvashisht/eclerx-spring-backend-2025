package com.pv.rest_api_app.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.pv.rest_api_app.entities.Itinerary;
import com.pv.rest_api_app.repositories.ItineraryRepository;

@RestController
@RequestMapping("/api")
public class ItineraryController {
    
    @Autowired
    ItineraryRepository itineraryRepository;

    @PatchMapping("/itineraries/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItinerary(@PathVariable int id, @RequestBody Itinerary updatedItinerary) {
        // Implementation for updating an itinerary
        itineraryRepository.findById(id).ifPresent(existingItinerary -> {
            if (updatedItinerary.getActivity() != null) {
                existingItinerary.setActivity(updatedItinerary.getActivity());
            }
            if (updatedItinerary.getLocation() != null) {
                existingItinerary.setLocation(updatedItinerary.getLocation());
            }
            if (updatedItinerary.getTime() != null) {
                existingItinerary.setTime(updatedItinerary.getTime());
            }
            itineraryRepository.save(existingItinerary);
        });

    }

    @DeleteMapping("/itineraries/{id}")
    public void deleteItinerary(@PathVariable int id) {
        itineraryRepository.deleteById(id);
    }   
    
}
