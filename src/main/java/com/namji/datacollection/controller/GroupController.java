package com.namji.datacollection.controller;

import com.namji.datacollection.dto.request.GroupRequest;
import com.namji.datacollection.dto.response.CommonResponse;
import com.namji.datacollection.dto.response.GroupResponse;
import com.namji.datacollection.service.GroupService;
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
public class GroupController {

  private final GroupService groupService;

  // 그룹 등록
  @PostMapping("/groups")
  public ResponseEntity<CommonResponse<GroupResponse>> createGroup(
      @RequestBody GroupRequest request) {
    GroupResponse response = groupService.createGroup(request);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<GroupResponse>builder()
            .message("success")
            .data(response)
            .build());
  }
}
