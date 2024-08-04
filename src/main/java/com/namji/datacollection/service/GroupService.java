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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

  private final GroupRepository groupRepository;
  private final GroupQuery groupQuery;

  public GroupResponse createGroup(GroupRequest groupRequest) {
    Group findGroup = groupRepository.findByStationGroupSerial(groupRequest.getStationGroupSerial());

    if (findGroup != null) {
      throw new CustomException(ErrorCode.DUPLICATE_GROUP);
    }

    Group saveGroup = new Group(groupRequest.getStationGroupSerial());

    groupRepository.save(saveGroup);

    return new GroupResponse(
        saveGroup.getStationGroupId(),
        saveGroup.getStationGroupSerial(),
        saveGroup.getCreatedAt());

  }

  public List<DataStatisticsResponse> getGroupStatistics(DataStatisticsRequest request) {
    return groupQuery.getGroupStatistics(request);
  }
}
