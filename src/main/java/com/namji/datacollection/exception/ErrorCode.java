package com.namji.datacollection.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  SUCCESS(HttpStatus.OK, "OK"),

  DUPLICATE_DEVICE(HttpStatus.BAD_REQUEST, "동일한 장치가 존재합니다."),
  DUPLICATE_GROUP(HttpStatus.BAD_REQUEST, "동일한 그룹이 존재합니다.");

  private final HttpStatus httpStatus;
  private final String message;
}
