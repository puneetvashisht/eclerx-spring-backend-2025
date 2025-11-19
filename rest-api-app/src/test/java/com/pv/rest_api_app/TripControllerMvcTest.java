package com.pv.rest_api_app;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pv.rest_api_app.entities.Trip;
import com.pv.rest_api_app.repositories.TripRepository;
import com.pv.rest_api_app.utils.TripExceptionHandler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = com.pv.rest_api_app.controllers.TripController.class)
@Import(TripExceptionHandler.class)
public class TripControllerMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TripRepository tripRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllTrips_returnsOkAndJson() throws Exception {
        Trip t = new Trip();
        t.setId(1);
        t.setDestination("Paris");
        t.setStartDate(LocalDate.of(2025,11,20));
        t.setEndDate(LocalDate.of(2025,11,25));

        when(tripRepository.findAll()).thenReturn(List.of(t));

        mockMvc.perform(get("/api/trips").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].destination").value("Paris"));
    }

    @Test
    void postTrip_invalidDestination_returnsBadRequest() throws Exception {
        // destination shorter than @Size(min=3)
        String requestJson = "{\"destination\":\"ab\",\"startDate\":\"2025-11-20\",\"endDate\":\"2025-11-25\",\"itineraries\":[] }";

        mockMvc.perform(post("/api/trips")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.error").value("Validation Failed"))
            .andExpect(jsonPath("$.message").exists())
            .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("destination")));
    }

}
