package com.example.demo.function;
import com.example.demo.function.fadce.AppleFormatter;
import com.example.demo.function.fadce.AppleSimpleFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FunctionDemo {

    public static void main(String[] args) {
        // 尝试5次，如果有一次成功就输出第一次的结果
//        Stream.generate(() -> Math.random() > 0.8 ? "ok" : null)
//                .limit(5)
//                .filter(Objects::nonNull)
//                .findFirst()
//                .ifPresent(System.out::println);
        List<Apple> inventory = new ArrayList<Apple>(){{
            add(new Apple("green", 160));
            add(new Apple("red", 180));
            add(new Apple("yellow", 91));
        }};
        prettyPrintApple(inventory, new AppleSimpleFormatter());

        Comparator<Apple> comparator = (a1, a2) -> a1.getWeight() - a2.getWeight();
        inventory.sort(comparator);
        inventory.forEach(a -> System.out.println(a.getColor() + " " + a.getWeight()));
    }

    public static void prettyPrintApple(List<Apple> inventory,
                                        AppleFormatter formatter){
        for(Apple apple: inventory){
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }
}
