package com.example.demo.ratelimiter.exception;

/**
 * The exception represents that some internal error occurs.
 * @author cong.pi
 * @date 2022/4/7 16:39
 */
public class InternalErrorException extends Exception {

  private static final long serialVersionUID = 400505098652932743L;

  public InternalErrorException(String message) {
    super(message);
  }

  public InternalErrorException(String message, Throwable cause) {
    super(message, cause);
  }
}
