package com.namji.datacollection.controller;

import com.namji.datacollection.dto.request.DataRequest;
import com.namji.datacollection.dto.request.StatisticsRequest;
import com.namji.datacollection.dto.response.CommonResponse;
import com.namji.datacollection.dto.response.DataResponse;
import com.namji.datacollection.dto.response.StatisticsResponse;
import com.namji.datacollection.service.DataService;
import com.namji.datacollection.service.DataServiceImpl;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.InterpreterRuleContext;
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
  @GetMapping("/device/data-statistics")
  public ResponseEntity<CommonResponse<StatisticsResponse>> deviceStatisticsData (
      @RequestBody StatisticsRequest request) {
    StatisticsResponse response = dataService.deviceStatisticsData(request);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<StatisticsResponse>builder()
            .message("success")
            .data(response)
            .build());
  }

  // 특정 그룹 데이터 통계
  @GetMapping("/group/data-statistics")
  public ResponseEntity<CommonResponse<List<StatisticsResponse>>> groupStatisticsData (
      @RequestBody StatisticsRequest request) {
    List<StatisticsResponse> responseList = dataService.groupStatisticsData(request);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<List<StatisticsResponse>>builder()
            .message("success")
            .data(responseList)
            .build());
  }
}
