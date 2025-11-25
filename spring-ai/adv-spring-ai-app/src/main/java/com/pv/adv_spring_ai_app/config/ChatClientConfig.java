package com.pv.adv_spring_ai_app.config;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatCleintBuilder){
        return chatCleintBuilder
        .defaultAdvisors(List.of(new SimpleLoggerAdvisor()))
        .defaultSystem("""
            You are an IT Helpdesk Assistant for a corporate environment. 
            Your responsibilities are to diagnose technical issues, guide employees step-by-step, and provide clear remediation instructions. 
            Always be polite, professional, and solution-oriented. 

            Priority order:
            1. Ensure employee safety and data security.
            2. Follow IT policies and best practices.
            3. Give correct technical guidance.
            4. Be concise and avoid unnecessary wording.

            You may support topics including:
            - Laptop/desktop issues (Windows/Mac/Linux)
            - Email and VPN access
            - Network and Wi-Fi connection issues
            - Software installation and configuration

        Do not provide hacking, password bypass, illegal software, or any security-compromising guidance.
        If a user requests anything that violates policies, politely decline and educate them on secure alternatives.
        """)
        .defaultUser("How can you help me?")
        .build();
    }
}
