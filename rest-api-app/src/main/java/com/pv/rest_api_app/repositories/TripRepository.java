package com.pv.rest_api_app.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pv.rest_api_app.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {

    public Trip findByDestination(String destination);
    // select * from Trip where destination = ?

    // find trip with startDate after given date
    public Trip findByStartDateAfter(LocalDate date);

    // custom query using JPQL -- not database independent
    @Query("SELECT t FROM Trip t WHERE t.destination = :dest")
    public Trip customFindByDestination(@Param("dest") String destination);
    
}
