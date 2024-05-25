package com.project.commentservice.likeDislike.repository;

import com.project.commentservice.likeDislike.models.CommentsLikeDislike;

import java.util.List;

public interface CommentLikeDislikeRepository {

    void saveLikeDislike(CommentsLikeDislike commentsLikeDislike);

    List<Long> getUsersIdsByReactTypeOnComment(Long commentId, int reactType);
}
