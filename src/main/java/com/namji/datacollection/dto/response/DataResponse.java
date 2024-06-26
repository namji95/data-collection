package com.namji.datacollection.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataResponse {

  private int dataInfo;
  private LocalDateTime recordedAt;

}
