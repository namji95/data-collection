package com.namji.datacollection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "devices")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Device extends TimeStamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long deviceId;

  @Column
  private String serialNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "station_group_id", nullable = false)
  private Group group;

  public Device(String serialNumber, Group group) {
    this.serialNumber = serialNumber;
    this.group = group;
  }
}
