package com.namji.datacollection.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataResponse {

  private Long dataId;
  private String serialNumber;
  private List<String> dataList;
}
