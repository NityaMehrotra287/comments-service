package com.project.commentservice.likeDislike.service.impl;

import com.project.commentservice.likeDislike.models.CommentLikeDislike;
import com.project.commentservice.likeDislike.models.PostLikeDislike;
import com.project.commentservice.likeDislike.repository.CommentLikeDislikeRepository;
import com.project.commentservice.likeDislike.repository.PostLikeDislikeRepository;
import com.project.commentservice.likeDislike.service.LikeDislikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class LikeDislikeServiceImpl implements LikeDislikeService {
    @Autowired
    private PostLikeDislikeRepository postLikeDislikeRepository;

    @Autowired
    private CommentLikeDislikeRepository commentLikeDislikeRepository;

    @Override
    public void reactOnPost(Long userId, Long postId, int reactType) {
        PostLikeDislike postLikeDislike = PostLikeDislike.builder().like(reactType).addedBy(userId).postId(postId).createdAt(new Date()).updatedAt(new Date()).build();
        postLikeDislikeRepository.saveLikeDislike(postLikeDislike);
    }

    @Override
    public void reactOnComment(Long userId, Long commentId, int reactType) {
        CommentLikeDislike commentLikeDislike = CommentLikeDislike.builder().like(reactType).addedBy(userId).commentId(commentId).createdAt(new Date()).updatedAt(new Date()).build();
        commentLikeDislikeRepository.saveLikeDislike(commentLikeDislike);
    }

    @Override
    public Set<Long> getUsersListByReactTypeOnPost(Long postId, int reactType) {
        List<Long> usersIdsByReactTypeOnPost = postLikeDislikeRepository.getUsersIdsByReactTypeOnPost(postId, reactType);
        return new HashSet<Long>(usersIdsByReactTypeOnPost);
    }

    @Override
    public Set<Long> getUsersListByReactTypeOnComment(Long commentId, int reactType) {
        List<Long> usersIdsByReactTypeOnComment = commentLikeDislikeRepository.getUsersIdsByReactTypeOnComment(commentId, reactType);
        return new HashSet<Long>(usersIdsByReactTypeOnComment);
    }
}
