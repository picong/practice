//package com.example.demo.hessian;
//
//import com.caucho.hessian.io.Hessian2Input;
//import com.caucho.hessian.io.Hessian2Output;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//
//public class HessainDemo {
//
//  public static void main(String[] args) throws IOException {
//    Student student = new Student();
//    student.setAge(101);
//    student.setName("HESSIAN");
//    System.out.println("student = " + student);
//
//    // 把student对象转为byte数组
//    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//    Hessian2Output output = new Hessian2Output(bos);
//    output.writeObject(student);
//    output.flushBuffer();
//    byte[] data = bos.toByteArray();
//    bos.close();
//
//    // 把刚才序列化出来的byte数组转化为student对象
//    ByteArrayInputStream bis = new ByteArrayInputStream(data);
//    Hessian2Input input = new Hessian2Input(bis);
//    Student deStudent = (Student) input.readObject();
//    System.out.println(deStudent);
//  }
//
//}
