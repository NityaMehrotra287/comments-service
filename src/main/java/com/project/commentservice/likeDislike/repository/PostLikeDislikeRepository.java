package com.project.commentservice.likeDislike.repository;

import com.project.commentservice.likeDislike.models.CommentLikeDislike;
import com.project.commentservice.likeDislike.models.PostLikeDislike;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikeDislikeRepository {
    void saveLikeDislike(PostLikeDislike postLikeDislike);

    List<Long> getUsersIdsByReactTypeOnPost(Long postId, int reactType);
}
