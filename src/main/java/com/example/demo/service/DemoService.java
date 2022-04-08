package com.example.demo.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DemoService implements IService {

  @Resource
  private DemoService2 demoService2;

//  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  @Override
  public String sayHi(String word) {
    demoService2.test(word);
    return "hello " + word;
  }

}
