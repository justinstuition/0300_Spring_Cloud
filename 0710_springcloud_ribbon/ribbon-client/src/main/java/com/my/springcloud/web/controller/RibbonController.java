package com.my.springcloud.web.controller;

import com.my.springcloud.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon Client {@link RestController}
 *
 * @author Instuition
 * @date 2019/1/31 14:46
 */
@RestController
public class RibbonController {
    private final RestTemplate restTemplate;

    private final String serviceProviderHost;

    private final Integer serviceProviderPort;

    private final String serviceProviderName;

    public RibbonController(RestTemplate restTemplate,
                            @Value("${service-provider.host}") String serviceProviderHost,
                            @Value("${service-provider.port}") Integer serviceProviderPort,
                            @Value("${service-provider.name}") String serviceProviderName) {
        this.restTemplate = restTemplate;
        this.serviceProviderHost = serviceProviderHost;
        this.serviceProviderPort = serviceProviderPort;
        this.serviceProviderName = serviceProviderName;
    }

    @GetMapping("")
    public String index() {
        User user = new User(1, "Hamilton");

        // Method 1: 直接指定服务提供方的 IP
        //String serviceProviderUrl = "http://" + serviceProviderHost + ":" + serviceProviderPort;
        //return restTemplate.postForObject(serviceProviderUrl + "/greeting", user, String.class);

        // Method 2: 指定服务提供方的服务名称，RestTemplate 通过拦截器将其转换为相应服务的 IP 地址
        return restTemplate.postForObject("http://" + serviceProviderName + "/greeting", user, String.class);
    }
}
