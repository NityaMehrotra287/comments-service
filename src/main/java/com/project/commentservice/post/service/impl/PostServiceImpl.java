package com.project.commentservice.post.service.impl;

import com.project.commentservice.comment.models.Comment;
import com.project.commentservice.comment.service.CommentService;
import com.project.commentservice.exceptions.custom.InvalidReactTypeException;
import com.project.commentservice.exceptions.custom.PostNotFoundException;
import com.project.commentservice.exceptions.custom.UserNotFoundException;
import com.project.commentservice.likeDislike.models.ReactType;
import com.project.commentservice.likeDislike.service.LikeDislikeService;
import com.project.commentservice.post.models.Post;
import com.project.commentservice.post.models.dto.*;
import com.project.commentservice.post.repository.PostRepository;
import com.project.commentservice.post.service.PostService;
import com.project.commentservice.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeDislikeService likeDislikeService;

    @Autowired
    PostRepository postRepository;

    @Override
    public CreatePostResponseDto create(CreatePostRequestDto createPostRequestDto) {
        Long userId = createPostRequestDto.getUserId();
        String content = createPostRequestDto.getContent();
        userService.validateUser(userId);
        Post post = Post.builder()
                .content(content)
                .addedBy(userId)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        postRepository.createPost(post);
        CreatePostResponseDto response = new CreatePostResponseDto();
        response.setMessage("Post added successfully");
        return response;
    }

    @Override
    public CommentResponseDto commentOnPost(CommentRequestDto commentRequestDto) {
        CommentResponseDto responseDto = new CommentResponseDto();
        Long userId = commentRequestDto.getUserId();
        Long postId = commentRequestDto.getPostId();
        String content = commentRequestDto.getComment();
        userService.validateUser(userId);
        validatePost(postId);
        CommentResponseDto comment = commentService.createComment(userId, content, postId);
        responseDto.setMessage("commented on post successfully");
        return responseDto;
    }

    @Override
    public Post getPostDetails(Long postId) throws PostNotFoundException {
        Optional<Post> postById = postRepository.getPostById(postId);  //return like and dislike and comments also
        if (postById.isEmpty()) {
            throw new PostNotFoundException(String.format("Post with postId %s is not present.", postId));
        }
        return postById.get();
    }

    @Override
    public boolean reactOnPost(PostReactRequestDto postReactRequestDto) throws UserNotFoundException, EntityNotFoundException, InvalidReactTypeException {
        Long userId = postReactRequestDto.getUserId();
        int reactType = postReactRequestDto.getReactType();
        Long postId = postReactRequestDto.getPostId();

        userService.validateUser(userId);
        validatePost(postId);
        ReactType.validateReactType(reactType);
        likeDislikeService.reactOnPost(userId, postId, reactType);
        return true;
    }

    @Override
    public List<Comment> getFirstNLevelComments(Long postId, int page, int size, int n) {
        validatePost(postId);
        List<Comment> firstNLevelCommentOfPost = commentService.getFirstNLevelCommentOfPost(postId, page, size);
        if (firstNLevelCommentOfPost.isEmpty()) {
            return new ArrayList<>();
        }
        return firstNLevelCommentOfPost;
    }

    @Override
    public Set<String> getUsersForReactType(Long postId, int reactType) {

        validatePost(postId);
        ReactType.validateReactType(reactType);
        return null;
//        ReactType reactTypeEnum = ReactType.valueOf(reactType);

//        return getPost(postId).getReactTypeVsUserIdsMap().get(reactTypeEnum) == null ? Set.of() : getPost(postId).getReactTypeVsUserIdsMap().get(reactTypeEnum);
    }

    private void validatePost(Long postId) {
        Optional<Post> postById = postRepository.getPostById(postId);
        if (postById.isEmpty()) {
            throw new PostNotFoundException(String.format("Post with postId %s is not present.", postId));
        }
    }

}
