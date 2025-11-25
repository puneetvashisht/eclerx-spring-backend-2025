package com.pv.adv_spring_ai_app.controllers;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pv.adv_spring_ai_app.dto.CountryCities;

@RestController
@RequestMapping("/api")
public class StructuredOutputController {
    
    private final ChatClient chatClient;
    
    public StructuredOutputController(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder
        .defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @GetMapping("/chat-list")
    public ResponseEntity<List<String>> chatList(@RequestParam String message){

        List<String> countryCities = chatClient.prompt()
        .user(message)
        .call()
        .entity(new ListOutputConverter()); 

        return ResponseEntity.ok(countryCities);
        
    }

    @GetMapping("/chat-bean")
    public ResponseEntity<CountryCities> chatBean(@RequestParam String message){

        CountryCities countryCities = chatClient.prompt()
        .user(message)
        .call()
        .entity(CountryCities.class); 

        return ResponseEntity.ok(countryCities);
        
    }
}
