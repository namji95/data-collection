package com.namji.datacollection.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;

/**
 * JPA AttributeConverter를 사용하여 List<String>을 JSON 문자열로 변환하고,
 * JSON 문자열을 List<String>으로 변환하는 클래스입니다.
 */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * List<String>을 JSON 문자열로 변환하여 데이터베이스에 저장
   *
   * @param dataList 변환할 List<String> 데이터
   * @return 변환된 JSON 문자열
   */
  @Override
  public String convertToDatabaseColumn(List<String> dataList) {
    try {
      // ObjectMapper를 사용하여 List<String>을 JSON 문자열로 변환
      return objectMapper.writeValueAsString(dataList);
    } catch (JsonProcessingException e) {
      // 변환 중 오류 발생 시 예외 처리
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * JSON 문자열을 List<String>으로 변환하여 엔티티 속성에 설정
   *
   * @param data 변환할 JSON 문자열
   * @return 변환된 List<String>
   */
  @Override
  public List<String> convertToEntityAttribute(String data) {
    try {
      // ObjectMapper를 사용하여 JSON 문자욜을 List<String>으로 변환
      return objectMapper.readValue(data, new TypeReference<>() {});
    } catch (JsonProcessingException e) {
      // 변환 중 오류 발생 시 예외 처리
      throw new RuntimeException(e.getMessage());
    }
  }
}
