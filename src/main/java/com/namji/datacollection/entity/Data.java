package com.namji.datacollection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table (name = "data_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Data extends TimeStamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long dataId;

  private int TimeInterval;
  private int dataSet;
  private LocalDateTime recordedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "device_id", nullable = false)
  private Device device;

  public Data(int interval, Device device, Integer i, LocalDateTime recordedAt) {
    this.TimeInterval = interval;
    this.device = device;
    this.recordedAt = recordedAt;
    this.dataSet = i;
  }
}
