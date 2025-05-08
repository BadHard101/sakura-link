package com.sakura.link.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RealTimeChat {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    // Клиент посылает на /app/chat/{chatId}
    @MessageMapping("/chat/{chatId}")
    public void processMessage(
            @Payload com.sakura.link.models.Message message,
            @DestinationVariable String chatId) {

        // ✨ FIX: шлём всем подписанным на /chat/{chatId}
        simpMessagingTemplate.convertAndSend("/chat/" + chatId, message);
    }
}
