package com.pv.trip_booking_app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="trip-app")
public interface TripProxy {
    
    @GetMapping("/api/trips/{destination}")
    Trip getTripDetails(@PathVariable("destination") String destination);
}
