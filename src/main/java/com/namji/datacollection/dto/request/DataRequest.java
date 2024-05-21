package com.namji.datacollection.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataRequest {
  private String serialNumber;
  private int timeInterval;
  private String dataSet;
  private LocalDateTime recordedAt;
}
