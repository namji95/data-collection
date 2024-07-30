package com.namji.datacollection.repository;

import com.namji.datacollection.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

  Group findByStationGroupSerial(String stationGroupSerial);

}
