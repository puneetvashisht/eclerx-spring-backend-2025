package com.pv.first_spring_ai_app;

import org.springframework.ai.chat.client.ChatClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatBuilder){
        this.chatClient = chatBuilder.build();
    }
    

    @GetMapping("/chat")
    public String chat(@RequestParam String message){
        // call the open ai to get response
        return this.chatClient.prompt()
        // .system("You are a polite assistant who responds concisely.")
        .user(message)
        .call().content();
    }
}
