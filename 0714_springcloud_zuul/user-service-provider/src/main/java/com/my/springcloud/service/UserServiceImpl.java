package com.my.springcloud.service;

import com.my.springcloud.api.UserService;
import com.my.springcloud.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实现用户服务 {@link UserService}
 *
 * @author Instuition
 * @date 2019/2/1 15:36
 * @since 0.0.1
 */
//@Service
@RestController // 这里是 @RestController，需要暴露访问接口给 Feign，不是 @Service
public class UserServiceImpl implements UserService {

    private static Map<Long, User> repository = new ConcurrentHashMap<>();

    static {
        User user = new User(44L, "Hamilton");
        repository.put(user.getId(), user);
    }

    @Override
    public boolean saveUser(@RequestBody User user) {
        // put 返回之前的值，如果第一次存，则返回 null
        return repository.put(user.getId(), user) == null;
    }

    @Override
    public List<User> findAll() {
        executeTime();
        return new ArrayList<>(repository.values());
    }

    @Override
    public User getById(@PathVariable("id") Long id) {
        executeTime();
        Optional<User> optionalUser = Optional.ofNullable(repository.get(id));
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("没有该用户");
            //return null;
        }
    }

    /**
     * 模拟执行超时
     */
    private void executeTime() {
        int executeTime = new Random().nextInt(200);
        System.out.println(executeTime);
        try {
            Thread.sleep(executeTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
