package com.example.demo.gof.interpret;

import java.util.Map;

/**
 * @author cong.pi
 * @date 2022/3/29 18:13
 */
public class GreaterExpression implements Expression {

  private String key;
  private long value;

  public GreaterExpression(String strExpression) {
    String[] elemets = strExpression.trim().split("\\s+");
    if (elemets.length != 3 || !elemets[1].trim().equals(">")) {
      throw new RuntimeException("Expression is invalid: " + strExpression);
    }
    this.key = elemets[0].trim();
    this.value = Long.parseLong(elemets[2].trim());
  }

  public GreaterExpression(String key, long value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public boolean interpret(Map<String, Long> stats) {
    if (!stats.containsKey(key)) {
      return false;
    }
    long statValue = stats.get(key);
    return statValue > value;
  }
}
