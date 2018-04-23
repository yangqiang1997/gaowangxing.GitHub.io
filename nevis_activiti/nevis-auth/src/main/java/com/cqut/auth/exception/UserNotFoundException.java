package com.cqut.auth.exception;

public class UserNotFoundException extends RuntimeException {

    private static String DEFAULT_MSG = "用户不存在";

    public UserNotFoundException() {
        super(DEFAULT_MSG);
    }
}
