package com.namji.datacollection.service;

import com.namji.datacollection.dto.request.DataStatisticsRequest;
import com.namji.datacollection.dto.request.GroupRequest;
import com.namji.datacollection.dto.response.DataStatisticsResponse;
import com.namji.datacollection.dto.response.GroupResponse;
import com.namji.datacollection.entity.Group;
import com.namji.datacollection.exception.CustomException;
import com.namji.datacollection.exception.ErrorCode;
import com.namji.datacollection.repository.GroupQuery;
import com.namji.datacollection.repository.GroupRepository;
import com.namji.datacollection.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

  private final GroupRepository groupRepository;
  private final GroupQuery groupQuery;
  private final CommonUtil commonUtil;

  // 데이터 저장 장치 분류 그룹
  @Transactional
  public GroupResponse createGroup(GroupRequest groupRequest) {
    commonUtil.duplicatedGroup(groupRequest.getStationGroupSerial());

    Group saveGroup = new Group(groupRequest.getStationGroupSerial());

    groupRepository.save(saveGroup);

    return new GroupResponse(
        saveGroup.getStationGroupId(),
        saveGroup.getStationGroupSerial(),
        saveGroup.getCreatedAt());

  }

  // 그룹 기준 데이터 통계
  @Transactional(readOnly = true)
  public List<DataStatisticsResponse> getGroupStatistics(DataStatisticsRequest request) {
    Group findGroup = commonUtil.findGroup(request.getStationGroupSerial());
    LocalDateTime startDate = request.getStartDate();
    LocalDateTime endDate = request.getEndDate();

    return groupQuery.getGroupStatistics(findGroup, startDate, endDate);
  }
}
