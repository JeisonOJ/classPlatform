package com.tati.classes_platform.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.tati.classes_platform.api.dto.request.StudentRequest;
import com.tati.classes_platform.api.dto.response.StudentResponseDetails;
import com.tati.classes_platform.api.dto.response.responseBasic.StudentResponse;
import com.tati.classes_platform.domain.entities.Student;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ClassMapper.class })
public interface StudentMapper {

  StudentResponseDetails entityToResponseDetails(Student student);

  List<StudentResponseDetails> entityToResponseDetailsList(List<Student> students);

  @Mappings({
    @Mapping(target = "id", ignore = true),
    @Mapping(target = "classEntity", ignore = true),
    @Mapping(target = "createdAt", ignore = true) })
  Student requestToEntity(StudentRequest rq);

  StudentResponse entityToResponse(Student student);

  List<StudentResponse> entityToResponseList(List<Student> students);

  @Mappings({
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "classEntity", ignore = true),
      @Mapping(target = "createdAt", ignore = true) })
  void updateStudent(StudentRequest rq, @MappingTarget Student student);
}
