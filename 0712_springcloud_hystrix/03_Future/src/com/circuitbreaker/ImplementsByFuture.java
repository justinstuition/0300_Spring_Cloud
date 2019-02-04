package com.circuitbreaker;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 通过{@link Future} 实现服务短路
 *
 * @author Instuition
 * @date 2019/2/3 23:21
 * @since 0.0.1
 */
public class ImplementsByFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 初始化线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Command<String> command = new RandomCommand();

        Future<String> future = executorService.submit(() -> command.run());
        //Future<String> future1 = executorService.submit(command::run);

        String result = null;

        try {
            // 超时时间 100ms，报异常
            result = future.get(100, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            // 超时后，调用 fallBack 方法
            result = command.fallBack();
        } finally {
            executorService.shutdown();
        }

        System.out.println(result);
    }

    private static final Random RANDOM_TIME = new Random();

    public static class RandomCommand implements Command<String> {

        @Override
        public String run() throws InterruptedException {
            int executeTime = RANDOM_TIME.nextInt(200);
            System.out.println(executeTime);

            // 通过休眠来模拟执行时间
            Thread.sleep(executeTime);

            return "Hello World";
        }

        @Override
        public String fallBack() {
            return "fallback";
        }
    }

    public interface Command<T> {

        /**
         * 正常执行
         *
         * @return
         * @throws InterruptedException
         */
        T run() throws InterruptedException;

        /**
         * 发生异常时，返回容错结果
         *
         * @return
         */
        T fallBack();
    }
}
