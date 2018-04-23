package com.cqut;

import com.cqut.auth.exception.*;
import com.cqut.entity.global.JSONResult;
import com.cqut.util.constants.ResponseCodeConstants;
import com.cqut.util.exception.BusinessException;
import com.cqut.util.exception.NonDataScopeException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JSONResult handleException(Exception e) {
        e.printStackTrace();
        return new JSONResult(e.getMessage(), ResponseCodeConstants.FAILURE);
    }

    @ExceptionHandler(NonDataScopeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JSONResult handleNonDataScopeException(NonDataScopeException e) {
        return new JSONResult(e.getMessage(), ResponseCodeConstants.FAILURE);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JSONResult handleBusinessException(BusinessException e) {
        e.printStackTrace();
        return new JSONResult(e.getMessage(), ResponseCodeConstants.FAILURE);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JSONResult handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return new JSONResult(e.getMessage(), ResponseCodeConstants.FAILURE);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JSONResult handleUserNotFoundException(UserNotFoundException e) {
        return new JSONResult(e.getMessage(), ResponseCodeConstants.LOGIN_USER_NOT_FOUND);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JSONResult handleIncorrectPasswordException(IncorrectPasswordException e) {
        return new JSONResult(e.getMessage(), ResponseCodeConstants.LOGIN_INCORRECT_PASSWORD);
    }

    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JSONResult handleDisabledException(DisabledException e) {
        return new JSONResult(e.getMessage(), ResponseCodeConstants.LOGIN_USER_DISABLED);
    }

    @ExceptionHandler(ExpiredException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JSONResult handleExpiredException(ExpiredException e) {
        return new JSONResult(e.getMessage(), ResponseCodeConstants.LOGIN_USER_EXPIRED);
    }

    @ExceptionHandler(LockException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JSONResult handleLockException(LockException e) {
        return new JSONResult(e.getMessage(), ResponseCodeConstants.LOGIN_USER_LOCKED);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JSONResult handleBindException(BindException e) {
        return new JSONResult(e.getMessage(), ResponseCodeConstants.BIND_ERROR);
    }
}
