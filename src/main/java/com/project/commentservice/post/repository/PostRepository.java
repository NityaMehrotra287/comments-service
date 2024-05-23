package com.project.commentservice.post.repository;

import com.project.commentservice.post.models.Post;

import java.util.Optional;

public interface PostRepository {

    void createPost(Post post);

    Optional<Post> getPostById(Long postId);
}
