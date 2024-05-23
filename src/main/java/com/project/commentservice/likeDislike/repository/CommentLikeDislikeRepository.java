package com.project.commentservice.likeDislike.repository;

import com.project.commentservice.likeDislike.models.CommentLikeDislike;

public interface CommentLikeDislikeRepository {

    void saveLikeDislike(CommentLikeDislike commentLikeDislike);

}
