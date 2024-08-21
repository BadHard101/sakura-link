package com.sakura.link.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.sakura.link.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sakura.link.models.User;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}
	
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) {
		
		User user = new User(
				1,
				"Akio",
				"Scott",
				"akio@gmail.com",
				"coolpass"
		);

		if (Objects.equals(user.getId(), id)) {
			return user;
		}
		return null;
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {

		User newUser = new User();

		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());

        return userRepository.save(newUser);
	}

	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {

		User newUser = new User(
				1,
				"Akio",
				"Scott",
				"akio@gmail.com",
				"coolpass"
		);

		if (user.getEmail() != null)
			newUser.setEmail(user.getEmail());
		if (user.getFirstName() != null)
			newUser.setFirstName(user.getFirstName());
		if (user.getLastName() != null)
			newUser.setLastName(user.getLastName());
		if (user.getPassword() != null)
			newUser.setPassword(user.getPassword());

		return newUser;
	}

	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) {

		return "User with id " + userId + " has been deleted";
	}
}
