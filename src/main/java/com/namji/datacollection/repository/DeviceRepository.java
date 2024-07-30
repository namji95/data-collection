package com.namji.datacollection.repository;

import com.namji.datacollection.entity.Device;
import com.namji.datacollection.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
  Device findBySerialNumberAndGroup(String serialNumber, Group findGroup);

  Device findBySerialNumber(String serialNumber);
}
