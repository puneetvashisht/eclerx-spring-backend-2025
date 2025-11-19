package com.pv.rest_api_app.controllers;

import java.time.LocalDate;
import java.util.List;

import com.pv.rest_api_app.services.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestController;

import com.pv.rest_api_app.dto.TripDTO;
import com.pv.rest_api_app.entities.Itinerary;
import com.pv.rest_api_app.entities.Trip;
import com.pv.rest_api_app.repositories.TripRepository;
import com.pv.rest_api_app.utils.TripNotFoundException;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
@CrossOrigin
public class TripController {
    @Autowired
    TripRepository TripRepository;

    @Autowired
    TripService tripService;

    @Cacheable("trips")
    @GetMapping("/trips")
    public List<Trip> getAllTrips() {
        return TripRepository.findAll();
    }


    @Operation(summary = "Get a Trip by id", description = "Returns a trip as per the id, includes the itinerary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The trip was not found")
    })
    @GetMapping("/trips/{id}")
    public Trip getTripById(@PathVariable int id) {
        return TripRepository.findById(id)
            .orElseThrow(() -> new TripNotFoundException("Trip with id " + id + " not found"));
    }

    // count of trips
    @Cacheable("count")
    @GetMapping("/trips/count")
    public long getTripCount() {
        return TripRepository.count();
    }
    
    
    @GetMapping("/trips/destination/{destination}")
    public Trip getTripByDestination(@PathVariable String destination) {
        return TripRepository.findByDestination(destination);
    }

    @GetMapping("/trips/startDate/upcoming")
    public Trip getTripByStartDateAfter() {
        return TripRepository.findByStartDateAfter(LocalDate.now());
    }

    @GetMapping("/trips/containing/{substring}")
    public org.springframework.data.domain.Page<Trip> getTripsByDestinationContaining(@PathVariable String substring, @RequestParam("size") Integer size , @RequestParam("page") Integer page, @RequestParam("sort") String sort) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sort));
        return TripRepository.findByDestinationContaining(substring, pageable);
    }

    @PostMapping("/trips")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrip(@Valid @RequestBody TripDTO emp) throws Exception {
        Trip trip = convertToEntity(emp);
        tripService.addTrip(trip);
        // TripRepository.save(emp);
    }

    private Trip convertToEntity(TripDTO emp) {
        Trip trip = new Trip();
        trip.setDestination(emp.getDestination());
        trip.setStartDate(emp.getStartDate());
        trip.setEndDate(emp.getEndDate());
        trip.setItineraries(emp.getItineraries());
        return trip;
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


    
}
