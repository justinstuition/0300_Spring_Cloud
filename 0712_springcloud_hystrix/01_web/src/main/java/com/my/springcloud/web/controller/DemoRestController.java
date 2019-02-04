package com.my.springcloud.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 *
 *
 * @author Instuition
 * @date 2019/2/2 22:16
 * @since 0.0.1
 */
@RestController
public class DemoRestController {

    private static final Random TIME_OUT = new Random();

    /**
     * 当执行时间超过 100ms 时，抛出异常
     *
     * @return
     * @throws TimeoutException
     */
    @GetMapping("")
    public String index() throws TimeoutException {

        long executeTime = TIME_OUT.nextInt(200);
        if (executeTime > 100) {
            throw new TimeoutException("Timeout : " + executeTime);
        }

        return "Hellow : " + executeTime;
    }
}
