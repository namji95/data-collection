package com.namji.datacollection.exception;

import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException {

  private final Integer errorCode;
  private final String message;

  protected CustomException(ExceptionStatus e) {
    this.errorCode = e.getErrorCode();
    this.message = getMessage();
  }

}
