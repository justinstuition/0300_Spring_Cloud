package com.my.springcloud.api.fallbackfactory;

import com.my.springcloud.api.UserService;
import com.my.springcloud.domain.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Instuition
 * @date 2019/2/6 2:51
 * @since 0.0.1
 */
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserService create(Throwable throwable) {
        logger.info(throwable.toString());
        return new UserService() {
            @Override
            public boolean saveUser(User user) {
                return false;
            }

            @Override
            public List<User> findAll() {
                return null;
            }

            @Override
            public User getById(Long id) {
                // If one needs access to the cause that made the fallback trigger
                // 相较于 Fallback，FallbackFactory 可以得到熔断的原因
                // 注意：这里直接 throwable.toString()，不是 throwable.getMessage()
                return new User(id, throwable.toString());
            }
        };
    }
}
