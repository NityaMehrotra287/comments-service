package com.project.commentservice.post;

import com.project.commentservice.post.models.dto.CommentRequestDto;
import com.project.commentservice.post.models.dto.CreatePostRequestDto;
import com.project.commentservice.post.models.dto.PostReactRequestDto;
import com.project.commentservice.post.service.PostService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody CreatePostRequestDto createPostRequestDto) {
        try {
            log.info("Adding post for userId:{} content:{}", createPostRequestDto.getUserId(), createPostRequestDto.getContent());
            return ResponseEntity.ok(postService.create(createPostRequestDto));
        } catch (Exception e) {
            log.error("Exception occurred while creating post and exception message :{}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{postId}/comment")
    public ResponseEntity<?> commentOnPost(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long postId) {
        try {
            log.info("Adding comment to the post for postId:{} content:{}", postId, commentRequestDto.getComment());
            commentRequestDto.setPostId(postId);
            return ResponseEntity.ok(postService.commentOnPost(commentRequestDto));
        } catch (Exception e) {
            log.error("Exception occurred while commenting on a post and exception message :{}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{postId}/react")
    public ResponseEntity<?> reactOnPost(@PathVariable Long postId, @RequestBody PostReactRequestDto postReactRequestDto) {
        try {
            postReactRequestDto.setPostId(postId);
            log.info("Reacting on post for id :{}", postId);
            return ResponseEntity.ok(postService.reactOnPost(postReactRequestDto));
        } catch (Exception e) {
            log.error("Exception occurred while reaction on a post and exception message :{}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        try {
            log.info("Get post details for id :{}", postId);
            return ResponseEntity.ok(postService.getPostDetails(postId));
        } catch (Exception e) {
            log.error("Exception occurred while getting post details and exception message :{}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<?> getFirstNLevelComments(@PathVariable Long postId,
                                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) int size,
                                                    @RequestParam(value = "pageNo", defaultValue = "0", required = false) int page) {

        try {
            log.info("Get post first n level comments for id :{}", postId);
            return ResponseEntity.ok(postService.getNFirstLevelComments(postId, page, size));
        } catch (Exception e) {
            log.error("Exception occurred while getting first n level comments and exception message :{}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{postId}/react/{reactType}/users")
    public ResponseEntity<?> getUsersWrtReactType(@PathVariable Long postId, @PathVariable int reactType) {
        try {
            log.info("Get users for react type :{} and postId :{}", reactType, postId);
            return ResponseEntity.ok(postService.getUsersForReactType(postId, reactType));
        } catch (Exception e) {
            log.error("Exception occurred while getting users who liked the post :{}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
