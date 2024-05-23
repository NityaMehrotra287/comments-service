package com.project.commentservice.likeDislike.repository.impl;

import com.project.commentservice.likeDislike.models.PostLikeDislike;
import com.project.commentservice.likeDislike.repository.PostLikeDislikeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;

public class PostLikeDislikeRepositoryImpl implements PostLikeDislikeRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    @Modifying
    @Transactional
    public void saveLikeDislike(PostLikeDislike postLikeDislike) {
        Query query = entityManager.createNativeQuery("INSERT into post_like_dislike (post_id, like, added_by, created_at, updated_at) VALUES (?, ?,?,?,?)");
        query.setParameter(1, postLikeDislike.getPostId());
        query.setParameter(2, postLikeDislike.getLike());
        query.setParameter(3, postLikeDislike.getAddedBy());
        query.setParameter(4, postLikeDislike.getCreatedAt());
        query.setParameter(5, postLikeDislike.getUpdatedAt());
        query.executeUpdate();
    }
}
