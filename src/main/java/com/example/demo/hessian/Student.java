package com.example.demo.hessian;

import java.io.Serializable;
import lombok.Data;

@Data
public class Student implements Serializable {

  private int age;

  private String name;
}
