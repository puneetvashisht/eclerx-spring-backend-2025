package com.pv.rest_api_app.dto;

import java.time.LocalDate;
import java.util.List;

import com.pv.rest_api_app.entities.Itinerary;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


// @Data
public class TripDTO {

    int id;
    @Size(min = 3, message = "Destination must be at least 3 characters long")
    String destination;
    // @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Start date must be in the format YYYY-MM-DD")  
    LocalDate startDate;
    LocalDate endDate;

    List<Itinerary> itineraries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }


    

}
