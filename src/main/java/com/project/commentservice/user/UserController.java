package com.project.commentservice.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.project.commentservice.user.model.dto.CheckResponseDto;
import com.project.commentservice.user.model.dto.CreateUserRequestDto;
import com.project.commentservice.user.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/sanity")
    public ResponseEntity<CheckResponseDto> isServerRunning() {
        CheckResponseDto responseDto = new CheckResponseDto();
        responseDto.setStatus("Service is up and running");
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequestDto createUserDto) {
        try {
            log.info("Adding user for userName:{} email:{}", createUserDto.getUserName(), createUserDto.getEmailId());
            return ResponseEntity.ok(userService.create(createUserDto));
        } catch (Exception e) {
            log.error("Exception occurred while saving user and exception message :{}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
