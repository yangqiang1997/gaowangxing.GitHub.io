package com.cqut.auth.service;

import com.cqut.auth.entity.AuthUser;

import java.util.concurrent.TimeUnit;

public interface RedisService {

    /**
     * @param token
     * @param authUser
     * @param expirationTime 过期时间
     * @param timeUnit
     * */
    void setToken(String token, AuthUser authUser, long expirationTime, TimeUnit timeUnit);

    /**
     * @param token
     * */
    AuthUser getToken(String token);

    /**
     * @param token
     * @param expirationTime
     * @param timeUnit
     * */
    void expireToken(String token, long expirationTime, TimeUnit timeUnit);

    /**
     * @param token
     * */
    void deleteToken(String token);
}
