package com.pv.rest_api_app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pv.rest_api_app.services.TripService;

@SpringBootTest
public class TestTripService {

    @Autowired
    TripService tripService;

    @Test
    public void testStartDateBeforeEndDate(){
        // preparing input
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(2);

        // invoke service
        boolean result =  tripService.checkStartDateBeforeEndDate(startDate, endDate);
        System.out.println("Start Date is beofe " + result) ;

        //validation
        assertTrue(result);
    }

    @Test
    public void testStartDateBeforeEndDateNegative(){
        // preparing input
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.minusDays(2);

        // invoke service
        boolean result =  tripService.checkStartDateBeforeEndDate(startDate, endDate);
        System.out.println("Start Date is beofe " + result) ;

        //validation
        assertFalse(result);
    }

    @Test
    public void testStartDateBeforeEndDateException(){
        // preparing input
        LocalDate startDate = null;
        LocalDate endDate = null;

       

        //validation
        assertThrows(NullPointerException.class, ()-> {
        // invoke service
                boolean result =  tripService.checkStartDateBeforeEndDate(startDate, endDate);
                System.out.println("Start Date is beofe " + result) ;
        });
    }

    @Test
    public void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0; // This line will throw an ArithmeticException
        });
    }
    
}
