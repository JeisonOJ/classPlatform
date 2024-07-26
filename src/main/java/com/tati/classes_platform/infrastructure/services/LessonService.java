package com.tati.classes_platform.infrastructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tati.classes_platform.api.dto.request.LessonRequest;
import com.tati.classes_platform.api.dto.response.LessonResponseDetails;
import com.tati.classes_platform.api.dto.response.responseBasic.LessonResponse;
import com.tati.classes_platform.domain.entities.Lesson;
import com.tati.classes_platform.domain.entities.Multimedia;
import com.tati.classes_platform.domain.repositories.ClassRepository;
import com.tati.classes_platform.domain.repositories.LessonRepository;
import com.tati.classes_platform.domain.repositories.MultimediaRepository;
import com.tati.classes_platform.infrastructure.abstract_services.ILessonService;
import com.tati.classes_platform.infrastructure.mappers.LessonMapper;
import com.tati.classes_platform.infrastructure.mappers.MultimediaMapper;
import com.tati.classes_platform.util.exceptions.BadIdException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class LessonService implements ILessonService {

    @Autowired
    private final LessonRepository lessonRepository;

    @Autowired
    private final LessonMapper lessonMapper;

    @Autowired
    private final ClassRepository classRepository;

    @Autowired
    private final MultimediaRepository multimediaRepository;

    @Autowired
    private final MultimediaMapper multimediaMapper;

    @Override
    public LessonResponseDetails create(LessonRequest rq) {
        Lesson lesson = lessonRepository.save(lessonMapper.requestToEntity(rq));
        lesson.setClassEntity(classRepository.findById(rq.getClassEntityId())
                .orElseThrow(() -> new BadIdException("Class not found")));
        List<Multimedia> multimedias = multimediaMapper.requestListToEntityList(rq.getMultimedias());
        List<Multimedia> savedMultimedias = multimedias.stream().map(multimedia -> {
            multimedia.setLesson(lesson);
            return multimediaRepository.save(multimedia);
        }).toList();
        for (Multimedia multimedia2 : savedMultimedias) {
            log.info("------------------->" + multimedia2);
        }
        lesson.setMultimedias(savedMultimedias);
        return lessonMapper.entityToResponseDetails(lesson);
    }

    @Override
    public LessonResponse disable(Long id) {
        Lesson lesson = getById(id);
        lesson.setIsActive(false);
        return lessonMapper.entityToResponse(lessonRepository.save(lesson));
    }

    @Override
    public List<LessonResponseDetails> findAll() {
        return lessonMapper.entityToResponseDetailsList(lessonRepository.findAll());
    }

    private Lesson getById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> new BadIdException("Lesson not found"));
    }

}
