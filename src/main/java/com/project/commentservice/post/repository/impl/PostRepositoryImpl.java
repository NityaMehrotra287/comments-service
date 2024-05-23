package com.project.commentservice.post.repository.impl;

import com.project.commentservice.post.models.Post;
import com.project.commentservice.post.repository.PostRepository;
import com.project.commentservice.user.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    @Modifying
    @Transactional
    public void createPost(Post post) {
        Query query = entityManager.createNativeQuery("INSERT into posts (content, added_by, created_at, updated_at) VALUES (?, ?, ?,?)", Post.class);
        query.setParameter(1, post.getContent());
        query.setParameter(2, post.getAddedBy());
        query.setParameter(3, post.getCreatedAt());
        query.setParameter(4, post.getUpdatedAt());
        query.executeUpdate();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        Post post = entityManager.find(Post.class, id);
        return Optional.ofNullable(post);
    }
}
