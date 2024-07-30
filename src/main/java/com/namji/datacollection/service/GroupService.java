package com.namji.datacollection.service;

import com.namji.datacollection.dto.request.GroupRequest;
import com.namji.datacollection.dto.response.GroupResponse;
import com.namji.datacollection.entity.Group;
import com.namji.datacollection.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

  private final GroupRepository groupRepository;

  public GroupResponse createGroup(GroupRequest groupRequest) {
    Group findGroup = groupRepository.findByStationGroupSerial(groupRequest.getStationGroupSerial());

    if (findGroup != null) {
      throw new IllegalArgumentException("일치하는 그룹이 존재합니다.");
    }

    Group saveGroup = new Group(groupRequest.getStationGroupSerial());

    groupRepository.save(saveGroup);

    return new GroupResponse(
        saveGroup.getStationGroupId(),
        saveGroup.getStationGroupSerial(),
        saveGroup.getCreatedAt());

  }
}
