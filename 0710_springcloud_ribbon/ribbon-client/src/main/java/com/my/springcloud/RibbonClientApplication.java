package com.my.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@RibbonClients({
//        @RibbonClient(name = "spring-cloud-service-provider")
//})
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonClientApplication.class, args);
    }

    @LoadBalanced // 内部 LoadBalancerInterceptor 负责将服务名称转换为 IP 地址
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

