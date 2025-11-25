package com.pv.trip_app.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pv.trip_app.entities.Trip;



public interface TripRepository extends JpaRepository<Trip, Integer> {

    public Trip findByDestination(String destination);
    // select * from Trip where destination = ?

    // find trip with startDate after given date
    public Trip findByStartDateAfter(LocalDate date);

    //add a count trips method
    public long countBy();

    //page find by destination containing substring
    public Page<Trip> findByDestinationContaining(String substring, Pageable pageable);

    // custom query using JPQL -- not database independent
    @Query("SELECT t FROM Trip t WHERE t.destination = :dest")
    public Trip customFindByDestination(@Param("dest") String destination);
    
}