package com.tati.classes_platform.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tati.classes_platform.api.dto.error.ErrorResponse;
import com.tati.classes_platform.api.dto.request.ClassRequest;
import com.tati.classes_platform.api.dto.response.ClassResponseDetails;
import com.tati.classes_platform.infrastructure.abstract_services.IClassEntityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/classes")
public class ClassEntityController {

  @Autowired
  private final IClassEntityService service;

  @Operation(summary = "Get classes by name")
  @GetMapping
  public ResponseEntity<Page<ClassResponseDetails>> getAllByName(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size,
      @RequestParam(defaultValue = "") String name) {

    Pageable pageable = PageRequest.of(page - 1, size);
    return ResponseEntity.ok(service.findAllByName(pageable, name));
  }

  @Operation(summary = "Create a class")
  @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
  })
  @PostMapping
  public ResponseEntity<ClassResponseDetails> create(@Validated @RequestBody ClassRequest rq) {
    return ResponseEntity.ok(service.create(rq));
  }

  @Operation(summary = "Get a class by ID number")
  @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
  })
  @GetMapping(path = "/{id}")
  public ResponseEntity<ClassResponseDetails> getById(@PathVariable Long id) {
    return ResponseEntity.ok(service.findById(id));
  }

}
