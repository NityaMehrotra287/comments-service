package com.project.commentservice.likeDislike.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@Table(name = "comments_like_dislike")
public class CommentsLikeDislike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long commentId;

    private Long addedBy;

    private int like;

    private Date createdAt;
    private Date updatedAt;
}
