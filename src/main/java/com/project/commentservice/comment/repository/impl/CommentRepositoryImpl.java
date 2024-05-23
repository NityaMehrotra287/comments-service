package com.project.commentservice.comment.repository.impl;

import com.project.commentservice.comment.models.Comment;
import com.project.commentservice.comment.models.dto.CommentReactRequestDto;
import com.project.commentservice.comment.repository.CommentRepository;
import com.project.commentservice.post.models.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Modifying
    @Transactional
    public void saveComment(Comment comment) {
        Query query = entityManager.createNativeQuery("INSERT into comments (post_id, parent_id, content, added_by, created_at, updated_at) VALUES (?, ?, ?,?, ?, ?)", Comment.class);
        query.setParameter(1, comment.getPostId());
        query.setParameter(2, null);
        query.setParameter(3, comment.getContent());
        query.setParameter(4, comment.getAddedBy());
        query.setParameter(5, comment.getCreatedAt());
        query.setParameter(6, comment.getUpdatedAt());
        query.executeUpdate();
    }

    public List<Comment> getFirstNLevelComments(Long postId, int start, int limit) {
        Query query = entityManager.createNativeQuery("SELECT * FROM comments where e.postId = :postId", Comment.class);
        query.setParameter("postId", postId);
        query.setFirstResult(start);
        query.setMaxResults(limit);
        return (List<Comment>) query.getResultList();
    }

    @Override
    public Optional<Comment> getCommentById(Long commentId) {
        Comment comment = entityManager.find(Comment.class, commentId);
        return Optional.ofNullable(comment);
    }
}