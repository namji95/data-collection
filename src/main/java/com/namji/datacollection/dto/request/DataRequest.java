package com.namji.datacollection.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataRequest {
  private String serialNumber;
  private int timeInterval;
  private String dataSet;
  private LocalDate recordedAt;
}
