package com.my.springcloud.web.controller;

import com.my.springcloud.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * 用户 Ribbon Controller
 *
 * @author Instuition
 * @date 2019/2/1 17:03
 * @since 0.0.1
 */
@RestController
public class UserRibbonController {

    //private final RestTemplate restTemplate;

    // RestTemplate 内部的实现方式
    private final LoadBalancerClient loadBalancerClient;

    private final String serviceProviderName;

    public UserRibbonController(LoadBalancerClient loadBalancerClient,
                                @Value("${service-provider.name}") String serviceProviderName) {
        this.loadBalancerClient = loadBalancerClient;
        this.serviceProviderName = serviceProviderName;
    }

    @GetMapping("")
    public String index() throws IOException {
        User user = new User(44L, "Hamilton");

        // 根据 serviceId (serviceName) 返回 ServiceInstance
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceProviderName);
        // 这里的第三个参数 LoadBalancerRequest 不一定要用 Http 请求，可以自定义其他的请求方式
        String res = loadBalancerClient.execute(serviceProviderName, serviceInstance,
                instance -> {
                    String host = instance.getHost();
                    int port = instance.getPort();
                    String url = "http://" + host + ":" + port + "/user";
                    RestTemplate restTemplate = new RestTemplate();

                    return restTemplate.postForObject(url, user, String.class);
                });
        return res;
    }
}
