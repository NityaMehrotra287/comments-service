package com.project.commentservice.user.repository.impl;

import com.project.commentservice.user.model.User;
import com.project.commentservice.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Modifying
    @Transactional
    public void insertUser(String userName, String email) {
        Query query = entityManager.createNativeQuery("INSERT into users (user_name, email_id) VALUES (?, ?)");
        query.setParameter(1, userName);
        query.setParameter(2, email);
        query.executeUpdate();
    }

    @Override

    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<String> getUserNamesFromIds(Set<Long> usersListByReactTypeOnPost) {
        Query query = entityManager.createNativeQuery("SELECT user_name from users u where u.id IN (?)");
        query.setParameter(1, usersListByReactTypeOnPost);
        return query.getResultList();
    }
}
