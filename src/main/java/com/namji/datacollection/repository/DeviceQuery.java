package com.namji.datacollection.repository;

import com.namji.datacollection.dto.request.DataStatisticsRequest;
import com.namji.datacollection.dto.response.DataStatisticsResponse;

public interface DeviceQuery {

  DataStatisticsResponse getDeviceStatistics(DataStatisticsRequest request);

}
