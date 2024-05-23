package com.project.commentservice.likeDislike.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@Table(name = "post_like_dislike")
public class PostLikeDislike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;


    private int like;

    private Long addedBy;

    private Date createdAt;
    private Date updatedAt;
}
