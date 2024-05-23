package com.project.commentservice.post.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CreatePostRequestDto {

    @NotNull(message = "User ID must not be null")
    private Long userId;

    @NotBlank(message = "Content is required")
    private String content;
}