package com.pv.trip_app.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.pv.trip_app.dto.CountryCities;
import java.util.List;

@FeignClient(name="trip-ai-app")
public interface TripAIProxy {
    
    @GetMapping("/api/chat-bean")
    CountryCities getTripDetails(@RequestParam("message") String message);

    @GetMapping("/api/chat")
    String getTripIdeas(@RequestParam("message") String message);
}
