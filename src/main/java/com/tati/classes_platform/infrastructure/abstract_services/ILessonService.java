package com.tati.classes_platform.infrastructure.abstract_services;

import com.tati.classes_platform.api.dto.request.LessonRequest;
import com.tati.classes_platform.api.dto.response.LessonResponseDetails;
import com.tati.classes_platform.api.dto.response.responseBasic.LessonResponse;
import com.tati.classes_platform.infrastructure.interfaces.Create;
import com.tati.classes_platform.infrastructure.interfaces.Disable;
import com.tati.classes_platform.infrastructure.interfaces.FindAll;

public interface ILessonService extends Create<LessonRequest, LessonResponseDetails>, Disable<LessonResponse, Long>,
        FindAll<LessonResponseDetails> {

}
