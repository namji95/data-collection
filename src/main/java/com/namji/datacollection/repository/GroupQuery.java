package com.namji.datacollection.repository;

import com.namji.datacollection.dto.request.DataStatisticsRequest;
import com.namji.datacollection.dto.response.DataStatisticsResponse;

import java.util.List;

public interface GroupQuery {

  List<DataStatisticsResponse> getGroupStatistics(DataStatisticsRequest request);

}
