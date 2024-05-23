package com.project.commentservice.likeDislike.repository.impl;

import com.project.commentservice.likeDislike.models.CommentLikeDislike;
import com.project.commentservice.likeDislike.repository.CommentLikeDislikeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public class CommentLikeDislikeRepositoryImpl implements CommentLikeDislikeRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    @Modifying
    @Transactional
    public void saveLikeDislike(CommentLikeDislike commentLikeDislike) {
        Query query = entityManager.createNativeQuery("INSERT into comment_like_dislike (comment_id, like, added_by, created_at, updated_at) VALUES (?, ?,?,?,?)");
        query.setParameter(1, commentLikeDislike.getCommentId());
        query.setParameter(2, commentLikeDislike.getLike());
        query.setParameter(3, commentLikeDislike.getAddedBy());
        query.setParameter(4, commentLikeDislike.getCreatedAt());
        query.setParameter(5, commentLikeDislike.getUpdatedAt());
        query.executeUpdate();
    }
}
