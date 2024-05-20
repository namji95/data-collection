package com.namji.datacollection.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {

  private Long groupId;
  private String groupSerial;
  private LocalDateTime createdAt;

}
