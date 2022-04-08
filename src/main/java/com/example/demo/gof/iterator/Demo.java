package com.example.demo.gof.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author cong.pi
 * @date 2022/3/18 15:37
 */
public class Demo {

  public static void main(String[] args) {
//    demo();
    demo1();
  }

  private static void demo() {
    List<String> names = new ArrayList<>();
    names.add("a");
    names.add("b");
    names.add("c");
    names.add("d");

    Iterator<String> iterator1 = names.iterator();
    Iterator<String> iterator2 = names.iterator();

    iterator1.next();
    iterator1.remove();
    iterator2.next();
  }

  static void demo1() {
    List<Integer> list = new ArrayList<Integer>(){{add(2);add(3);add(8);}};

    Iterator<Integer> iter1 = list.iterator(); // snapshot 2, 3, 8
    list.remove(new Integer(2)); // list: 3, 8
    Iterator<Integer> iter2 = list.iterator(); // snapshot  3, 8
    list.remove(new Integer(8)); // list: 3
    Iterator<Integer> iter3 = list.iterator(); // snapshot  3

    // 输出 2 3 8
    while (iter1.hasNext()) {
      System.out.print(iter1.next() + " ");
    }

//    while (iter2.hasNext()) {
//      System.out.print(iter2.next() + " ");
//    }
//
//    while (iter3.hasNext()) {
//      System.out.println(iter3.next() + " ");
//    }

  }

}
