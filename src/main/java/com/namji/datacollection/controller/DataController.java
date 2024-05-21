package com.namji.datacollection.controller;

import com.namji.datacollection.dto.request.DataRequest;
import com.namji.datacollection.dto.response.CommonResponse;
import com.namji.datacollection.dto.response.DataResponse;
import com.namji.datacollection.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DataController {

  private final DataService dataService;

  @PostMapping("/data-info")
  public ResponseEntity<CommonResponse<DataResponse>> createData (
      @RequestBody DataRequest request) {
    DataResponse response = dataService.createData(request);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<DataResponse>builder()
            .message("success")
            .data(response)
            .build());
  }

  /*
  todo :: 데이터 등록부터 시작
   */
}
