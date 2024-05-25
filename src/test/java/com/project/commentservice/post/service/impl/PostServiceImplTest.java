package com.project.commentservice.post.service.impl;

import com.project.commentservice.comment.service.CommentService;
import com.project.commentservice.likeDislike.service.LikeDislikeService;
import com.project.commentservice.post.models.Post;
import com.project.commentservice.post.models.dto.CreatePostRequestDto;
import com.project.commentservice.post.models.dto.CreatePostResponseDto;
import com.project.commentservice.post.repository.PostRepository;
import com.project.commentservice.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostServiceImplTest {
    @Mock
    private UserService userService;

    @Mock
    private CommentService commentService;

    @Mock
    private LikeDislikeService likeDislikeService;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {
        CreatePostRequestDto requestDto = new CreatePostRequestDto();
        requestDto.setUserId(1L);
        requestDto.setContent("Test post content");
        doNothing().when(postRepository).createPost(any(Post.class));

        CreatePostResponseDto responseDto = postService.create(requestDto);

        assertEquals("Post added successfully", responseDto.getMessage());
        verify(userService, times(1)).validateUser(anyLong());
        verify(postRepository, times(1)).createPost(any(Post.class));
    }
}