package com.tati.classes_platform.infrastructure.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tati.classes_platform.api.dto.request.ClassRequest;
import com.tati.classes_platform.api.dto.response.ClassResponseDetails;
import com.tati.classes_platform.domain.entities.ClassEntity;
import com.tati.classes_platform.domain.repositories.ClassRepository;
import com.tati.classes_platform.infrastructure.abstract_services.IClassEntityService;
import com.tati.classes_platform.infrastructure.mappers.ClassMapper;
import com.tati.classes_platform.util.exceptions.BadIdException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassEntityService implements IClassEntityService {

    @Autowired 
    private ClassMapper classMapper;

    @Autowired
    private ClassRepository classRepository;

    @Override
    public ClassResponseDetails create(ClassRequest rq) {
        ClassEntity classEntity = classMapper.requestToEntity(rq);
        classEntity.setLessons(new ArrayList<>());
        classEntity.setStudents(new ArrayList<>());
        return classMapper.entityToResponseDetails(classRepository.save(classEntity));
    }

    @Override
    public ClassResponseDetails findById(Long id) {
        return classMapper.entityToResponseDetails(getById(id));
    }

    @Override
    public Page<ClassResponseDetails> findAllByName(Pageable pageable, String name) {
        return classRepository.findAllByIsActiveTrueAndNameContaining(pageable, name)
                .map(classMapper::entityToResponseDetails);
    }
    
    private ClassEntity getById(Long id) {
        return classRepository.findById(id).orElseThrow(() -> new BadIdException("Class not found"));
    }

}
