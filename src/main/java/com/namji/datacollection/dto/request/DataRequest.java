package com.namji.datacollection.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DataRequest {
  private String serialNumber;
  private int interval;
  private String dataSet;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime recordedAt;
}
