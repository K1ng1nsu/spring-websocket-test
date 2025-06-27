package com.example.chattestapp.controller;

import com.example.chattestapp.dto.ChatRequest;
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
    public void sendMessage(ChatRequest chatRequest) {
        System.out.println("here i am");
        System.out.println(chatRequest);
        sendingOperations.convertAndSend("/sub/chat/room/test",chatRequest);
        //  HtmlUtils.htmlEscape(chRequest.get)
        // import org.springframework.web.util.HtmlUtils;
    }

}
