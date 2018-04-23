package com.cqut.auth.exception;

public class InvalidTokenException extends RuntimeException {

    private static String DEFAULT_MSG = "token值无效或过期";

    public InvalidTokenException() {
        super(DEFAULT_MSG);
    }
}
