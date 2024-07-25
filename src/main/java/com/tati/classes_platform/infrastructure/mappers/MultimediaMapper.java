package com.tati.classes_platform.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.tati.classes_platform.api.dto.request.MultimediaRequest;
import com.tati.classes_platform.api.dto.response.MultimediaResponseDetails;
import com.tati.classes_platform.api.dto.response.responseBasic.MultimediaResponse;
import com.tati.classes_platform.domain.entities.Multimedia;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ClassMapper.class, LessonMapper.class })
public interface MultimediaMapper {

  MultimediaResponseDetails entityToResponseDetails(Multimedia multimedia);

  List<MultimediaResponseDetails> entityToResponseDetailsList(List<Multimedia> multimedias);

  @Mappings({
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "lesson", ignore = true),
      @Mapping(target = "createdAt", ignore = true) })
  Multimedia requestToEntity(MultimediaRequest rq);

  MultimediaResponse entityToResponse(Multimedia multimedia);

  List<MultimediaResponse> entityToResponseList(List<Multimedia> multimedias);

  @Mappings({
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "lesson", ignore = true),
      @Mapping(target = "createdAt", ignore = true) })
  void update(MultimediaRequest rq, @MappingTarget Multimedia multimedia);
}
