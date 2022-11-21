package com.mmutawe.projects.chat.system.controllers;

import com.mmutawe.projects.chat.system.dtos.MessageDto;
import com.mmutawe.projects.chat.system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.ObjectNotFoundException;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final UserRepository userRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{toUser}")
    public void sendMessage(@DestinationVariable Integer userId, MessageDto message){
        log.info("Message detail: {}. Sent to user with ID: {}", message.getContent(), userId);

        boolean isExists = userRepository.existsById(userId);

        if(!isExists){
            throw new ObjectNotFoundException(userId, "User");
        }

        simpMessagingTemplate.convertAndSend("/channel/messages/" + userId, message);
    }
}
