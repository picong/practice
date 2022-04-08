package com.example.demo.concurrency;

public class Demo2 {

    private volatile long count = 0;

    public void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Demo2 demo2 = new Demo2();

        Thread threadA = new Thread(() -> demo2.add10K());
        Thread threadB = new Thread(() -> demo2.add10K());
        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println(demo2.count);
    }

}
