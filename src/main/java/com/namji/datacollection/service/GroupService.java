package com.namji.datacollection.service;

import com.namji.datacollection.dto.request.GroupRequest;
import com.namji.datacollection.dto.response.GroupResponse;
import com.namji.datacollection.entity.Group;
import com.namji.datacollection.repository.GroupRepository;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {

  private final GroupRepository groupRepository;

  @Transactional
  public GroupResponse createGroup(GroupRequest request) {

    Group group = groupRepository.findByGroupSerial(request.getGroupSerial());

    if (group != null) {
      throw new IllegalArgumentException("동일한 그룹이 존재합니다.");
    }

    Group newGroup = new Group(request.getGroupSerial());

    Group saveGroup = groupRepository.save(newGroup);

    return new GroupResponse(
        saveGroup.getGroupId(),
        saveGroup.getGroupSerial(),
        saveGroup.getCreateAt());
  }
}
