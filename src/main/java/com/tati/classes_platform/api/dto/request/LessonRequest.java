package com.tati.classes_platform.api.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
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
public class LessonRequest {

  @NotBlank(message = "Title is required")
  @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
  @Schema(description = "Title of the lesson", example = "Introduction to Maths")
  private String title;

  @NotBlank(message = "Content is required")
  @Size(min = 2, max = 1000, message = "Content must be between 2 and 1000 characters")
  @Schema(description = "Content of the lesson", example = "Details to Maths")
  private String content;

  @NotNull(message = "Active status is required")
  @Schema(description = "Indicates if the class is active", example = "true")
  private Boolean isActive;

  @NotNull(message = "ClassEntityId is required")
  @Schema(description = "Id of the class entity", example = "1")
  private Long ClassEntityId;

  @NotNull(message = "multimedia is required")
  @ArraySchema(schema = @Schema(description = "Multimedia request details", implementation = MultimediaRequest.class))
  private List<MultimediaRequest> multimedias;

}
