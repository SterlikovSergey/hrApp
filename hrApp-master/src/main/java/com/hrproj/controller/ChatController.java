package com.hrproj.controller;

import com.hrproj.entity.Candidate;
import com.hrproj.entity.ChatMessage;
import com.hrproj.service.impl.CandidateServiceImpl;
import com.hrproj.service.impl.ChatMessageServiceImpl;
import com.hrproj.service.impl.EmployeeServiceImpl;
import com.hrproj.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ChatMessageServiceImpl chatMessageService;
    @MessageMapping("/message-{id}.sendMessage")
    @SendTo("/topic/public.{id}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage,@DestinationVariable String id) {
        if(chatMessage.getMessage()!=null) {
            ChatMessage newChatMessage = new ChatMessage(chatMessage);
            newChatMessage.setUserTo(userService.getByUsername(chatMessage.getTo()));
            newChatMessage.setUserFrom(userService.getByUsername(chatMessage.getFrom()));
            chatMessageService.addChatMessage(newChatMessage);
        }
            return chatMessage;
    }
    @MessageMapping("/message-{id}.addUser")
    @SendTo("/topic/public.{id}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor,
                               @DestinationVariable String id ){
        // Add username in web socket session
//        System.out.println(chatMessage.getFrom());
            headerAccessor.getSessionAttributes().put("username", chatMessage.getFrom());

            return chatMessage;
    }
}
