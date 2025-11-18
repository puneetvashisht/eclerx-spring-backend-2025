package com.pv.trip_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private TripRepository tripRepository;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrip(@RequestBody Trip trip) {
         tripRepository.save(trip);
    }

    @GetMapping("/")
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @GetMapping("/{destination}")
    public Trip getTripByDestination(@PathVariable String destination) {
        Trip trip = tripRepository.findByDestination(destination);
        trip.setPort(port);
        return trip;
    }    
}
