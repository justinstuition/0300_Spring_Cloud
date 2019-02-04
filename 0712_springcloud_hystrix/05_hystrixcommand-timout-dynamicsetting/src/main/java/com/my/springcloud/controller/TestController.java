package com.my.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Instuition
 * @date 2019/2/4 16:46
 * @since 0.0.1
 */
@RestController
@RefreshScope
public class TestController {

    // 方式二
    // 注意： 远程修改后，还需 /refresh 下才能生效
    @Value("${hystrix.command.timeout}")
    private Integer timeout;

    @GetMapping("")
    public String index() {
        // 方式一： 不需任何参数
        return new TestHystrixCommand().execute();

        // 方式二： 指定 Timeout
        //return new TestHystrixCommand(timeout).execute();
    }
}
