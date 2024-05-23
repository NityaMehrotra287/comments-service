package com.project.commentservice.exceptions.custom;

import com.project.commentservice.exceptions.ParentSocialMediaException;

public class InvalidReactTypeException extends ParentSocialMediaException {
    public InvalidReactTypeException(String message) {
        super(message);
    }
}