package com.project.commentservice.user.service.impl;

import com.project.commentservice.user.model.dto.CreateUserRequestDto;
import com.project.commentservice.user.model.dto.CreateUserResponseDto;
import com.project.commentservice.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    public UserServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate_Success() {
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setUserName("testUser");
        requestDto.setEmailId("test@example.com");

        doNothing().when(userRepository).insertUser(requestDto.getUserName(), requestDto.getEmailId());
        CreateUserResponseDto responseDto = userService.create(requestDto);
        verify(userRepository, times(1)).insertUser(requestDto.getUserName(), requestDto.getEmailId());
        assertEquals("User added successfully", responseDto.getMessage());
    }

    @Test
    public void testCreate_Failure() {
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setUserName("testUser");
        requestDto.setEmailId("test@example.com");

        doThrow(new RuntimeException("Simulated exception")).when(userRepository).insertUser(requestDto.getUserName(), requestDto.getEmailId());
        CreateUserResponseDto responseDto = userService.create(requestDto);
        assertEquals("Failed to add user: Simulated exception", responseDto.getMessage());
        verify(userRepository, times(1)).insertUser(requestDto.getUserName(), requestDto.getEmailId());
    }
}