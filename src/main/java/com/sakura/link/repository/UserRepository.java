package com.sakura.link.repository;

import com.sakura.link.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);            // было
    User findByUsername(String username);      // ➕ новое

    @Query("select u from User u where " +
            "u.firstName  like %:query% or " +
            "u.lastName   like %:query% or " +
            "u.email      like %:query% or " +   // 🔍 username тоже ищем
            "u.username   like %:query%")
    List<User> searchUser(String query);
}
