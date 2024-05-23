package com.project.commentservice.user.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUserRequestDto implements Serializable {

    @NotBlank(message = "User name is required")
    String userName;

    @NotBlank(message = "Email ID is required")
    @Email(message = "Invalid email format")
    String emailId;
}