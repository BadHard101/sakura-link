package com.sakura.link.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.sakura.link.repository.UserRepository;
import com.sakura.link.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sakura.link.models.User;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/api/users")
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer userId) throws Exception {

        User foundUser = userService.findUserById(userId);
        return foundUser;
    }

    @PutMapping("/api/users") // (updating user's email provides SignIn for new JWT token)
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
        User reqUser = userService.findUserJwt(jwt);
        User updatedUser = userService.updateUser(user, reqUser.getId());
        return updatedUser;
    }

    @PutMapping("/api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt,
                                  @PathVariable Integer userId2) throws Exception {

        User reqUser = userService.findUserJwt(jwt);
        User user = userService.followUser(reqUser.getId(), userId2);
        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        List<User> users = userService.searchUser(query);
        return users;
    }

    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserJwt(jwt);
        user.setPassword(null);
        return user;
    }
}
