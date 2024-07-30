package com.namji.datacollection.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRequest {

  private String serialNumber;
  private String stationGroupSerial;

}
