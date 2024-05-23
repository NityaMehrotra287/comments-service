package com.project.commentservice.comment.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "comments")
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long postId;

    private Long parentId;

    private String content;
    private Long addedBy;

    private Date createdAt;
    private Date updatedAt;
}