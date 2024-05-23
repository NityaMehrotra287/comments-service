package com.project.commentservice.likeDislike.repository;

import com.project.commentservice.likeDislike.models.CommentLikeDislike;
import com.project.commentservice.likeDislike.models.PostLikeDislike;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikeDislikeRepository {
//    @Query("SELECT c FROM CommentLikeDislike c WHERE c.commentId = :commentId AND c.like = :like")
//    List<CommentLikeDislike> findByCommentIdAndLike(Long commentId, boolean like);

    void saveLikeDislike(PostLikeDislike postLikeDislike);
}
