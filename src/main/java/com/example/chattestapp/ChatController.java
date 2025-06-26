package com.example.chattestapp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessageSendingOperations sendingOperations;


    @MessageMapping("/send/messsage")
    public ResponseEntity<?> sendMessage() {
        sendingOperations.convertAndSend("/sub/chat/room/test", "테스트 연결");
        return ResponseEntity.ok().build();
    }

}
