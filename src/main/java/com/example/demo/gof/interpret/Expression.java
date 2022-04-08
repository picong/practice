package com.example.demo.gof.interpret;

import java.util.Map;

/**
 * @author cong.pi
 * @date 2022/3/29 18:13
 */
public interface Expression {

  boolean interpret(Map<String, Long> stats);

}
