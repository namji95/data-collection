package com.namji.datacollection.dto.response;

import com.namji.datacollection.entity.Group;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResponse {

  private Long deviceId;
  private String serialNumber;
  private Group group;
  private LocalDateTime createdAt;

}
