package com.namji.datacollection.entity;

import jakarta.persistence.*;
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
  @Convert(converter = StringListConverter.class)
  private List<String> dataList;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "device_id", nullable = false)
  private Device device;

  public Data(List<String> dataList, Device device) {
    this.dataList = dataList;
    this.device = device;
  }
}
