package com.project.commentservice.user.service;

import com.project.commentservice.exceptions.custom.UserNotFoundException;
import com.project.commentservice.user.model.dto.CreateUserRequestDto;
import com.project.commentservice.user.model.dto.CreateUserResponseDto;

public interface UserService {
    CreateUserResponseDto create(CreateUserRequestDto createUserDto);

    void validateUser(Long userId) throws UserNotFoundException;
}