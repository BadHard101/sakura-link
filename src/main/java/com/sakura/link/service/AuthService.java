package com.sakura.link.service;

import com.sakura.link.models.User;
import com.sakura.link.request.LoginRequest;
import com.sakura.link.response.AuthResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    public AuthResponse registerUser(@RequestBody User user) throws Exception;
    public AuthResponse authorizeUser(@RequestBody LoginRequest loginRequest);
}
