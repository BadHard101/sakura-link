// src/main/java/com/sakura/link/controller/forum/ForumWsController.java
package com.sakura.link.controller.forum;

import com.sakura.link.dto.forum.PostRequest;
import com.sakura.link.service.forum.ForumService;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ForumWsController {

    private final ForumService service;

    public ForumWsController(ForumService service) {
        this.service = service;
    }

    /** Web-Socket endpoint — новое сообщение в теме */
    @MessageMapping("/forum/{threadId}")
    public void handleNewPost(@DestinationVariable Long threadId,
                              @Payload PostRequest req,
                              Principal principal) {

        Integer userId = Integer.valueOf(principal.getName());   // id хранится строкой
        service.addPost(threadId, req, userId);

        /* Рассылка клиентам выполняется в самом сервисе
           broker.convertAndSend("/chat/forum/" + threadId, dto); */
    }
}
