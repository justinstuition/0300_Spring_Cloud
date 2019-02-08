package com.my.springcloud.web.hystrix;

import com.my.springcloud.domain.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * User Ribbon Client HystrixCommand
 *
 * @author Instuition
 * @date 2019/2/3 12:36
 * @since 0.0.1
 */
public class UserRibbonClientHystrixCommand extends HystrixCommand<List<User>> {

    private final String serviceProviderName;

    private final RestTemplate restTemplate;

    public UserRibbonClientHystrixCommand(String serviceProviderName, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("user-ribbon-client"));
        this.serviceProviderName = serviceProviderName;
        this.restTemplate = restTemplate;
    }

    /**
     * 主要业务逻辑
     *
     * @return
     * @throws Exception
     */
    @Override
    protected List<User> run() throws Exception {
        return restTemplate.getForObject("http://" + serviceProviderName + "/users", List.class);
    }

    /**
     * FallBack 实现
     *
     * @return 空集合
     */
    @Override
    protected List<User> getFallback() {
        return Arrays.asList(new User(22L, "A"));
    }
}
