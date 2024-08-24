package com.sakura.link.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
	public User getUserById(@PathVariable("userId") Integer id) throws Exception {
		Optional<User> user = userRepository.findById(id);

		if(user.isPresent()) {
			return user.get();
		}

		throw new Exception("user not exist with userId " + id);
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

	@PutMapping("/users/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {

		Optional<User> user1 = userRepository.findById(userId);

		if (user1.isEmpty()) {
			throw new Exception("user not exist with id " + userId);
		}

		User oldUser = user1.get();

		if (user.getEmail() != null)
			oldUser.setEmail(user.getEmail());
		if (user.getFirstName() != null)
			oldUser.setFirstName(user.getFirstName());
		if (user.getLastName() != null)
			oldUser.setLastName(user.getLastName());
		if (user.getPassword() != null)
			oldUser.setPassword(user.getPassword());

		User updatedUser = userRepository.save(oldUser);

		return updatedUser;
	}

	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) throws Exception {
		Optional<User> user1 = userRepository.findById(userId);

		if (user1.isEmpty()) {
			throw new Exception("user not exist with id " + userId);
		}

		userRepository.delete(user1.get());

		return "User with id " + userId + " has been deleted";
	}
}
