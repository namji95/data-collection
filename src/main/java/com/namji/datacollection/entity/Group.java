package com.namji.datacollection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data_group")
@Entity
public class Group extends TimeStamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long stationGroupId;

  @Column
  private String stationGroupSerial;

  public Group(String stationGroupSerial) {
    this.stationGroupSerial = stationGroupSerial;
  }
}
