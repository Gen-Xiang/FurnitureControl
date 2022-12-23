package com.example.furniturecontrol.service;

import com.example.furniturecontrol.entity.User;
import com.example.furniturecontrol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getUser(){

        return userRepository.findAll();
        //return List.of(new User(1,"chx","12345678","chx@qq.com","A stupid"));
    }
}
