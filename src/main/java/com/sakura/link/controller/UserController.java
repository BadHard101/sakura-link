package com.sakura.link.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakura.link.models.User;

@RestController
public class UserController {
	
	@GetMapping("/users")
	public List<User> getUsers() {
		
		List<User> users = new ArrayList<>();
		
		User user1 = new User("bad", "hard", "badhard@gmail.com", "12345");
		
		users.add(user1);
		
		return users;
	}

}
