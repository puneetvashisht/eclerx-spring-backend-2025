package com.pv.trip_ai_app;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.ListOutputConverter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class StructuredOutputController {
    
    private final ChatClient chatClient;
    
    public StructuredOutputController(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder
        .defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

     @GetMapping("/chat")
     public String chat(@RequestParam String message){
        return chatClient.prompt()
        .user(message)
        .call().content();
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
    public CountryCities chatBean(@RequestParam String message){

        CountryCities countryCities = chatClient.prompt()
        .user(message)
        .call()
        .entity(CountryCities.class); 

        return countryCities;
        
    }
}