package com.example.demo.controller;

import com.example.demo.service.IService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/")
public class DemoController {

  @Resource
  private IService service;

  @GetMapping("sayHi")
  public String sayHi() {
    return "Hi!";
  }

  @GetMapping("test")
  public String test() {
    return service.sayHi("world!");
  }

}
