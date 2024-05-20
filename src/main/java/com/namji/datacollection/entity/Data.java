package com.namji.datacollection.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "datas")
public class Data extends TimeStamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long dataId;

  @Column(nullable = false)
  private int timeInterval;

  @Column(nullable = false)
  private String dataSet;

  @Column(nullable = false)
  private LocalDate recordedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "device_id", nullable = false)
  private Device device;

  public Data(Device device, int timeInterval, String dataSet, LocalDate recordedAt) {
    this.device = device;
    this.timeInterval = timeInterval;
    this.dataSet = dataSet;
    this.recordedAt = recordedAt;
  }
}
