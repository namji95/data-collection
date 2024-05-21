package com.namji.datacollection.service;

import com.namji.datacollection.dto.request.DataRequest;
import com.namji.datacollection.dto.response.DataResponse;
import com.namji.datacollection.entity.Data;
import com.namji.datacollection.entity.Device;
import com.namji.datacollection.repository.DataRepository;
import com.namji.datacollection.repository.DeviceRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

  public DataResponse createData(DataRequest request) {
    Device device = deviceRepository.findBySerialNumber(request.getSerialNumber());
    if (device == null) {
      throw new IllegalArgumentException("존재하는 장치가 없습니다.");
    }
    List<String> splitStr = splitString(request.getDataSet());
    List<String> decimalData = decimalString(splitStr);
    List<String> dateFormat =
        formattingDate(request.getRecordedAt(), request.getTimeInterval(), decimalData.size());
    List<String> dataList = new ArrayList<>();

    for (int i = 0; i < dateFormat.size(); i++) {
      dataList.add(decimalData + ", " + dateFormat);
    }

    log.info(String.valueOf(dataList));

    Data data = new Data(
        dataList,
        device);

    Data saveData = dataRepository.save(data);

    DataResponse response = new DataResponse(
        saveData.getDataId(),
        saveData.getDevice().getSerialNumber(),
        saveData.getDataList());

    return response;
  }

  // 입력받은 문자열 자르기
  private List<String> splitString(String str) {
    List<String> splitstr = new ArrayList<>();

    for (int i = 0; i < str.length(); i+= 4) {
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

  // 날짜 포맷팅
  private List<String> formattingDate(LocalDateTime time, int minutes, int size) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    List<String> formatDate = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      formatDate.add(time.plusMinutes(minutes).format(formatter));
    }

    return formatDate;
  }
}
