package com.namji.datacollection.service;

import com.namji.datacollection.dto.request.DataRequest;
import com.namji.datacollection.dto.request.StatisticsRequest;
import com.namji.datacollection.dto.response.DataResponse;
import com.namji.datacollection.dto.response.StatisticsResponse;
import java.time.LocalDateTime;
import java.util.List;

public interface DataService {

  /**
   * 데이터 저장
   *
   * @param request 저장 장치 명, 나눌 시간, 데이터
   * @return 시간 별로 데이터를 나눠서 저장
   */
  List<DataResponse> createData(DataRequest request);

  /**
   * 선택 장치 데이터 평균
   *
   * @param request 장치 명, 시작 시간, 끝 시간
   * @return 선택 시간 안에서 선택 장치에 저장된 데이터 평균값 반환
   */
  StatisticsResponse deviceStatisticsData(StatisticsRequest request);

  /**
   * 그룹 장치 별 데이터 평균
   *
   * @param request 그룹 명, 시작 시간, 끝 시간
   * @return 그룹 장치 별 데이터 평균 반환
   */
  List<StatisticsResponse> groupStatisticsData(StatisticsRequest request);

}
