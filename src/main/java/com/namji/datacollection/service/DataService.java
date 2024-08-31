package com.namji.datacollection.service;

import com.namji.datacollection.dto.request.DataRequest;
import com.namji.datacollection.entity.Data;
import com.namji.datacollection.entity.Device;
import com.namji.datacollection.exception.CustomException;
import com.namji.datacollection.exception.ErrorCode;
import com.namji.datacollection.repository.DataJpaRepository;
import com.namji.datacollection.repository.DeviceRepository;
import com.namji.datacollection.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {

  private final DeviceRepository deviceRepository;
  private final DataJpaRepository  dataJpaRepository;
  private final CommonUtil commonUtil;

  // 데이터 저장
  @Transactional
  public void createData(DataRequest dataRequest) {
    Device findDevice = deviceRepository.findBySerialNumber(dataRequest.getSerialNumber());
    if (findDevice == null) {
      throw new CustomException(ErrorCode.NOT_FOUND_DEVICE);
    }

    List<String> splitData = splitData(dataRequest.getDataSet());
    List<Integer> decimalData = decimalData(splitData);
    List<Data> intervalData = dataList(decimalData, dataRequest.getRecordedAt(), dataRequest.getInterval(), findDevice);

    dataJpaRepository.saveAll(intervalData);
  }

  // 문자열 자르기
  private List<String> splitData(String dataSet) {
    List<String> splitData = new ArrayList<>();
    for (int i = 0; i < dataSet.length(); i += 4) {
      splitData.add(dataSet.substring(i, Math.min(dataSet.length(), i + 4)));
    }

    return splitData;
  }

  // 잘라진 문자열 10진수로 변환
  private List<Integer> decimalData(List<String> splitData) {
    List<Integer> decimalData = new ArrayList<>();
    for (String s : splitData) {
      decimalData.add((int) (short) Integer.parseInt(s, 16));
    }

    return decimalData;
  }

  // interval 간격으로 시간 계산한 값과 데이터 저장
  private List<Data> dataList(List<Integer> decimalData, LocalDateTime recordedAt, int interval, Device device) {
    List<Data> dataList = new ArrayList<>();
    for (Integer i : decimalData) {
      Data data = new Data(interval, device, i, recordedAt);
      dataList.add(data);
      recordedAt = recordedAt.plusMinutes(interval);
    }

    return dataList;
  }
}
