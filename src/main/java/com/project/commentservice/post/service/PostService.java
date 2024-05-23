package com.project.commentservice.post.service;


import com.project.commentservice.comment.models.Comment;
import com.project.commentservice.exceptions.custom.PostNotFoundException;
import com.project.commentservice.post.models.Post;
import com.project.commentservice.post.models.dto.*;

import java.util.List;
import java.util.Set;


public interface PostService {
    CreatePostResponseDto create(CreatePostRequestDto createPostRequestDto);

    CommentResponseDto commentOnPost(CommentRequestDto commentRequestDto);

    Post getPostDetails(Long postId) throws PostNotFoundException;

    boolean reactOnPost(PostReactRequestDto postReactRequestDto);

    List<Comment> getFirstNLevelComments(Long postId, int page, int size, int n);

    Set<String> getUsersForReactType(Long postId, int reactType);
}
