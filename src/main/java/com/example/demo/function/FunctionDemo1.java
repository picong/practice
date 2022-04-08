package com.example.demo.function;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.comparing;

@Slf4j
public class FunctionDemo1 {

    public static void main(String[] args) throws IOException {

        String reuslt = processFile(br -> br.readLine() + "\r\n" + br.readLine());
//        System.out.println(reuslt);

        List<String> str = Arrays.asList("a", "b", "A", "B");
        str.sort(String::compareToIgnoreCase);
//        str.forEach(System.out::println);

        List<Apple> inventory = new ArrayList<Apple>() {
            {
                add(new Apple("green", 10));
                add(new Apple("blue", 11));
                add(new Apple("yellow", 8));
            }
        };
        log.info("排序前: {}", JSON.toJSONString(inventory, true));
        // 根据重量倒序排
        inventory.sort(comparing(Apple::getWeight).reversed());
        log.info("排序后: {}", JSON.toJSONString(inventory, true));

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
//        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> h = f.compose(g);
        Integer result = h.apply(1);
        log.info("最终输出的结果为: {}", result);
    }

    public static String processFile(BufferReaderProcessor bufferReaderProcessor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\code\\demo\\src\\main\\java\\com\\example\\demo\\function\\FunctionDemo.java"))) {
            return bufferReaderProcessor.processor(br);
        }
    }
}
