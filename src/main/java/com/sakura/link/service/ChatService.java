package com.sakura.link.service;

import com.sakura.link.models.Chat;
import com.sakura.link.models.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, User user);
    public Chat findChatById(Integer chatId) throws Exception;
    public List<Chat> findUsersChat(Integer userId);
}
