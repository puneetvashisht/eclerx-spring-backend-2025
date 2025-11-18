package com.pv.trip_booking_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/trip-bookings")
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
       
        // call the Trip app and get trip details for the destination
        Trip tripDetails = tripProxy.getTripDetails(tripBooking.getDestination());

        // multiply cost per traveler with number of travelers
        double totalCost = tripDetails.getPrice() * tripBooking.getNumberOfTravelers();
        tripBooking.setTotalCost(totalCost);
        tripBooking.setPort(tripDetails.getPort());
        return tripBooking;
    }
    
}
