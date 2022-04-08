package com.example.demo.function.stream;

import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
//        List<Dish> menu = Arrays.asList(
//                new Dish("pork", false, 800, Dish.Type.MEAT),
//                new Dish("beef", false, 700, Dish.Type.MEAT),
//                new Dish("chicken", false, 400, Dish.Type.MEAT),
//                new Dish("french fries", true, 530, Dish.Type.OTHER),
//                new Dish("rice", true, 350, Dish.Type.OTHER),
//                new Dish("season fruit", true, 120, Dish.Type.OTHER),
//                new Dish("pizza", true, 550, Dish.Type.OTHER),
//                new Dish("prawns", false, 300, Dish.Type.FISH),
//                new Dish("salmon", false, 450, Dish.Type.FISH) );
//
//
//        String[] arr = {"ren", "churn"};
//
//        String s = Arrays.stream(arr).map(w -> w.split(""))
//                .flatMap(Arrays::stream)
//                .distinct()
//                .collect(Collectors.joining());
//        System.out.println(s);

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .parallel()
                .reduce((a, b) -> {
                    System.out.println(String.format("%s: %d + %d = %d",
                            Thread.currentThread().getName(), a, b, a + b));
                    return a + b;
                }).ifPresent(System.out::println);
    }
}
