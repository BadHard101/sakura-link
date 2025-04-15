package com.sakura.link.controller;

import com.sakura.link.models.User;
import com.sakura.link.request.LoginRequest;
import com.sakura.link.response.AuthResponse;
import com.sakura.link.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody User user) throws Exception {
        return authService.registerUser(user);
    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
        return authService.authorizeUser(loginRequest);
    }
}
