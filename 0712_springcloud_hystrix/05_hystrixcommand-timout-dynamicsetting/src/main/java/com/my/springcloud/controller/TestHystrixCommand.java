package com.my.springcloud.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.Random;

/**
 * Timeout Dynamic Setting
 *
 * @author Instuition
 * @date 2019/2/4 16:49
 * @since 0.0.1
 */
public class TestHystrixCommand extends HystrixCommand<String> {

    private static final Random RANDOM_TIME = new Random();

    // 方式一
    public TestHystrixCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("TestGroupKey"));
    }

    // 方式二：
    // 1. 自定义 commandKey
    // 2. 通过自定义的属性指定 timeout
    public TestHystrixCommand(Integer timeout) {
        // 参数 com.netflix.hystrix.HystrixCommand.Setter
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TestGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("TestCommandKey" + timeout))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(timeout)));
    }

    @Override
    protected String run() throws Exception {
        int executeTime = RANDOM_TIME.nextInt(200);
        System.out.println(executeTime);

        Thread.sleep(executeTime);

        return "Hello";
    }

    @Override
    protected String getFallback() {
        return "Fallback";
    }
}
