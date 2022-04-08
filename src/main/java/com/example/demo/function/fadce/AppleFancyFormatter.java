package com.example.demo.function.fadce;

import com.example.demo.function.Apple;

public class AppleFancyFormatter implements AppleFormatter{
    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy " : "light ";
        return "A " + characteristic + apple.getColor() + "Apple";
    }
}
