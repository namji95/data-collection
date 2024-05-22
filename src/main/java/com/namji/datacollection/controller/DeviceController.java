package com.namji.datacollection.controller;

import com.namji.datacollection.dto.request.DeviceRequest;
import com.namji.datacollection.dto.response.CommonResponse;
import com.namji.datacollection.dto.response.DeviceResponse;
import com.namji.datacollection.service.DeviceService;
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
public class DeviceController {

  private final DeviceService deviceService;

  // 장치 등록
  @PostMapping("devices")
  public ResponseEntity<CommonResponse<DeviceResponse>> createDevice(
      @RequestBody DeviceRequest request) {
    DeviceResponse response = deviceService.createDevice(request);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<DeviceResponse>builder()
            .message("success")
            .data(response)
            .build());
  }

}
