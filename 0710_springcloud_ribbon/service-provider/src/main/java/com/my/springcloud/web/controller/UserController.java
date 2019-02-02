package com.my.springcloud.web.controller;

import com.my.springcloud.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务提供者 {@link RestController}
 *
 * @author Instuition
 * @date 2019/1/31 9:36
 */
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${server.port}")
    private Integer port;

    @PostMapping("/greeting")
    public String greeting(@RequestBody User user) {
        logger.info(port.toString());
        return "Hello, " + user + " " + port;
    }
}
