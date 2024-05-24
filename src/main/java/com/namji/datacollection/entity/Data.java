package com.namji.datacollection.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
  private int dataInfo;

  @Column(nullable = false)
  private LocalDateTime recordedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "device_id", nullable = false)
  private Device device;

  public Data(int info, LocalDateTime recordedAt, Device device) {
    this.dataInfo = info;
    this.recordedAt = recordedAt;
    this.device = device;
  }
}
