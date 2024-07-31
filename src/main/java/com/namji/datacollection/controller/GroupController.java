package com.namji.datacollection.controller;

import com.namji.datacollection.common.CommonResponse;
import com.namji.datacollection.dto.request.DataRequest;
import com.namji.datacollection.dto.request.DataStatisticsRequest;
import com.namji.datacollection.dto.request.GroupRequest;
import com.namji.datacollection.dto.response.DataStatisticsResponse;
import com.namji.datacollection.dto.response.GroupResponse;
import com.namji.datacollection.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupController {

  private final GroupService groupService;

  @PostMapping()
  public ResponseEntity<CommonResponse<GroupResponse>> createGroup(@RequestBody GroupRequest groupRequest) {
    GroupResponse response = groupService.createGroup(groupRequest);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<GroupResponse>builder()
            .msg("success")
            .data(response)
            .build());
  }

  @GetMapping("/statistics")
  public ResponseEntity<CommonResponse<List<DataStatisticsResponse>>> getGroupStatistics(
      @RequestBody DataStatisticsRequest request) {
    List<DataStatisticsResponse> response = groupService.getGroupStatistics(request);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<List<DataStatisticsResponse>>builder()
            .msg("success")
            .data(response)
            .build());
  }
}
