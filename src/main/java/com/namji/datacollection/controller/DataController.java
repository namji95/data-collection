package com.namji.datacollection.controller;

import com.namji.datacollection.dto.request.DataRequest;
import com.namji.datacollection.dto.request.StatisticsRequest;
import com.namji.datacollection.dto.response.CommonResponse;
import com.namji.datacollection.dto.response.DataResponse;
import com.namji.datacollection.dto.response.StatisticsResponse;
import com.namji.datacollection.entity.Data;
import com.namji.datacollection.service.DataService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DataController {

  private final DataService dataService;

  // 데이터 등록
  @PostMapping("/data-info")
  public ResponseEntity<CommonResponse<List<DataResponse>>> createData (
      @RequestBody DataRequest request) {
    List<DataResponse> responses = dataService.createData(request);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<List<DataResponse>>builder()
            .message("success")
            .data(responses)
            .build());
  }

  // 특정 장치 데이터 통계
  @GetMapping("/data-statistics")
  public ResponseEntity<CommonResponse<StatisticsResponse>> statisticsData (
      @RequestBody StatisticsRequest request) {
    StatisticsResponse response = dataService.statisticsData(request);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<StatisticsResponse>builder()
            .message("success")
            .data(response)
            .build());
  }
}
