package com.sakura.link.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakura.link.models.User;

@RestController
public class UserController {
	
	@GetMapping("/users")
	public List<User> getUsers() {
		
		List<User> users = new ArrayList<>();
		
		User user1 = new User(2,"bad", "hard", "badhard@gmail.com", "12345");
		
		users.add(user1);
		
		return users;
	}
	
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) {
		
		User user = new User(1, "Akio", "Scott", "akio@gmail.com", "coolpass");
		if (user.getId() == id) {
			return user;
		}
		return null;
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		
		return newUser;
	}
}
