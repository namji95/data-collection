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
import com.namji.datacollection.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeviceService {

  private final DeviceRepository deviceRepository;
  private final GroupRepository groupRepository;
  private final DeviceQuery deviceQuery;
  private final CommonUtil commonUtil;

  public DeviceResponse createDevice(DeviceRequest deviceRequest) {
    Group findGroup = commonUtil.findGroup(deviceRequest.getStationGroupSerial());
    commonUtil.duplicatedDevice(deviceRequest.getSerialNumber(), findGroup);

    Device saveDevice = new Device(deviceRequest.getSerialNumber(), findGroup);

    deviceRepository.save(saveDevice);

    return new DeviceResponse(
        saveDevice.getDeviceId(),
        saveDevice.getSerialNumber(),
        saveDevice.getGroup(),
        saveDevice.getCreatedAt());
  }

  public DataStatisticsResponse getDeviceStatistics(DataStatisticsRequest request) {
    Group findGroup = commonUtil.findGroup(request.getStationGroupSerial());
    Device findDevice = commonUtil.findDevice(request.getSerialNumber(), findGroup);
    LocalDateTime startDate = request.getStartDate();
    LocalDateTime endDate = request.getEndDate();

    return deviceQuery.getDeviceStatistics(findDevice, startDate, endDate);
  }
}
