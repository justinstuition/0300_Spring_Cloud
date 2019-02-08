package com.my.springcloud.api.fallback;

import com.my.springcloud.api.UserService;
import com.my.springcloud.domain.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * {@link UserService} Fallback
 *
 * @author Instuition
 * @date 2019/2/6 0:28
 * @since 0.0.1
 */
@Component // 将 Fallback 添加到 Spring 容器中
public class UserServiceFallback implements UserService {
    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return Collections.singletonList(new User(-1L, "Timeout"));
    }

    @Override
    public User getById(Long id) {
        return new User(id, "No User with Id " + id);
    }
}
