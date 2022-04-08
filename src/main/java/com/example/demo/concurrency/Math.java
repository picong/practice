package com.example.demo.concurrency;

public class Math {
    public static final Integer CONSTANT = 666;

    public int compute() { //stack
        int a = 3;
        int b = 5;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }
}

