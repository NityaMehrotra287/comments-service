package com.project.commentservice.post.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CommentRequestDto {
    private Long userId;
    private Long postId;
    private String comment;
    private Long parentCommentId;
}
