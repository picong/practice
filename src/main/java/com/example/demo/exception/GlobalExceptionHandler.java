package com.example.demo.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {



  @ExceptionHandler(ArithmeticException.class)
  public void resolve(ArithmeticException e) {
    log.error("divide by zero", e);
  }

}
