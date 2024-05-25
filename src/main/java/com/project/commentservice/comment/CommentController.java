package com.project.commentservice.comment;

import com.project.commentservice.comment.models.dto.CommentReactRequestDto;
import com.project.commentservice.comment.service.CommentService;
import com.project.commentservice.post.models.dto.CommentRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/comment")
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;


    @PostMapping("/{parentCommentId}/comment")
    public ResponseEntity<?> replyOnComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long parentCommentId) {
        try {
            commentRequestDto.setParentCommentId(parentCommentId);
            log.info("Comment on comment for id :{}", parentCommentId);
            return ResponseEntity.ok(commentService.commentOnComment(commentRequestDto));
        } catch (Exception e) {
            log.error("Exception occurred while replying on a comment and exception message :{}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{commentId}/react")
    public ResponseEntity<?> reactOnComment(@PathVariable Long commentId, @RequestBody CommentReactRequestDto commentReactRequestDto) {
        try {
            commentReactRequestDto.setCommentId(commentId);
            log.info("Reacting on comment for id :{}", commentId);
            return ResponseEntity.ok(commentService.reactOnComment(commentReactRequestDto));
        } catch (Exception e) {
            log.error("Exception occurred while reacting on a comment and exception message :{}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> getComment(@PathVariable Long commentId) {
        try {
            log.info("Getting comment details on comment for id :{}", commentId);
            return ResponseEntity.ok(commentService.getComment(commentId));
        } catch (Exception e) {
            log.error("Exception occurred while getting comment details and exception message :{}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/{commentId}/replies")
    public ResponseEntity<?> getCommentNextLevelReplies(@PathVariable Long commentId,
                                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int size,
                                                        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int page) {

        try {
            log.info("Getting comment replies on comment for id :{}", commentId);
            return ResponseEntity.ok(commentService.getCommentNextLevelReplies(commentId, page, size));
        } catch (Exception e) {
            log.error("Exception occurred while getting replies om comment and exception message :{}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{commentId}/react/{reactType}/users")
    public ResponseEntity<?> getUsersWrtReactType(@PathVariable Long commentId, @PathVariable int reactType) {
        try {
            log.info("Get users for react type :{} and commentId :{}", reactType, commentId);
            return ResponseEntity.ok(commentService.getUsersForReactType(commentId, reactType));
        } catch (Exception e) {
            log.error("Exception occurred while getting users who liked/dislike the comment :{}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}