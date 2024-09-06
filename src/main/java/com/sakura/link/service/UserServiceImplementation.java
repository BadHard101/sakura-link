package com.sakura.link.service;

import com.sakura.link.config.JwtProvider;
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
    public User followUser(Integer reqUserId, Integer userId2) throws Exception {

        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;
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
        if (user.getGender() != null)
            oldUser.setGender(user.getGender());

        User updatedUser = userRepository.save(oldUser);

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query){

        return userRepository.searchUser(query);
    }

    @Override
    public User findUserJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }
}
