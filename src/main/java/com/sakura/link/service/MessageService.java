package com.sakura.link.service;

import com.sakura.link.models.Message;
import com.sakura.link.models.User;

import java.util.List;

public interface MessageService {
    public Message createMessage(User user, Integer chatId, Message req) throws Exception;
    public List<Message> findChatsMessages(Integer chatId) throws Exception;

}
