package com.pv.rest_api_app.services;

import java.time.LocalDate;

import com.pv.rest_api_app.dto.TripDTO;
import com.pv.rest_api_app.entities.Trip;
import com.pv.rest_api_app.repositories.TripRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    public boolean checkStartDateBeforeEndDate(LocalDate startDate, LocalDate endDate){
        // logic for checking startDate is before endDate
        boolean result = startDate.isBefore(endDate);
        return result;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void addTrip( Trip trip) throws Exception {
        tripRepository.save(trip);
        if(!checkStartDateBeforeEndDate(trip.getStartDate(), trip.getEndDate())){
//            throw new RuntimeException("StartDate cannot be less than end date");
            throw new RuntimeException("StartDate cannot be less than end date");
            // for transaction rollback happens only for RuntiTime exception
        }
    }
    
}
