package com.namji.datacollection.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DataRequest {
  private String serialNumber;
  private int interval;
  private String dataSet;
  private String recordedAt;
}
