package com.tati.classes_platform.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tati.classes_platform.api.dto.request.StudentRequest;
import com.tati.classes_platform.api.dto.response.StudentResponseDetails;
import com.tati.classes_platform.api.dto.response.responseBasic.StudentResponse;
import com.tati.classes_platform.domain.entities.ClassEntity;
import com.tati.classes_platform.domain.entities.Student;
import com.tati.classes_platform.domain.repositories.ClassRepository;
import com.tati.classes_platform.domain.repositories.StudentRepository;
import com.tati.classes_platform.infrastructure.abstract_services.IStudentService;
import com.tati.classes_platform.infrastructure.mappers.StudentMapper;
import com.tati.classes_platform.util.exceptions.BadIdException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class StudentService implements IStudentService {

  @Autowired
  private final StudentRepository studentRepository;

  @Autowired
  private final ClassRepository classRepository;

  @Autowired
  private final StudentMapper studentMapper;

  @Override
  public Page<StudentResponseDetails> findAllByName(Pageable pageable, String name) {
    return studentRepository.findAllByIsActiveTrueAndNameContaining(pageable, name)
        .map(studentMapper::entityToResponseDetails);
  }

  @Override
  public StudentResponse create(StudentRequest rq) {
    ClassEntity classEntity = classRepository.findById(rq.getClassEntityId())
        .orElseThrow(() -> new BadIdException("Class not found"));
    Student student = studentMapper.requestToEntity(rq);
    student.setClassEntity(classEntity);
    return studentMapper.entityToResponse(studentRepository.save(studentMapper.requestToEntity(rq)));
  }

  @Override
  public StudentResponseDetails findById(Long id) {
    return studentMapper.entityToResponseDetails(getById(id));

  }

  private Student getById(Long id) {
    return studentRepository.findById(id)
        .orElseThrow(() -> new BadIdException("Student not found"));
  }

  @Override
  public void delete(Long id) {
    studentRepository.deleteById(id);
  }

  @Override
  public StudentResponse update(StudentRequest rq, Long id) {
    Student student = getById(id);
    ClassEntity classEntity = classRepository.findById(rq.getClassEntityId())
        .orElseThrow(() -> new BadIdException("Class not found"));
    student.setClassEntity(classEntity);
    studentMapper.updateStudent(rq, student);
    return studentMapper.entityToResponse(studentRepository.save(student));
  }

  @Override
  public StudentResponse disable(Long id) {
    Student student = getById(id);
    log.info("Disabling student with id: {}  name: {}",
        id, student.getName());
    student.setIsActive(false);
    return studentMapper.entityToResponse(studentRepository.save(student));
  }

}
