package com.example.demo.netty.the.flash.serialize;


import com.example.demo.netty.the.flash.serialize.impl.JSONSerializer;

public interface Serializer {


  Serializer DEFAULT = new JSONSerializer();

  /**
   * 序列化算法
   * @return
   */
  byte getSerializerAlogrithm();

  /**
   * java 对象转换成二进制
   * @param object
   * @return
   */
  byte[] serialize(Object object);

  /**
   * 二进制转换成 java对象
   * @param clzz
   * @param bytes
   * @param <T>
   * @return
   */
  <T> T deserialize(Class<T> clzz, byte[] bytes);
}
