package com.sakura.link.service;

import com.sakura.link.models.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user) throws Exception;

    public User findUserById(Integer userId) throws Exception;

    public User findUserByEmail(String email) throws Exception;

    public User followUser(Integer userId1, Integer userId2) throws Exception;

    public User updateUser(User user) throws Exception;

    public List<User> searchUser(String query) throws Exception;

}
