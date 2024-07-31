package com.namji.datacollection.controller;

import com.namji.datacollection.common.CommonResponse;
import com.namji.datacollection.dto.request.DataRequest;
import com.namji.datacollection.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DataController {

  private final DataService dataService;

  @PostMapping("/data-info")
  public ResponseEntity<CommonResponse<Void>> createData (@RequestBody DataRequest dataRequest) {
    dataService.createData(dataRequest);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<Void>builder()
            .msg("success")
            .build());
  }
}
