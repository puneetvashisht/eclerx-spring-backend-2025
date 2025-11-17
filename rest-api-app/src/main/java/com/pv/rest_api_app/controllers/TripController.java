package com.pv.rest_api_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestController;

import com.pv.rest_api_app.entities.Itinerary;
import com.pv.rest_api_app.entities.Trip;
import com.pv.rest_api_app.repositories.TripRepository;
import com.pv.rest_api_app.utils.TripNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
public class TripController {
     @Autowired
    TripRepository TripRepository;
 
    @GetMapping("/trips")
    public List<Trip> getAllTrips() {
        return TripRepository.findAll();
    }

    @GetMapping("/trips/{id}")
    public Trip getTripById(@PathVariable int id) {
        return TripRepository.findById(id)
            .orElseThrow(() -> new TripNotFoundException("Trip with id " + id + " not found"));
    }

    @PostMapping("/trips")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrip(@RequestBody Trip emp) {
        TripRepository.save(emp);
    }

    @DeleteMapping("/trips/{id}")
    public void deleteTrip(@PathVariable int id) {
        //implement HttpStatus 404 for records not found
        if (!TripRepository.existsById(id)) {
            throw new TripNotFoundException("Trip with id " + id + " not found");
        }
        TripRepository.deleteById(id);
    }

    @PatchMapping("/trips/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrip(@PathVariable int id, @RequestBody Trip updatedEmp) {
        Trip existingEmp = TripRepository.findById(id)
            .orElseThrow(() -> new TripNotFoundException("Trip with id " + id + " not found"));
        
        log.debug("Existing Trip: {}", existingEmp);

        if (updatedEmp.getStartDate() != null) {
            existingEmp.setStartDate(updatedEmp.getStartDate());
        }

        TripRepository.save(existingEmp);
    }

    @PostMapping("/trips/{id}/itinerary")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItineraryToTrip(@PathVariable int id, @RequestBody Itinerary itinerary) {
        Trip trip = TripRepository.findById(id)
            .orElseThrow(() -> new TripNotFoundException("Trip with id " + id + " not found"));
        
        log.debug("Adding Itinerary to Trip: {}", trip);

        trip.getItineraries().add(itinerary);
        TripRepository.save(trip);
    }

    @GetMapping("/trips/{id}/itinerary")
    public List<Itinerary> getItinerariesByTripId(@PathVariable int id) {
        Trip trip = TripRepository.findById(id)
            .orElseThrow(() -> new TripNotFoundException("Trip with id " + id + " not found"));
        
        return trip.getItineraries();
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTripNotFoundException(TripNotFoundException ex) {
        return ex.getMessage();
    }  
}
