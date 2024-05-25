package com.project.commentservice.comment.service;

import com.project.commentservice.comment.models.Comment;
import com.project.commentservice.comment.models.dto.CommentReactRequestDto;
import com.project.commentservice.post.models.dto.CommentRequestDto;
import com.project.commentservice.post.models.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto createComment(Long userId, String content, Long postId);
    List<Comment> getNFirstLevelCommentOfPost(Long postId, int page, int size);
    boolean reactOnComment(CommentReactRequestDto commentReactRequestDto);

    CommentResponseDto commentOnComment(CommentRequestDto commentRequestDto);

    Comment getComment(Long commentId);

    List<Comment> getCommentNextLevelReplies(Long commentId, int page, int size);

    long getCommentsCountOnPost(Long postId);

    List<String> getUsersForReactType(Long commentId, int reactType);
}
