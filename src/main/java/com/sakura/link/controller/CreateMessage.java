package com.sakura.link.controller;

import com.sakura.link.models.Message;
import com.sakura.link.models.User;
import com.sakura.link.service.MessageService;
import com.sakura.link.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMessage {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserJwt(jwt);
        Message message = messageService.createMessage(user, chatId, req);
        return message;
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatsMessage(@RequestHeader("Authorization") String jwt,
                                          @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserJwt(jwt);
        List<Message> messages = messageService.findChatsMessages(chatId);
        return messages;
    }



}
