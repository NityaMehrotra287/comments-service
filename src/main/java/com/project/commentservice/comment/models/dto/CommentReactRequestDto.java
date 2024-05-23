package com.project.commentservice.comment.models.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentReactRequestDto {

    private Long userId;

    private Long commentId;

    private int reactType;
}
