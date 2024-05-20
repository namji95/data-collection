package com.namji.datacollection.service;

import com.namji.datacollection.dto.request.DataRequest;
import com.namji.datacollection.entity.Data;
import com.namji.datacollection.entity.Device;
import com.namji.datacollection.repository.DataRepository;
import com.namji.datacollection.repository.DeviceRepository;
import java.util.Optional;
import javax.swing.text.html.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class DataService {

  private final DataRepository dataRepository;
  private final DeviceRepository deviceRepository;

  public void createData(DataRequest request) {
    Device device = deviceRepository.findBySerialNumber(request.getSerialNumber());
    if (device == null) {
      throw new IllegalArgumentException("존재하는 장치가 없습니다.");
    }

    Data data = new Data(
        device,
        request.getTimeInterval(),
        request.getDataSet(),
        request.getRecordedAt());

    dataRepository.save(data);
  }
}
