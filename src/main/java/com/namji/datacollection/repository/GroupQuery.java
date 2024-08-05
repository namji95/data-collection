package com.namji.datacollection.repository;

import com.namji.datacollection.dto.request.DataStatisticsRequest;
import com.namji.datacollection.dto.response.DataStatisticsResponse;
import com.namji.datacollection.entity.Group;

import java.time.LocalDateTime;
import java.util.List;

public interface GroupQuery {

  List<DataStatisticsResponse> getGroupStatistics(Group findGroup, LocalDateTime startDate, LocalDateTime endDate);
}
