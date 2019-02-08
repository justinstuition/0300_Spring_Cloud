package com.my.springcloud;

import com.my.springcloud.api.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


//@RibbonClients(
//        @RibbonClient("${service-provider.name}")
//)
// 申明 UserService 接口作为 FeignClient 调用，代替 RestTemplate 实现调用
@EnableFeignClients("com.my.springcloud.api")
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceClientApplication.class, args);
    }

//    @Bean // 启用 Feign 后，暂时禁用
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
}

