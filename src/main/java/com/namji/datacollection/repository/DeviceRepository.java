package com.namji.datacollection.repository;

import com.namji.datacollection.entity.Device;
import com.namji.datacollection.entity.Group;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

  Device findBySerialNumber(String request);

  List<Device> findAllByGroup(Group group);

}
