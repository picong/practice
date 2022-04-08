package com.example.demo.beanPostProcessor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Cat implements InitializingBean {

    public Cat() {
        System.out.println("Cat Construct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat afterPropertiesSet");
    }

    @PostConstruct
    public void afterInit() {
        System.out.println("Cat postConstruct");
    }


}
