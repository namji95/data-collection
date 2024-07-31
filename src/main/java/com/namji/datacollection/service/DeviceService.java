package com.namji.datacollection.service;

import com.namji.datacollection.dto.request.DataStatisticsRequest;
import com.namji.datacollection.dto.request.DeviceRequest;
import com.namji.datacollection.dto.response.DataStatisticsResponse;
import com.namji.datacollection.dto.response.DeviceResponse;
import com.namji.datacollection.entity.Device;
import com.namji.datacollection.entity.Group;
import com.namji.datacollection.repository.DeviceQuery;
import com.namji.datacollection.repository.DeviceRepository;
import com.namji.datacollection.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

  private final DeviceRepository deviceRepository;
  private final GroupRepository groupRepository;
  private final DeviceQuery deviceQuery;

  public DeviceResponse createDevice(DeviceRequest deviceRequest) {
    Group findGroup = groupRepository.findByStationGroupSerial(deviceRequest.getStationGroupSerial());
    if (findGroup == null) {
      throw new IllegalArgumentException("해당 그룹은 존재하지 않습니다.");
    }
    Device findDevice = deviceRepository.findBySerialNumberAndGroup(deviceRequest.getSerialNumber(), findGroup);
    if (findDevice != null) {
      throw new IllegalArgumentException("일치하는 장치가 존재합니다.");
    }

    Device saveDevice = new Device(
        deviceRequest.getSerialNumber(),
        findGroup);

    deviceRepository.save(saveDevice);

    return new DeviceResponse(
        saveDevice.getDeviceId(),
        saveDevice.getSerialNumber(),
        saveDevice.getGroup(),
        saveDevice.getCreatedAt());
  }

  public DataStatisticsResponse getDeviceStatistics(DataStatisticsRequest request) {
    return deviceQuery.getDeviceStatistics(request);
  }
}
