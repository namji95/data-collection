package com.namji.datacollection.service;

import com.namji.datacollection.dto.request.DataRequest;
import com.namji.datacollection.dto.request.StatisticsRequest;
import com.namji.datacollection.dto.response.DataResponse;
import com.namji.datacollection.dto.response.StatisticsResponse;
import java.time.LocalDateTime;
import java.util.List;

public interface DataService {

  List<DataResponse> createData(DataRequest request);

  StatisticsResponse deviceStatisticsData(StatisticsRequest request);

  List<StatisticsResponse> groupStatisticsData(StatisticsRequest request);

}
