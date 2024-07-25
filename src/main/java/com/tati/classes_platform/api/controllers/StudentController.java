package com.tati.classes_platform.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tati.classes_platform.api.dto.error.ErrorResponse;
import com.tati.classes_platform.api.dto.request.StudentRequest;
import com.tati.classes_platform.api.dto.response.StudentResponseDetails;
import com.tati.classes_platform.api.dto.response.responseBasic.StudentResponse;
import com.tati.classes_platform.infrastructure.abstract_services.IStudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private final IStudentService studentService;

  @Operation(summary = "Get students by name")
  @GetMapping
  public ResponseEntity<Page<StudentResponseDetails>> getAllByName(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size,
      @RequestParam(defaultValue = "") String name) {

    Pageable pageable = PageRequest.of(page - 1, size);
    return ResponseEntity.ok(studentService.findAllByName(pageable, name));
  }

  @Operation(summary = "Create a student")
  @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
  })
  @PostMapping
  public ResponseEntity<StudentResponse> createStudent(@Validated @RequestBody StudentRequest rq) {
    return ResponseEntity.ok(studentService.create(rq));
  }

  @Operation(summary = "Get a student by ID number")
  @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
  })
  @GetMapping(path = "/{id}")
  public ResponseEntity<StudentResponseDetails> getById(@PathVariable Long id) {
    return ResponseEntity.ok(studentService.findById(id));
  }

  @Operation(summary = "Delete a student by its ID number")
  @ApiResponse(responseCode = "204", description = "Student deleted successfully")
  @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
  })
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    studentService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Update a student by its ID number")
  @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
  })
  @PutMapping(path = "/{id}")
  public ResponseEntity<StudentResponse> updateStudent(@Validated @RequestBody StudentRequest rq,
      @PathVariable Long id) {
    return ResponseEntity.ok(studentService.update(rq, id));
  }

  @Operation(summary = "Disable a student by its ID number")
  @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
  })
  @PatchMapping(path = "/{id}/disable")
  public ResponseEntity<StudentResponse> disable(
      @PathVariable Long id) {
    return ResponseEntity.ok(studentService.disable(id));
  }

}
