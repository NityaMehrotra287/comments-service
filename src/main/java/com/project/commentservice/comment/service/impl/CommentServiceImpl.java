package com.project.commentservice.comment.service.impl;

import com.project.commentservice.comment.models.Comment;
import com.project.commentservice.comment.models.dto.CommentReactRequestDto;
import com.project.commentservice.comment.repository.CommentRepository;
import com.project.commentservice.comment.service.CommentService;
import com.project.commentservice.exceptions.custom.CommentNotFoundException;
import com.project.commentservice.likeDislike.models.ReactType;
import com.project.commentservice.likeDislike.service.LikeDislikeService;
import com.project.commentservice.post.models.dto.CommentRequestDto;
import com.project.commentservice.post.models.dto.CommentResponseDto;
import com.project.commentservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    UserService userService;

    @Autowired
    LikeDislikeService likeDislikeService;

    @Autowired
    private CommentRepository commentRepository;

    public CommentResponseDto createComment(Long userId, String content, Long postId) {
        CommentResponseDto responseDto = new CommentResponseDto();
        Comment comment = Comment.builder()
                .content(content)
                .addedBy(userId)
                .postId(postId)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        commentRepository.saveComment(comment);
        responseDto.setMessage("Comment added successfully");
        return responseDto;
    }

    public List<Comment> getNFirstLevelCommentOfPost(Long postId, int page, int size) {
        int start = page * size;
        List<Comment> firstNComments = commentRepository.getNFirstLevelComments(postId, start, size);
        return firstNComments;
    }

    @Override
    public boolean reactOnComment(CommentReactRequestDto commentReactRequestDto) {
        Long userId = commentReactRequestDto.getUserId();
        int reactType = commentReactRequestDto.getReactType();
        Long commentId = commentReactRequestDto.getCommentId();

        userService.validateUser(userId);
        validateComment(commentId);
        ReactType.validateReactType(reactType);
        likeDislikeService.reactOnComment(userId, commentId, reactType);
        return true;
    }

    @Override
    public CommentResponseDto commentOnComment(CommentRequestDto commentRequestDto) {
        CommentResponseDto responseDto = new CommentResponseDto();
        Long userId = commentRequestDto.getUserId();
        Long parentCommentId = commentRequestDto.getParentCommentId();
        String content = commentRequestDto.getComment();

        userService.validateUser(userId);
        validateComment(parentCommentId);
        Comment parentComment = getComment(parentCommentId);

        Comment childComment = Comment.builder()
                .content(content)
                .addedBy(userId)
                .postId(parentComment.getPostId())
                .parentId(parentCommentId)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        commentRepository.saveComment(childComment);
        responseDto.setMessage("Comment added successfully");
        return responseDto;
    }

    @Override
    public Comment getComment(Long commentId) {
        Optional<Comment> commentById = commentRepository.getCommentById(commentId);
        if (commentById.isEmpty()) {
            throw new CommentNotFoundException(String.format("Comment with commentId %s is not present.", commentId));
        }
        return commentById.get();
    }

    @Override
    public List<Comment> getCommentNextLevelReplies(Long commentId, int page, int size) {
        validateComment(commentId);
        int start = page * size;
        List<Comment> nextNReplies = commentRepository.getRepliesByCommentId(commentId, start, size);
        return nextNReplies;
    }
    @Override
    public long getCommentsCountOnPost(Long postId) {
        long commentCountOnPost = commentRepository.getCommentCountOnPost(postId);
        return commentCountOnPost;
    }

    @Override
    public List<String> getUsersForReactType(Long commentId, int reactType) {
        validateComment(commentId);
        ReactType.validateReactType(reactType);
        Set<Long> usersListByReactTypeOnPost = likeDislikeService.getUsersListByReactTypeOnComment(commentId, reactType);
        return userService.getUserNamesFromIds(usersListByReactTypeOnPost);
    }

    private void validateComment(Long commentId) {
        Optional<Comment> commentById = commentRepository.getCommentById(commentId);
        if (commentById.isEmpty()) {
            throw new CommentNotFoundException(String.format("Comment with commentId %s is not present.", commentId));
        }
    }
}
