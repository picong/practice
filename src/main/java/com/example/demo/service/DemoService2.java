package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService2 {


//  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
  public String test(String word) {
    System.out.println("sayHi run 1 ...");
    System.out.println("sayHi run 2 ...");
    return "hello " + word;
  }

}
