package com.pv.trip_booking_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/trip-bookings")

@Slf4j
public class TripBookingController {

    @Autowired
    private TripProxy tripProxy;

    @PostMapping("/")
    public TripBooking bookTrip(@RequestBody TripBooking tripBooking) {
        // Placeholder for trip booking logic
       
        // call the Trip app and get trip details for the destination
        RestTemplate restTemplate = new RestTemplate();
        String tripServiceUrl = "http://localhost:9090/api/trips/" + tripBooking.getDestination();
        Trip tripDetails = restTemplate.getForObject(tripServiceUrl, Trip.class);

        // multiply cost per traveler with number of travelers
        double totalCost = tripDetails.getPrice() * tripBooking.getNumberOfTravelers();
        tripBooking.setTotalCost(totalCost);
        return tripBooking;
    }

    @PostMapping("/feign")
    public TripBooking bookTripUsingFeign(@RequestBody TripBooking tripBooking) {
        // Placeholder for trip booking logic
        log.info("Booking trip using Feign client for destination: {}", tripBooking.getDestination());
       
        // call the Trip app and get trip details for the destination
        Trip tripDetails = tripProxy.getTripDetails(tripBooking.getDestination());

        log.info("Received trip details: {}", tripDetails);

        // multiply cost per traveler with number of travelers
        double totalCost = tripDetails.getPrice() * tripBooking.getNumberOfTravelers();
        tripBooking.setTotalCost(totalCost);
        tripBooking.setPort(tripDetails.getPort());
        return tripBooking;
    }
    
}
