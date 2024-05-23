package com.project.commentservice.exceptions.custom;

import com.project.commentservice.exceptions.ParentSocialMediaException;

public class PostNotFoundException extends ParentSocialMediaException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
