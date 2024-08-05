package com.namji.datacollection.util;

import com.namji.datacollection.dto.response.GroupResponse;
import com.namji.datacollection.entity.Device;
import com.namji.datacollection.entity.Group;
import com.namji.datacollection.exception.CustomException;
import com.namji.datacollection.exception.ErrorCode;
import com.namji.datacollection.repository.DeviceRepository;
import com.namji.datacollection.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonUtil {

  private final GroupRepository groupRepository;
  private final DeviceRepository deviceRepository;

  public void duplicatedGroup (String stationGroupSerial) {
    Group findGroup = groupRepository.findByStationGroupSerial(stationGroupSerial);

    if (findGroup != null) {
      throw new CustomException(ErrorCode.DUPLICATE_GROUP);
    }
  }

  public Group findGroup (String stationGroupSerial) {
    Group findGroup = groupRepository.findByStationGroupSerial(stationGroupSerial);

    if (findGroup == null) {
      throw new CustomException(ErrorCode.NOT_FOUND_GROUP);
    }

    return findGroup;
  }

  public Device findDevice (String deviceSerial, Group group) {
    Device findDevice = deviceRepository.findBySerialNumberAndGroup(deviceSerial, group);

    if (findDevice == null) {
      throw new CustomException(ErrorCode.DUPLICATE_DEVICE);
    }

    return findDevice;
  }

}
