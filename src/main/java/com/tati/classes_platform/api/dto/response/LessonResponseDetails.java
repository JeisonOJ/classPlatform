package com.tati.classes_platform.api.dto.response;

import java.util.List;

import com.tati.classes_platform.api.dto.response.responseBasic.ClassResponse;
import com.tati.classes_platform.api.dto.response.responseBasic.MultimediaResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponseDetails {

  private Long id;

  private String title;

  private String content;

  private String createdAt;

  private Boolean isActive;

  private ClassResponse classEntity;

  private List<MultimediaResponse> multimedias;
}
