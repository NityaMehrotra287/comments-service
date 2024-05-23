package com.project.commentservice.likeDislike.models;

import com.project.commentservice.exceptions.custom.InvalidReactTypeException;
import lombok.Getter;

@Getter
public enum ReactType {
    DISLIKE(0),
    LIKE(1),
    ;

    final int val;

    ReactType(int val) {
        this.val = val;
    }

    public static void validateReactType(int reactType) {
        boolean isValid = false;
        for (ReactType value : ReactType.values()) {
            if (value.getVal() == reactType) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new InvalidReactTypeException(String.format("ReactType %s passed is invalid.", reactType));
        }
    }
}
