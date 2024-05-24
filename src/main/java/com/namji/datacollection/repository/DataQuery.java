package com.namji.datacollection.repository;

import com.namji.datacollection.dto.request.StatisticsRequest;
import com.namji.datacollection.dto.response.StatisticsResponse;
import java.time.LocalDateTime;
import java.util.List;

public interface DataQuery {

  StatisticsResponse deviceStatisticsData(
      String serialNumber, LocalDateTime startDate, LocalDateTime endDate);

  List<StatisticsResponse> groupStatisticsData(
      String groupSerial, LocalDateTime startDate, LocalDateTime endDate);

}
