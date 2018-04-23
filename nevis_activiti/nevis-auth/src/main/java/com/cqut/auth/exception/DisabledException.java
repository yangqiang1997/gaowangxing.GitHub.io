package com.cqut.auth.exception;

public class DisabledException extends RuntimeException {

    private static String DEFAULT_MSG = "账号被禁用";

    public DisabledException() {
        super(DEFAULT_MSG);
    }
}
