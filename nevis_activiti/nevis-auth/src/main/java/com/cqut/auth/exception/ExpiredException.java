package com.cqut.auth.exception;

public class ExpiredException extends RuntimeException {

    private static String DEFAULT_MSG = "账号过期";

    public ExpiredException() {
        super(DEFAULT_MSG);
    }
}
