package com.my.springcloud.service;

import com.my.springcloud.api.UserService;
import com.my.springcloud.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存实现用户服务 {@link UserService}
 *
 * @author Instuition
 * @date 2019/2/1 15:36
 * @since 0.0.1
 */
//@Service // 改用 Feign，暂时禁用
public class InMemoryUserService implements UserService {

    private static Map<Long, User> repository = new ConcurrentHashMap<>();

    static {
        User user = new User(44L, "Hamilton");
        repository.put(user.getId(), user);
    }

    @Override
    public boolean saveUser(User user) {
        // put 返回之前的值，如果第一次存，则返回 null
        return repository.put(user.getId(), user) == null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public User getById(Long id) {
        return null;
    }
}
