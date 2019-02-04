package com.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.Scheduler;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * @author Instuition
 * @date 2019/2/4 9:54
 * @since 0.0.1
 */
public class RxTest {

    @Test
    public void single() {

        Single.just("Hello") // 仅能发布单个数据
                //.subscribeOn(Schedulers.immediate())
                .subscribeOn(Schedulers.io())
                .subscribe(RxTest::println); // 订阅并且消费数据
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void observable() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Observable.from(list) // 发布多个数据
                    .subscribeOn(Schedulers.trampoline())
                    .subscribe(RxTest::println);
    }

    /**
     * 在 03_Future 中，对于
     *      future.get(100, TimeUnit.MILLISECONDS);
     * 我们通过 try..catch.. 捕获超时异常并处理，
     * 这里我们运用 Reactive 统一的编程模型来处理正常情况和异常情况
     */
    @Test
    public void standardReactive() throws InterruptedException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Observable.from(list) // 发布多个数据
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        // 1. 消费数据
                        value -> {
                            if (value > 5) {
                                throw new IllegalStateException("不能大于5");
                            }
                            println(value);
                        },
                        // 2. 异常发生，中断执行
                        e -> println("异常:" + e.getMessage()),
                        // 3. 全部成功执行
                        () -> println("成功完成"));
        Thread.sleep(1000);
    }

    private static void println(Object value) {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.printf("[线程： %s] 数据： %s\n", Thread.currentThread().getName(), value);
    }
}
