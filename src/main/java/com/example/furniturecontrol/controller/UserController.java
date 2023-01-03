package com.example.furniturecontrol.controller;


import com.example.furniturecontrol.entity.User;
import com.example.furniturecontrol.result.Result;
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
    public List<User> getUsers(){
        return userService.getUser();
    }

//    @PostMapping
//    public void registerNewUser(@RequestBody User user){
//        userService.addNewUser(user);
//    }

    @PostMapping
    public User getUser(@RequestBody User requestUser){
        System.out.println(requestUser);
        User result = userService.getUserByUsername(requestUser.getUsername());
        System.out.println(result);
        return result;
    }

    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") int uid){
        userService.deleteUser(uid);
    }

    @PutMapping(path="{userId}")
    public Result updateUser(@PathVariable("userId") int uid, @RequestBody User requestUser){
        //@RequestParam(required = false) String username,@RequestParam(required = false) String password,@RequestParam(required = false) String email,@RequestParam(required = false) String introduction
        System.out.println(requestUser);
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        String email = requestUser.getEmail();
        String introduction = requestUser.getIntroduction();
        userService.updateUser(uid,username,password,email,introduction);
        System.out.println("Success");
        return new Result(200);
    }
}
