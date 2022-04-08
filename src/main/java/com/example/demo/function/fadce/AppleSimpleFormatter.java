package com.example.demo.function.fadce;

import com.example.demo.function.Apple;

public class AppleSimpleFormatter implements AppleFormatter{
 @Override
  public String accept(Apple apple){
    return "An apple of " + apple.getWeight() + "g";
  }
}