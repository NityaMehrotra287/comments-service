package com.project.commentservice.user.repository;

import com.project.commentservice.user.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository {
    void insertUser(String userName, String emailId);

    Optional<User> findById(Long id);

    List<String> getUserNamesFromIds(Set<Long> usersListByReactTypeOnPost);
}