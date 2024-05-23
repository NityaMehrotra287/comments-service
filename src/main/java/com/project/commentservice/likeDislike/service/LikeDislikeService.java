package com.project.commentservice.likeDislike.service;

import org.springframework.stereotype.Service;

@Service
public interface LikeDislikeService {
    void reactOnPost(Long userId, Long postId, int reactType);
    void reactOnComment(Long userId, Long commentId, int reactType);
}
