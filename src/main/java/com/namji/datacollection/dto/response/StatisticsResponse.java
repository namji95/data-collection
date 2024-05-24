package com.namji.datacollection.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatisticsResponse {

  private Long deviceId;
  private String serialNumber;
  private Double averageData;

}
