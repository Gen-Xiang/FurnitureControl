package com.example.furniturecontrol.controller;


import com.example.furniturecontrol.entity.User;
import com.example.furniturecontrol.result.Result;
import com.example.furniturecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Objects;

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

    @CrossOrigin
    @PostMapping(path = "/login")
    public Result login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        String password = requestUser.getPassword();
        password = HtmlUtils.htmlEscape(password);

        User user = userService.getUserByUsername(username);

        if (user==null || !Objects.equals(user.getPassword(), password)) {
            String message = "账号密码错误";
            System.out.println("Fail");
            return new Result(400);
        } else {
            System.out.println("Success");
            return new Result(200);
        }
    }

    @CrossOrigin
    @PostMapping(path = "/register")
    public User register(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        String password = requestUser.getPassword();
        password = HtmlUtils.htmlEscape(password);
        String email = requestUser.getEmail();
        email = HtmlUtils.htmlEscape(email);
        String introduction = requestUser.getIntroduction();
        introduction = HtmlUtils.htmlEscape(introduction);

        User user = userService.getUserByUsername(username);

        if (user!=null) {
            String message = "账号已注册";
            System.out.println("Fail");
            return new User(-1,username,password,email,introduction);
        } else {
            user = userService.addNewUser(new User(username,password,email,introduction));
            System.out.println("Success");
            return user;
        }
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
