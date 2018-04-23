package com.cqut.auth.exception;

public class IncorrectPasswordException extends RuntimeException {

    private static String DEFAULT_MSG = "密码不正确";

    public IncorrectPasswordException() {
        super(DEFAULT_MSG);
    }
}
