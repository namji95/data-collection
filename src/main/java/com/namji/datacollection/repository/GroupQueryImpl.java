package com.namji.datacollection.repository;

import com.namji.datacollection.dto.request.DataStatisticsRequest;
import com.namji.datacollection.dto.response.DataStatisticsResponse;
import com.namji.datacollection.entity.Group;
import com.namji.datacollection.entity.QData;
import com.namji.datacollection.entity.QDevice;
import com.namji.datacollection.entity.QGroup;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupQueryImpl implements GroupQuery {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<DataStatisticsResponse> getGroupStatistics(
      Group findGroup, LocalDateTime startDate, LocalDateTime endDate) {
    QGroup qGroup = QGroup.group;
    QDevice qDevice = QDevice.device;
    QData qData = QData.data;

    return jpaQueryFactory
        .select(Projections.constructor(
            DataStatisticsResponse.class,
            qDevice.deviceId.as("id"),
            qDevice.serialNumber,
            qData.dataSet.avg().as("averageData")))
        .from(qGroup)
        .join(qDevice).on(qGroup.stationGroupId.eq(qDevice.group.stationGroupId))
        .join(qData).on(qDevice.deviceId.eq(qData.device.deviceId))
        .where(qGroup.stationGroupSerial.eq(findGroup.getStationGroupSerial()),
            qData.recordedAt.between(startDate, endDate))
        .groupBy(qDevice.deviceId, qDevice.serialNumber)
        .fetch();
  }
}
