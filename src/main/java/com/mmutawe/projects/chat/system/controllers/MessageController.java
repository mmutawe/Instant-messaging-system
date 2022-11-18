package com.mmutawe.projects.chat.system.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @MessageMapping("/")
    public void sendMessage(){

    }
}
