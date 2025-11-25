package com.pv.adv_spring_ai_app.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    @Value("classpath:/templates/systemPromptTemplate.st")
    Resource systemPromptTemplate;
    
    public ChatController(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message){

        return chatClient.prompt()
        .system(systemPromptTemplate)
        .user(message)
        .call().content();
    }
}
