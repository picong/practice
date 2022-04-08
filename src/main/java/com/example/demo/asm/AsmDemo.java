package com.example.demo.asm;


import java.util.concurrent.TimeUnit;

public class AsmDemo {

    public void method1() {
        System.out.println("now in method1 ----->");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AsmDemo asmDemo = new AsmDemo();
        System.out.println(asmDemo.getClass().getClassLoader());
        System.out.println(asmDemo.getClass().getClassLoader().getParent());
        System.out.println(asmDemo.getClass().getClassLoader().getParent().getParent());
    }
}
