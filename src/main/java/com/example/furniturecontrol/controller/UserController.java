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
    private int loginuid=0;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/logined")
    public User getLoginUser(){
        User user = userService.getUserByUid(loginuid);
        System.out.println(user);
        System.out.println(loginuid);
        return user;
    }

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
        if (username.length()==0){
            return new Result(401);
        }
        else if (password.length()==0){
            return new Result(402);
        }

        User user = userService.getUserByUsername(username);
        if (user==null){
            return new Result(403);
        }
        else if (!Objects.equals(user.getPassword(), password)) {
            String message = "账号密码错误";
            return new Result(404);
        } else {
            loginuid=user.getUid();
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
        if (username.length()==0){
            return new User(0,username,password,email,introduction);
        }
        if (email.length()==0){
            return new User(-4,username,password,email,introduction);
        }

        User user = userService.getUserByUsername(username);
        if (user!=null) {
            return new User(-1,username,password,email,introduction);
        }
        user = userService.getUserByEmail(email);
        if (user!=null) {
            return new User(-2,username,password,email,introduction);
        }
        else if (password.length()<6){
            return new User(-3,username,password,email,introduction);
        }
        else {
            user = userService.addNewUser(new User(username,password,email,introduction));
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
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        String email = requestUser.getEmail();
        String introduction = requestUser.getIntroduction();

        User origin_user = userService.getUserByUid(uid);
        User user = userService.getUserByUsername(username);
        if (user!=null&&!Objects.equals(origin_user.getUsername(), user.getUsername())) {
            return new Result(401);
        }
        user = userService.getUserByEmail(email);
        if (user!=null&&!Objects.equals(origin_user.getEmail(), user.getEmail())) {
            return new Result(402);
        }
        else if (password.length()<6&&password.length()>0){
            return new Result(403);
        }
        else {
            userService.updateUser(uid,username,password,email,introduction);
            return new Result(200);
        }
    }
}
