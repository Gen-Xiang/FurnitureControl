package com.example.furniturecontrol.controller;

import com.example.furniturecontrol.entity.User;
import com.example.furniturecontrol.result.Result;
import com.example.furniturecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

@RestController
@RequestMapping(path="api/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping
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
}
