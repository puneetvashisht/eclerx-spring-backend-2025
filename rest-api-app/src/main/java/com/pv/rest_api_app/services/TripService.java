package com.pv.rest_api_app.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class TripService {

    public boolean checkStartDateBeforeEndDate(LocalDate startDate, LocalDate endDate){
        // logic for checking startDate is before endDate
        boolean result = startDate.isBefore(endDate);
        return result;
    }
    
}
