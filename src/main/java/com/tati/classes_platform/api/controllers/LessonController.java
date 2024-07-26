package com.tati.classes_platform.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tati.classes_platform.api.dto.error.ErrorResponse;
import com.tati.classes_platform.api.dto.request.LessonRequest;
import com.tati.classes_platform.api.dto.response.LessonResponseDetails;
import com.tati.classes_platform.api.dto.response.responseBasic.LessonResponse;
import com.tati.classes_platform.infrastructure.abstract_services.ILessonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private final ILessonService service;

    @Operation(summary = "Get lessons with details")
    @GetMapping
    public ResponseEntity<List<LessonResponseDetails>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Create a lesson")
    @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @PostMapping
    public ResponseEntity<LessonResponseDetails> create(@Validated @RequestBody LessonRequest rq) {
        return ResponseEntity.ok(service.create(rq));
    }

    @Operation(summary = "Disable a lesson by its ID number")
  @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
  })
  @PatchMapping(path = "/{id}/disable")
  public ResponseEntity<LessonResponse> disable(
      @PathVariable Long id) {
    return ResponseEntity.ok(service.disable(id));
  }

}
