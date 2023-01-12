package com.example.furniturecontrol.service;

import com.example.furniturecontrol.entity.User;
import com.example.furniturecontrol.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getUsers(){

        return userRepository.findAll();
    }

    public User getUserByUid(int uid){
        Optional<User> userOptional = userRepository.findById(uid);
        return userOptional.orElse(null);
    }

    public User getUserByUsername(String username){
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        return userOptional.orElse(null);
    }

    public User getUserByEmail(String email){
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        return userOptional.orElse(null);
    }

    public User addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByUsername(user.getUsername());
        if (userOptional.isPresent()){
            throw new IllegalStateException("username taken");
        }
        userRepository.save(user);
        userOptional = userRepository.findUserByUsername(user.getUsername());
        return userOptional.orElse(null);
    }

    public void deleteUser(int uid) {
        boolean exists = userRepository.existsById(uid);
        if (!exists){
            throw new IllegalStateException("user with id "+ uid + " doesn't exist");
        }
        userRepository.deleteById(uid);
    }

    @Transactional
    public void updateUser(int uid, String username, String password, String email, String introduction) {
//        boolean exists = userRepository.existsById(uid);
//        if (!exists){
//            throw new IllegalStateException("user with id "+ uid + " doesn't exist");
//        }

        Optional<User> userOptional = userRepository.findById(uid);
        User user;
        if (userOptional.isPresent()){
            user = userOptional.get();
        }
        else{
            throw new IllegalStateException("user with id "+ uid + " doesn't exist");
        }

        if (username!=null&&username.length()>0&&!Objects.equals(user.getUsername(),username)){
            user.setUsername(username);
        }

        if (password!=null&&password.length()>0&&!Objects.equals(user.getPassword(),password)){
            user.setPassword(password);
        }

        if (email!=null&&email.length()>0&&!Objects.equals(user.getEmail(),email)){
            user.setEmail(email);
        }

        if (introduction!=null&&introduction.length()>0&&!Objects.equals(user.getIntroduction(),introduction)){
            user.setIntroduction(introduction);
        }
    }
}
