package com.namji.datacollection.service;

import com.namji.datacollection.dto.request.DataRequest;
import com.namji.datacollection.dto.response.DataResponse;
import com.namji.datacollection.entity.Data;
import com.namji.datacollection.entity.Device;
import com.namji.datacollection.repository.DataRepository;
import com.namji.datacollection.repository.DeviceRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataService {

  private final DataRepository dataRepository;
  private final DeviceRepository deviceRepository;

  public List<DataResponse> createData(DataRequest request) {
    Device device = deviceRepository.findBySerialNumber(request.getSerialNumber());
    if (device == null) {
      throw new IllegalArgumentException("존재하는 장치가 없습니다.");
    }
    List<String> splitStr = splitString(request.getDataSet());
    List<String> decimalData = decimalString(splitStr);
    List<Data> dataList = createDataList(request.getRecordedAt(), request.getTimeInterval(), decimalData.size(), decimalData, device);
    List<DataResponse> responses = new ArrayList<>();
    for (Data data : dataList) {
      dataRepository.save(data);
      responses.add(new DataResponse(data.getDataInfo(), data.getRecordedAt()));
    }
    return responses;
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
  private List<String> decimalString(List<String> hex) {
    List<String> decimalstr = new ArrayList<>();
    for (String string : hex) {
      decimalstr.add(String.valueOf((short) Integer.parseInt(string, 16)));
    }
    return decimalstr;
  }

  // 데이터 목록 생성
  private List<Data> createDataList(LocalDateTime time, int minutes, int size, List<String> decimalData, Device device) {
    List<Data> dataList = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      LocalDateTime recordedAt = time.plusMinutes((long) minutes * (i+1));
      String info = decimalData.get(i);
      dataList.add(new Data(info, recordedAt, device));
    }
    return dataList;
  }
}
