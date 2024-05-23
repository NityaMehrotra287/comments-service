package com.project.commentservice.user.repository;

import com.project.commentservice.user.model.User;

import java.util.Optional;

public interface UserRepository {
    void insertUser(String userName, String emailId);

    Optional<User> findById(Long id);
}