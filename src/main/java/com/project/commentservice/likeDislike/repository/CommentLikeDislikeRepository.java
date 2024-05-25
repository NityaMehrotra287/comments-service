package com.project.commentservice.likeDislike.repository;

import com.project.commentservice.likeDislike.models.CommentLikeDislike;

import java.util.List;

public interface CommentLikeDislikeRepository {

    void saveLikeDislike(CommentLikeDislike commentLikeDislike);

    List<Long> getUsersIdsByReactTypeOnComment(Long commentId, int reactType);
}
