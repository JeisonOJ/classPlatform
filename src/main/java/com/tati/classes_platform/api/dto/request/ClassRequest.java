package com.tati.classes_platform.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassRequest {

  @NotBlank(message = "Name is required")
  @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
  @Schema(description = "Name of the class", example = "Maths")
  private String name;

  @NotBlank(message = "Description is required")
  @Size(min = 2, max = 1000, message = "Description must be between 2 and 1000 characters")
  @Schema(description = "Description of the class", example = "Maths is a class for maths")
  private String description;

  @NotNull(message = "Active status is required")
  @Schema(description = "Indicates if the class is active", example = "true")
  private Boolean isActive;

}
