package com.my.springcloud.api;

import com.my.springcloud.api.fallback.UserServiceFallback;
import com.my.springcloud.api.fallbackfactory.UserServiceFallbackFactory;
import com.my.springcloud.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户服务
 *
 * @author Instuition
 * @date 2019/2/1 15:30
 * @since 0.0.1
 */
// 指定 UserService 接口的服务提供方
//@FeignClient(name = "${service-provider.name}", fallback = UserServiceFallback.class)
@FeignClient(name = "${service-provider.name}", fallbackFactory = UserServiceFallbackFactory.class)
public interface UserService {

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping("/user")
    boolean saveUser(@RequestBody User user);

    /**
     * 查询所有用户列表
     *
     * @return non-null
     */
    @GetMapping("/users")
    List<User> findAll();

    /**
     * 根据 Id 查询 User
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}") // @PathVariable 指明参数映射
    User getById(@PathVariable("id") Long id);
}
