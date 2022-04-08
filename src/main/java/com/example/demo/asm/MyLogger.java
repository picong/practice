package com.example.demo.asm;

public class MyLogger {
    public static long start = 0;

    public static void start() {
        start = System.currentTimeMillis();
    }

    public static void end() {
        long end = System.currentTimeMillis();
        System.out.println("cost millis = " + (end - start) + "--------------->");
    }
}
