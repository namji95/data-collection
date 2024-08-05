package com.namji.datacollection.repository;

import com.namji.datacollection.dto.request.DataStatisticsRequest;
import com.namji.datacollection.dto.response.DataStatisticsResponse;
import com.namji.datacollection.entity.Device;
import com.namji.datacollection.entity.QData;
import com.namji.datacollection.entity.QDevice;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class DeviceQueryImpl implements DeviceQuery {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public DataStatisticsResponse getDeviceStatistics(
      Device findDevice, LocalDateTime startDate, LocalDateTime endDate) {
    QDevice qDevice = QDevice.device;
    QData qData = QData.data;

    return jpaQueryFactory
        .select(Projections.constructor(
            DataStatisticsResponse.class,
            qDevice.deviceId.as("id"),
            qDevice.serialNumber,
            qData.dataSet.avg().as("averageData")))
        .from(qDevice)
        .join(qData).on(qDevice.deviceId.eq(qData.device.deviceId))
        .where(qDevice.serialNumber.eq(findDevice.getSerialNumber()),
            qData.recordedAt.between(startDate, endDate))
        .groupBy(qDevice.deviceId, qDevice.serialNumber)
        .fetchOne();
  }

}
