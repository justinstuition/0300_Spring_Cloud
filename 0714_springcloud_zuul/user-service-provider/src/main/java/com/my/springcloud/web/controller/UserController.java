package com.my.springcloud.web.controller;

import com.my.springcloud.api.UserService;
import com.my.springcloud.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 用户控制模块
 *
 * @author Instuition
 * @date 2019/2/1 20:18
 * @since 0.0.1
 */
//@RestController // 改用 Feign，暂时禁用
public class UserController {

    private final UserService userService;

    private static final Random RANDOM_TIME = new Random();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * 获取所有用户列表
     *
     * @return
     * @throws InterruptedException
     */
    @HystrixCommand(
        // 设置 FallBack 方法
        fallbackMethod = "findAllFallBack",
        // 设置超时时间 100ms
        commandProperties = {
            // https://github.com/Netflix/Hystrix/wiki/Configuration
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
        }
    )
    @GetMapping("/users")
    public List<User> findAll() throws InterruptedException {

        int executeTime = RANDOM_TIME.nextInt(200);
        System.out.println("executeTime = " + executeTime);
        // 模拟执行时间
        Thread.sleep(executeTime);

        return userService.findAll();
    }

    /**
     * {@link #findAll()} 的 FallBack
     *
     * @return
     */
    public List<User> findAllFallBack() {
        return Collections.emptyList();
    }
}
