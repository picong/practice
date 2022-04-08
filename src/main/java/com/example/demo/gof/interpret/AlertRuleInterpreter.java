package com.example.demo.gof.interpret;

import java.util.Map;

/**
 * @author cong.pi
 * @date 2022/3/29 18:31
 */
public class AlertRuleInterpreter {

  private Expression expression;

  public AlertRuleInterpreter(String ruleExpression) {
    this.expression = new OrExpression(ruleExpression);
  }

  public boolean interpret(Map<String, Long> stats) {
    return expression.interpret(stats);
  }

}
