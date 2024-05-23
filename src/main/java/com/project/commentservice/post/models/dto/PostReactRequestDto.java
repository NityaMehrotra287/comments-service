package com.project.commentservice.post.models.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostReactRequestDto {
    private Long userId;

    private Long postId;

    private int reactType;
}
