package com.project.commentservice.post.service;


import com.project.commentservice.comment.models.Comment;
import com.project.commentservice.exceptions.custom.PostNotFoundException;
import com.project.commentservice.post.models.dto.*;

import java.util.List;
import java.util.Set;


public interface PostService {
    CreatePostResponseDto create(CreatePostRequestDto createPostRequestDto);

    CommentResponseDto commentOnPost(CommentRequestDto commentRequestDto);

    PostDetailsResponseDto getPostDetails(Long postId) throws PostNotFoundException;

    boolean reactOnPost(PostReactRequestDto postReactRequestDto);

    List<Comment> getNFirstLevelComments(Long postId, int page, int size, int n);

    List<String> getUsersForReactType(Long postId, int reactType);
}
