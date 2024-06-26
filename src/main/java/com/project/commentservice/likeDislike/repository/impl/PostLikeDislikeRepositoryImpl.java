package com.project.commentservice.likeDislike.repository.impl;

import com.project.commentservice.likeDislike.models.PostsLikeDislike;
import com.project.commentservice.likeDislike.repository.PostLikeDislikeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostLikeDislikeRepositoryImpl implements PostLikeDislikeRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    @Modifying
    @Transactional
    public void saveLikeDislike(PostsLikeDislike postsLikeDislike) {
        Query query = entityManager.createNativeQuery("INSERT into posts_like_dislike (post_id, `like`, added_by, created_at, updated_at) VALUES (?,?,?,?,?)");
        query.setParameter(1, postsLikeDislike.getPostId());
        query.setParameter(2, postsLikeDislike.getLike());
        query.setParameter(3, postsLikeDislike.getAddedBy());
        query.setParameter(4, postsLikeDislike.getCreatedAt());
        query.setParameter(5, postsLikeDislike.getUpdatedAt());
        query.executeUpdate();
    }

    @Override
    public List<Long> getUsersIdsByReactTypeOnPost(Long postId, int reactType) {
        Query query = entityManager.createNativeQuery("SELECT added_by from posts_like_dislike p where p.post_id =:post_id and `like` =:like");
        query.setParameter("post_id", postId);
        query.setParameter("like", reactType);
        return query.getResultList();
    }
}
