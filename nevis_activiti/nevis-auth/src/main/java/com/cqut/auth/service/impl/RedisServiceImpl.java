package com.cqut.auth.service.impl;

import com.cqut.auth.entity.AuthUser;
import com.cqut.auth.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    private static String REDIS_TOKEN_PREFIX = "TOKEN_";

    @Autowired
    private RedisTemplate redisTemplate;

    private String getTokenKey(String token) {
        return REDIS_TOKEN_PREFIX + token;
    }

    /**
     * @param token
     * @param authUser
     * @param expirationTime 过期时间
     * @param timeUnit
     */
    @Override
    public void setToken(String token, AuthUser authUser, long expirationTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(getTokenKey(token), authUser);
        redisTemplate.expire(getTokenKey(token), expirationTime, timeUnit);
    }

    /**
     * @param token
     */
    @Override
    public AuthUser getToken(String token) {
        return (AuthUser) redisTemplate.opsForValue().get(getTokenKey(token));
    }

    /**
     * @param token
     * @param expirationTime
     * @param timeUnit
     */
    @Override
    public void expireToken(String token, long expirationTime, TimeUnit timeUnit) {
        expire(getTokenKey(token), expirationTime, timeUnit);
    }

    /**
     * @param token
     */
    @Override
    public void deleteToken(String token) {
        redisTemplate.delete(REDIS_TOKEN_PREFIX + token);
    }

    private void expire(String key, long expirationTime, TimeUnit timeUnit) {
        redisTemplate.expire(key, expirationTime, timeUnit);
    }
}
