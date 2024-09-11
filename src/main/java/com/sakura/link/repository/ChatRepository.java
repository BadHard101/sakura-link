package com.sakura.link.repository;

import com.sakura.link.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public List<Chat> findByUsersId(Integer userId);
}
