package com.my.springcloud.api;

import com.my.springcloud.domain.User;

import java.util.List;

/**
 * 用户服务
 *
 * @author Instuition
 * @date 2019/2/1 15:30
 * @since 0.0.1
 */
public interface UserService {

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    boolean saveUser(User user);

    /**
     * 查询所有用户列表
     *
     * @return non-null
     */
    List<User> findAll();
}
