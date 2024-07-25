package com.tati.classes_platform.infrastructure.abstract_services;

import com.tati.classes_platform.api.dto.request.StudentRequest;
import com.tati.classes_platform.api.dto.response.StudentResponseDetails;
import com.tati.classes_platform.api.dto.response.responseBasic.StudentResponse;
import com.tati.classes_platform.infrastructure.interfaces.Create;
import com.tati.classes_platform.infrastructure.interfaces.Delete;
import com.tati.classes_platform.infrastructure.interfaces.Disable;
import com.tati.classes_platform.infrastructure.interfaces.FindAllByName;
import com.tati.classes_platform.infrastructure.interfaces.FindById;
import com.tati.classes_platform.infrastructure.interfaces.Update;

public interface IStudentService  extends FindAllByName<StudentResponseDetails>, Create<StudentRequest, StudentResponse>,
    FindById<StudentResponseDetails, Long>, Delete<Long>, Update<StudentRequest, StudentResponse, Long>,
    Disable<StudentResponse, Long> {

}
