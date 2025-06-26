package com.example.chattestapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessageSendingOperations sendingOperations;


    @MessageMapping("/send/message")
    public void sendMessage() {
        System.out.println("here i am");
        sendingOperations.convertAndSend("/sub/chat/room/test", "테스트 연결");
    }

}
