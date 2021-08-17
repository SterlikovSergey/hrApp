package com.hrproj.config;

import com.hrproj.entity.Candidate;
import com.hrproj.service.impl.CandidateServiceImpl;
import com.hrproj.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.*;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Autowired
    private CandidateServiceImpl candidateService;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        List<Candidate> candidates = candidateService.getAll();
        registry.addEndpoint("/message-" + 1);
        registry.addEndpoint("/message-" + 1).withSockJS();
        if(candidates.size()!=0) {
            for (Candidate candidate : candidates) {
                registry.addEndpoint("/message-" + candidate.getSessionCode());
                registry.addEndpoint("/message-" + candidate.getSessionCode()).withSockJS();
            }
        }
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}