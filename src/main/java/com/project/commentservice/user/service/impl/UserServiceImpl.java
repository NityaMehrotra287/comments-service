package com.project.commentservice.user.service.impl;

import com.project.commentservice.exceptions.custom.UserNotFoundException;
import com.project.commentservice.user.model.User;
import com.project.commentservice.user.model.dto.CreateUserRequestDto;
import com.project.commentservice.user.model.dto.CreateUserResponseDto;
import com.project.commentservice.user.repository.UserRepository;
import com.project.commentservice.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateUserResponseDto create(CreateUserRequestDto createUserDto) {
        CreateUserResponseDto response = new CreateUserResponseDto();
        try {
            userRepository.insertUser(createUserDto.getUserName(), createUserDto.getEmailId());
            response.setMessage("User added successfully");
        } catch (Exception e) {
            response.setMessage("Failed to add user: " + e.getMessage());
        }
        return response;
    }


    @Override
    public void validateUser(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(String.format("User with userId %s is not present.", userId));
        }
    }

    @Override
    public List<String> getUserNamesFromIds(Set<Long> usersListByReactTypeOnPost) {
        return userRepository.getUserNamesFromIds(usersListByReactTypeOnPost);
    }
}
