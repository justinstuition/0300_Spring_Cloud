package com.my.springcloud.web.controller.advice;

import com.my.springcloud.web.controller.DemoRestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeoutException;

/**
 * {@link DemoRestController} 类似于 AOP 拦截
 *
 * @author Instuition
 * @date 2019/2/2 22:31
 * @since 0.0.1
 */
@RestControllerAdvice(assignableTypes = DemoRestController.class)
public class DemoRestControllerAdvice {

    @ExceptionHandler(TimeoutException.class)
    public Object faultToleranceTimeout(Throwable throwable) {
        return throwable.getMessage();
    }
}

