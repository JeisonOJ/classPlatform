package com.tati.classes_platform.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import com.tati.classes_platform.api.dto.request.LessonRequest;
import com.tati.classes_platform.api.dto.response.LessonResponseDetails;
import com.tati.classes_platform.api.dto.response.responseBasic.LessonResponse;
import com.tati.classes_platform.domain.entities.Lesson;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { MultimediaMapper.class })
public interface LessonMapper {

  List<LessonResponseDetails> entityToResponseDetailsList(List<Lesson> lessons);

  @Mappings({
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "classEntity", ignore = true),
      @Mapping(target = "createdAt", ignore = true),
       }) 
  Lesson requestToEntity(LessonRequest rq);

  LessonResponse entityToResponse(Lesson lesson);

  List<LessonResponse> entityToResponseList(List<Lesson> lessons);

  LessonResponseDetails entityToResponseDetails(Lesson lesson);
}
