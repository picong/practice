package com.example.demo.gcdemo;

import java.util.Date;

public class WeakReferenceTest {

  public static int M = 1024 * 1024;

  public static void printlnMemory(String tag) {
    Runtime runtime = Runtime.getRuntime();
    int M = WeakReferenceTest.M;
    System.out.println("\n" + tag + ":");
    System.out.println(runtime.freeMemory() / M + "M(free)/" + runtime.totalMemory() / M + "M(total)");
  }

//  public static void main(String[] args) {
//    WeakReferenceTest.printlnMemory("1.原可用内存和总内存");
//
//    // new WeakReference
//    WeakReference<Object> weakReference = new WeakReference<>(new byte[10 * WeakReferenceTest.M]);
//    WeakReferenceTest.printlnMemory("2.实例化10M的数组,并建立弱引用");
//    System.out.println("weakRerference.get() : "+weakReference.get());
//
//    System.gc();
//    StrongReferenceTest.printlnMemory("3.GC后");
//    System.out.println("weakRerference.get() : "+weakReference.get());
//  }

  public static void main(String[] args) {
    System.out.println(new Date().getTime());
  }

}
