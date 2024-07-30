package com.namji.datacollection.dto.response;

import com.namji.datacollection.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResponse {

  private Long deviceId;
  private String serialNumber;
  private Group stationGroup;
  private LocalDateTime createAt;

  public DeviceResponse(Long deviceId, String serialNumber, Group group) {
    this.deviceId = deviceId;
    this.serialNumber = serialNumber;
    this.stationGroup = group;
  }
}
