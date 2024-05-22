package com.namji.datacollection.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionStatus {

  DEVICE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 장치를 찾을 수 없습니다."),
  GROUP_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 그룹을 찾을 수 없습니다.");

  private final Integer errorCode;
  private final String message;
}
