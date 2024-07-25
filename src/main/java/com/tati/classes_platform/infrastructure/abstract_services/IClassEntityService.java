package com.tati.classes_platform.infrastructure.abstract_services;

import com.tati.classes_platform.api.dto.request.ClassRequest;
import com.tati.classes_platform.api.dto.response.ClassResponseDetails;
import com.tati.classes_platform.infrastructure.interfaces.Create;
import com.tati.classes_platform.infrastructure.interfaces.FindAllByName;
import com.tati.classes_platform.infrastructure.interfaces.FindById;

public interface IClassEntityService extends Create<ClassRequest, ClassResponseDetails>,
        FindById<ClassResponseDetails, Long>, FindAllByName<ClassResponseDetails> {

}
