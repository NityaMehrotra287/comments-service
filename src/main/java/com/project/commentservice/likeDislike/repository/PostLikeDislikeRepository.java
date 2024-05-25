package com.project.commentservice.likeDislike.repository;

import com.project.commentservice.likeDislike.models.PostsLikeDislike;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikeDislikeRepository {
    void saveLikeDislike(PostsLikeDislike postsLikeDislike);

    List<Long> getUsersIdsByReactTypeOnPost(Long postId, int reactType);
}
