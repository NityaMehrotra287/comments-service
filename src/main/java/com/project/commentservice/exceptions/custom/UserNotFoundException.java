package com.project.commentservice.exceptions.custom;

import com.project.commentservice.exceptions.ParentSocialMediaException;

public class UserNotFoundException extends ParentSocialMediaException {
    public UserNotFoundException(String message) {
        super(message);
    }
}