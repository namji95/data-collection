package com.namji.datacollection.repository;

import com.namji.datacollection.dto.request.StatisticsRequest;
import com.namji.datacollection.dto.response.StatisticsResponse;
import com.namji.datacollection.entity.QData;
import com.namji.datacollection.entity.QDevice;
import com.namji.datacollection.entity.QGroup;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DataQueryImpl implements DataQuery {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public StatisticsResponse deviceStatisticsData(
      String serialNumber, LocalDateTime startDate, LocalDateTime endDate) {
    QData qData = QData.data;
    QDevice qDevice = QDevice.device;

    return jpaQueryFactory
        .select(Projections.constructor(
            StatisticsResponse.class,
            qDevice.deviceId,
            qDevice.serialNumber,
            qData.dataInfo.avg()))
        .from(qDevice)
        .join(qData).on(qDevice.deviceId.eq(qData.device.deviceId))
        .where(qDevice.serialNumber.eq(serialNumber)
            .and(qData.recordedAt.between(startDate, endDate.plusDays(1))))
        .fetchOne();
  }

  @Override
  public List<StatisticsResponse> groupStatisticsData(
      String groupSerial, LocalDateTime startDate, LocalDateTime endDate) {
    QGroup group = QGroup.group;
    QDevice device = QDevice.device;
    QData data = QData.data;

    return jpaQueryFactory
        .select(Projections.constructor(
            StatisticsResponse.class,
            device.deviceId,
            device.serialNumber,
            data.dataInfo.avg()))
        .from(group)
        .join(device).on(device.group.groupId.eq(group.groupId))
        .join(data).on(data.device.deviceId.eq(device.deviceId))
        .where(group.groupSerial.eq(groupSerial)
            .and(data.recordedAt.between(startDate, endDate.plusDays(1))))
        .groupBy(device.serialNumber)
        .fetch();
  }
}
