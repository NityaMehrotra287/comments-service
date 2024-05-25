package com.project.commentservice.comment.repository;

import com.project.commentservice.comment.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    void saveComment(Comment comment);

    List<Comment> getNFirstLevelComments(Long postId, int start, int limit);

    Optional<Comment> getCommentById(Long commentId);

    long getCommentCountOnPost(Long postId);

    List<Comment> getRepliesByCommentId(Long commentId, int start, int size);
}
