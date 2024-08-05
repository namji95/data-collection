package com.namji.datacollection.repository;

import com.namji.datacollection.dto.request.DataStatisticsRequest;
import com.namji.datacollection.dto.response.DataStatisticsResponse;
import com.namji.datacollection.entity.Device;

import java.time.LocalDateTime;

public interface DeviceQuery {

  DataStatisticsResponse getDeviceStatistics(Device findDevice, LocalDateTime startDate, LocalDateTime endDate);

}
