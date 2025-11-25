package com.pv.trip_app.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.pv.trip_app.dto.CountryCities;
import com.pv.trip_app.entities.Trip;
import com.pv.trip_app.utils.TripNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
@CrossOrigin
public class TripController {
    @Autowired
    com.pv.trip_app.repositories.TripRepository TripRepository;

    @Autowired
    TripAIProxy tripAIProxy;

    @GetMapping("/trips")
    public List<Trip> getAllTrips() {
        return TripRepository.findAll();
    }

    @GetMapping("/trips/country")
    public CountryCities fetchListOfCities(@RequestParam("message") String message){
        return tripAIProxy.getTripDetails(message);
    }

    @GetMapping("/tripIdeas")
    public String getSomeTripIdeas(@RequestParam String message){
        return tripAIProxy.getTripIdeas(message);

    }
   
    @GetMapping("/trips/{id}")
    public Trip getTripById(@PathVariable int id) {
        return TripRepository.findById(id)
            .orElseThrow(() -> new TripNotFoundException("Trip with id " + id + " not found"));
    }


    
    @GetMapping("/trips/destination/{destination}")
    public Trip getTripByDestination(@PathVariable String destination) {
        return TripRepository.findByDestination(destination);
    }

    @GetMapping("/trips/startDate/upcoming")
    public Trip getTripByStartDateAfter() {
        return TripRepository.findByStartDateAfter(LocalDate.now());
    }

    @GetMapping("/trips/containing/{substring}")
    public org.springframework.data.domain.Page<Trip> getTripsByDestinationContaining(@PathVariable String substring, @RequestParam("size") Integer size , @RequestParam("page") Integer page, @RequestParam("sort") String sort) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sort));
        return TripRepository.findByDestinationContaining(substring, pageable);
    }

    @PostMapping("/trips")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrip(@RequestBody Trip emp) throws Exception {

        TripRepository.save(emp);
    }



    @DeleteMapping("/trips/{id}")
    public void deleteTrip(@PathVariable int id) {
        //implement HttpStatus 404 for records not found
        if (!TripRepository.existsById(id)) {
            throw new TripNotFoundException("Trip with id " + id + " not found");
        }
        TripRepository.deleteById(id);
    }

    @PatchMapping("/trips/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrip(@PathVariable int id, @RequestBody Trip updatedEmp) {
        Trip existingEmp = TripRepository.findById(id)
            .orElseThrow(() -> new TripNotFoundException("Trip with id " + id + " not found"));
        
        log.debug("Existing Trip: {}", existingEmp);

        if (updatedEmp.getStartDate() != null) {
            existingEmp.setStartDate(updatedEmp.getStartDate());
        }

        TripRepository.save(existingEmp);
    }

}