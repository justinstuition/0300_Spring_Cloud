package com.my.springcloud.web.controller;

import com.my.springcloud.api.UserService;
import com.my.springcloud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Instuition
 * @date 2019/2/5 21:39
 * @since 0.0.1
 */
@RestController
public class UserServiceClientController {

    @Autowired
    private UserService userService;

//    @Autowired
//    public UserServiceClientController(UserService userService) {
//        this.userService = userService;
//    }

    @PostMapping("/user")
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }
}
