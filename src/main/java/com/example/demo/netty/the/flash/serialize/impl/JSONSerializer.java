package com.example.demo.netty.the.flash.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.netty.the.flash.serialize.Serializer;
import com.example.demo.netty.the.flash.serialize.SerializerAlogrithm;

public class JSONSerializer implements Serializer {

  @Override
  public byte getSerializerAlogrithm() {
    return SerializerAlogrithm.JSON;
  }

  @Override
  public byte[] serialize(Object object) {
    return JSON.toJSONBytes(object);
  }

  @Override
  public <T> T deserialize(Class<T> clzz, byte[] bytes) {
    return JSON.parseObject(bytes, clzz);
  }
}
