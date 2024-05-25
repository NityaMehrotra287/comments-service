package com.project.commentservice.likeDislike.repository.impl;

import com.project.commentservice.likeDislike.models.CommentsLikeDislike;
import com.project.commentservice.likeDislike.repository.CommentLikeDislikeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentLikeDislikeRepositoryImpl implements CommentLikeDislikeRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    @Modifying
    @Transactional
    public void saveLikeDislike(CommentsLikeDislike commentsLikeDislike) {
        Query query = entityManager.createNativeQuery("INSERT into comments_like_dislike (comment_id, `like`, added_by, created_at, updated_at) VALUES (?, ?,?,?,?)");
        query.setParameter(1, commentsLikeDislike.getCommentId());
        query.setParameter(2, commentsLikeDislike.getLike());
        query.setParameter(3, commentsLikeDislike.getAddedBy());
        query.setParameter(4, commentsLikeDislike.getCreatedAt());
        query.setParameter(5, commentsLikeDislike.getUpdatedAt());
        query.executeUpdate();
    }

    @Override
    public List<Long> getUsersIdsByReactTypeOnComment(Long commentId, int reactType) {
        Query query = entityManager.createNativeQuery("SELECT added_by from comments_like_dislike c where c.comment_id =:comment_id and `like` =:like");
        query.setParameter("comment_id", commentId);
        query.setParameter("like", reactType);
        return query.getResultList();
    }
}
