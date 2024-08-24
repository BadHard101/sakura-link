package com.sakura.link.service;

import com.sakura.link.models.User;
import com.sakura.link.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user){
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return user.get();
        }

        throw new Exception("user not exist with userId " + userId);
    }

    @Override
    public User findUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {

        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new Exception("user not exist with id " + userId);
        }

        User oldUser = userOptional.get();

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

    @Override
    public List<User> searchUser(String query){

        return userRepository.searchUser(query);
    }
}
