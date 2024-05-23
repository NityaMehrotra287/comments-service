package com.project.commentservice.comment.repository;

import com.project.commentservice.comment.models.Comment;
import com.project.commentservice.post.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    void saveComment(Comment comment);

    List<Comment> getFirstNLevelComments(Long postId, int start, int limit);

    Optional<Comment> getCommentById(Long commentId);
}
