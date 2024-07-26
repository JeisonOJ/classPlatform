package com.tati.classes_platform.api.dto.request;

import com.tati.classes_platform.util.enums.Type;

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
@Schema(description = "Multimedia")
public class MultimediaRequest {

  @NotBlank(message = "Url is required")
  @Size(min = 2, max = 255, message = "Url must be between 2 and 255 characters")
  @Schema(description = "Url of the multimedia", example = "https://www.youtube.com/watch?v=dQw4w9WgXcQ")
  private String url;

  @NotNull(message = "Type is required")
  @Schema(description = "Type of the multimedia: VIDEO, AUDIO, DOCUMENT", example = "VIDEO")
  private Type type;

  @NotNull(message = "Active status is required")
  @Schema(description = "Indicates if the class is active", example = "true")
  private Boolean isActive;

}
