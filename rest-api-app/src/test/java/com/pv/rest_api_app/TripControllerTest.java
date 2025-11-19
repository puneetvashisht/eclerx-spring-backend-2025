package com.pv.rest_api_app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pv.rest_api_app.controllers.TripController;
import com.pv.rest_api_app.dto.TripDTO;
import com.pv.rest_api_app.entities.Itinerary;
import com.pv.rest_api_app.entities.Trip;
import com.pv.rest_api_app.repositories.TripRepository;
import com.pv.rest_api_app.utils.TripNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TripControllerTest {

    @Mock
    TripRepository tripRepository;

    @InjectMocks
    TripController tripController;

    Trip sampleTrip;

    @BeforeEach
    void setup() {
        sampleTrip = new Trip();
        sampleTrip.setId(1);
        sampleTrip.setDestination("Paris");
        sampleTrip.setStartDate(LocalDate.of(2025, 11, 20));
        sampleTrip.setEndDate(LocalDate.of(2025, 11, 25));
        sampleTrip.setItineraries(new ArrayList<>());
    }

    @Test
    void getAllTrips_returnsList() {
        List<Trip> list = List.of(sampleTrip);
        when(tripRepository.findAll()).thenReturn(list);

        List<Trip> result = tripController.getAllTrips();

        assertEquals(1, result.size());
        assertEquals("Paris", result.get(0).getDestination());
        verify(tripRepository, times(1)).findAll();
    }

    @Test
    void getTripById_found() {
        when(tripRepository.findById(1)).thenReturn(Optional.of(sampleTrip));

        Trip result = tripController.getTripById(1);

        assertNotNull(result);
        assertEquals("Paris", result.getDestination());
    }

    @Test
    void getTripById_notFound_throws() {
        when(tripRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(TripNotFoundException.class, () -> tripController.getTripById(2));
    }

    @Test
    void addTrip_callsSave() {
        TripDTO dto = new TripDTO();
        dto.setDestination("London");
        dto.setStartDate(LocalDate.of(2025, 12, 1));
        dto.setEndDate(LocalDate.of(2025, 12, 5));
        dto.setItineraries(new ArrayList<>());

        tripController.addTrip(dto);

        verify(tripRepository, times(1)).save(any(Trip.class));
    }

    @Test
    void deleteTrip_exists_deletes() {
        when(tripRepository.existsById(1)).thenReturn(true);

        tripController.deleteTrip(1);

        verify(tripRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteTrip_notExists_throws() {
        when(tripRepository.existsById(99)).thenReturn(false);

        assertThrows(TripNotFoundException.class, () -> tripController.deleteTrip(99));
    }

    @Test
    void updateTrip_updatesStartDate() {
        Trip updated = new Trip();
        updated.setStartDate(LocalDate.of(2026, 1, 1));

        when(tripRepository.findById(1)).thenReturn(Optional.of(sampleTrip));

        tripController.updateTrip(1, updated);

        verify(tripRepository, times(1)).save(argThat(t -> t.getStartDate().equals(LocalDate.of(2026, 1, 1))));
    }

    @Test
    void addItineraryToTrip_addsAndSaves() {
        Itinerary it = new Itinerary();
        it.setId(10);
        it.setTitle("Eiffel visit");

        when(tripRepository.findById(1)).thenReturn(Optional.of(sampleTrip));

        tripController.addItineraryToTrip(1, it);

        verify(tripRepository, times(1)).save(argThat(t -> t.getItineraries().contains(it)));
    }

}
