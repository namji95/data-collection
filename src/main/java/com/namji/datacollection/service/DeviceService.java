package com.namji.datacollection.service;

import com.namji.datacollection.dto.request.DeviceRequest;
import com.namji.datacollection.dto.response.DeviceResponse;
import com.namji.datacollection.entity.Device;
import com.namji.datacollection.entity.Group;
import com.namji.datacollection.repository.DeviceRepository;
import com.namji.datacollection.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeviceService {

  private final DeviceRepository deviceRepository;
  private final GroupRepository groupRepository;
  @Transactional
  public DeviceResponse createDevice(DeviceRequest request) {
    Group group = groupRepository.findByGroupSerial(request.getGroupSerial());
    Device device = deviceRepository.findBySerialNumber(request.getSerialNumber());

    if (group == null) {
      throw new IllegalArgumentException("그룹이 존재하지 않습니다. 그룹을 등록하세요");
    } else if (device != null) {
      throw new IllegalArgumentException("동일한 장치가 존재합니다.");
    }

    Device newDevice = new Device(
        request.getSerialNumber(),
        group);

    Device saveDevice = deviceRepository.save(newDevice);

    return new DeviceResponse(
        saveDevice.getDeviceId(),
        saveDevice.getSerialNumber(),
        saveDevice.getGroup(),
        saveDevice.getCreateAt());
  }
}
