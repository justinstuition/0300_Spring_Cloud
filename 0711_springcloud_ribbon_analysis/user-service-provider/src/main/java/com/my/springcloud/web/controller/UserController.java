package com.my.springcloud.web.controller;

import com.my.springcloud.api.UserService;
import com.my.springcloud.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制模块
 *
 * @author Instuition
 * @date 2019/2/1 20:18
 * @since 0.0.1
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
