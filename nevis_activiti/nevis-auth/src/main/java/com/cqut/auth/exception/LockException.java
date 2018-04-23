package com.cqut.auth.exception;

public class LockException extends RuntimeException {

    private static String DEFAULT_MSG = "账号被冻结";

    public LockException() {
        super(DEFAULT_MSG);
    }
}
