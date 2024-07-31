package com.namji.datacollection.controller;

import com.namji.datacollection.common.CommonResponse;
import com.namji.datacollection.dto.request.DataStatisticsRequest;
import com.namji.datacollection.dto.request.DeviceRequest;
import com.namji.datacollection.dto.response.DataStatisticsResponse;
import com.namji.datacollection.dto.response.DeviceResponse;
import com.namji.datacollection.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/devices")
public class DeviceController {

  private final DeviceService deviceService;

  @PostMapping()
  public ResponseEntity<CommonResponse<DeviceResponse>> createDevice(@RequestBody DeviceRequest deviceRequest) {
    DeviceResponse response = deviceService.createDevice(deviceRequest);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<DeviceResponse>builder()
            .msg("success")
            .data(response)
            .build());
  }

  @GetMapping("/statistics")
  public ResponseEntity<CommonResponse<DataStatisticsResponse>> getDeviceStatistics(
      @RequestBody DataStatisticsRequest request) {
    DataStatisticsResponse response = deviceService.getDeviceStatistics(request);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<DataStatisticsResponse>builder()
            .msg("success")
            .data(response)
            .build());
  }
}
