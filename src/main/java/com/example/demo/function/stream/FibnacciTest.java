package com.example.demo.function.stream;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 只是为了演示fork/join
 */
public class FibnacciTest {

    static class Fibonacci extends RecursiveTask<Integer> {
        int n;
        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            // 先假设n 大于0
            if (n <= 1) {
                return n;
            } else {
                Fibonacci f1 = new Fibonacci(n - 1);
                f1.fork();

                Fibonacci f2 = new Fibonacci(n - 2);
                f2.fork();

                return f1.join() + f2.join();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("Cpu核数：" + Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        Fibonacci fibonacci = new Fibonacci(40);
        ForkJoinTask<Integer> future = forkJoinPool.submit(fibonacci);
        System.out.println(future.get());
        long end = System.currentTimeMillis();
        System.out.println(String.format("耗时 : %d millis", end - start));
    }

}
