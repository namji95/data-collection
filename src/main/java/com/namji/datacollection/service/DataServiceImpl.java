package com.namji.datacollection.service;

import com.namji.datacollection.dto.request.DataRequest;
import com.namji.datacollection.dto.request.StatisticsRequest;
import com.namji.datacollection.dto.response.DataResponse;
import com.namji.datacollection.dto.response.StatisticsResponse;
import com.namji.datacollection.entity.Data;
import com.namji.datacollection.entity.Device;
import com.namji.datacollection.entity.Group;
import com.namji.datacollection.repository.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataServiceImpl implements DataService {

  private final DataRepository dataRepository;
  private final DeviceRepository deviceRepository;
  private final GroupRepository groupRepository;
  private final DataQuery dataQuery;

  @Override
  @Transactional
  public List<DataResponse> createData(DataRequest request) {
    Device device = deviceRepository.findBySerialNumber(request.getSerialNumber());
    if (device == null) {
      throw new IllegalArgumentException("존재하는 장치가 없습니다.");
    }
    List<String> splitStr = splitString(request.getDataSet());
    List<Integer> decimalData = decimalString(splitStr);
    List<Data> dataList = createDataList(request.getRecordedAt(), request.getTimeInterval(), decimalData.size(), decimalData, device);
    List<DataResponse> responses = new ArrayList<>();
    for (Data data : dataList) {
      dataRepository.save(data);
      responses.add(new DataResponse(data.getDataInfo(), data.getRecordedAt()));
    }
    return responses;
  }

  @Override
  @Transactional(readOnly = true)
  public StatisticsResponse deviceStatisticsData(StatisticsRequest request) {
    return dataQuery.deviceStatisticsData(
        request.getSerialNumber(), request.getStartDate(), request.getEndDate());
  }

  @Override
  @Transactional(readOnly = true)
  public List<StatisticsResponse> groupStatisticsData(StatisticsRequest request) {
    return dataQuery.groupStatisticsData(
        request.getGroupSerial(), request.getStartDate(), request.getEndDate());
  }

  // 입력받은 문자열 자르기
  private List<String> splitString(String str) {
    List<String> splitstr = new ArrayList<>();
    for (int i = 0; i < str.length(); i += 4) {
      splitstr.add(str.substring(i, Math.min(str.length(), i + 4)));
    }
    return splitstr;
  }

  // 나눠진 문자열 10진수로 변환
  private List<Integer> decimalString(List<String> hex) {
    List<Integer> decimalData = new ArrayList<>();
    for (String string : hex) {
      decimalData.add(Integer.parseInt(string, 16));
    }
    return decimalData;
  }

  // 데이터 목록 생성
  private List<Data> createDataList(LocalDateTime time, int minutes, int size, List<Integer> decimalData, Device device) {
    List<Data> dataList = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      LocalDateTime recordedAt = time.plusMinutes((long) minutes * (i+1));
      int info = decimalData.get(i);
      dataList.add(new Data(info, recordedAt, device));
    }
    return dataList;
  }
}
