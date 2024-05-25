package com.project.commentservice.post.models.dto;

import com.project.commentservice.post.models.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostDetailsResponseDto {
    Post post;
    long commentsCount;

}
