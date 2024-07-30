package com.namji.datacollection.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {

  private Long stationGroupId;
  private String serialNumber;
  private LocalDateTime createdAt;

}
