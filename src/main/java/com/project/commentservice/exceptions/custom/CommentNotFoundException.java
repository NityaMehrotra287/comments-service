package com.project.commentservice.exceptions.custom;

import com.project.commentservice.exceptions.ParentSocialMediaException;

public class CommentNotFoundException extends ParentSocialMediaException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
