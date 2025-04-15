package com.sakura.link.service;

import com.sakura.link.config.JwtProvider;
import com.sakura.link.models.Chat;
import com.sakura.link.models.User;
import com.sakura.link.repository.ChatRepository;
import com.sakura.link.repository.UserRepository;
import com.sakura.link.request.LoginRequest;
import com.sakura.link.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImplementation implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Override
    public AuthResponse registerUser(User user) throws Exception {
        User isExist = userRepository.findByEmail(user.getEmail());
        if (isExist != null) { throw new Exception("this email is already used"); }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(),
                savedUser.getPassword()
        );
        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token, "Register Success");
        return res;
    }

    @Override
    public AuthResponse authorizeUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token, "Login Success");
        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("invalid username");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("password not matched");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
