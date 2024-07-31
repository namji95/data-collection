package com.namji.datacollection.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataStatisticsResponse {
  private Long DeviceId;
  private String serialNumber;
  private Double DeviceStatistics;
}
