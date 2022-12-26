package com.example.furniturecontrol.controller;


import com.example.furniturecontrol.entity.User;
import com.example.furniturecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUser(){

        return userService.getUser();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") int uid){
        userService.deleteUser(uid);
    }

    @PutMapping(path="{userId}")
    public void updateUser(@PathVariable("userId") int uid,@RequestParam(required = false) String email,@RequestParam(required = false) String introduction){
        userService.updateUser(uid,email,introduction);
    }
}
