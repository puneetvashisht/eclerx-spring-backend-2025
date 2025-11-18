package com.pv.trip_booking_app;

import lombok.Data;

@Data
public class TripBooking {
    
    private String destination;
    private Integer numberOfTravelers;

    private Double totalCost;
    private Integer port;
}
