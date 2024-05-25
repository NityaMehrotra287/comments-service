package com.project.commentservice.likeDislike.service;


import java.util.Set;

public interface LikeDislikeService {
    void reactOnPost(Long userId, Long postId, int reactType);
    void reactOnComment(Long userId, Long commentId, int reactType);

    Set<Long> getUsersListByReactTypeOnPost(Long postId, int reactType);

    Set<Long> getUsersListByReactTypeOnComment(Long commentId, int reactType);
}
