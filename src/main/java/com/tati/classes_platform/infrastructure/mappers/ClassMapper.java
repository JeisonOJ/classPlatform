package com.tati.classes_platform.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.tati.classes_platform.api.dto.request.ClassRequest;
import com.tati.classes_platform.api.dto.response.ClassResponseDetails;
import com.tati.classes_platform.api.dto.response.responseBasic.ClassResponse;
import com.tati.classes_platform.domain.entities.ClassEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { LessonMapper.class, StudentMapper.class })
public interface ClassMapper {

    ClassResponseDetails entityToResponseDetails(ClassEntity classEntity);
    
    List<ClassResponseDetails> entityToResponseDetailsList(List<ClassEntity> classes);
    
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "lessons", ignore = true),
        @Mapping(target = "students", ignore = true),
        @Mapping(target = "createdAt", ignore = true) })
    ClassEntity requestToEntity(ClassRequest rq);
    
    ClassResponse entityToResponse(ClassEntity classEntity);

    List<ClassResponse> entityToResponseList(List<ClassEntity> classes);
    
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "lessons", ignore = true),
        @Mapping(target = "students", ignore = true),
        @Mapping(target = "createdAt", ignore = true) })
    void updateClassEntity(ClassRequest rq, @MappingTarget ClassEntity classEntity);
}
