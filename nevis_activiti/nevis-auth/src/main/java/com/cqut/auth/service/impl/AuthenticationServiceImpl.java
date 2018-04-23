package com.cqut.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqut.auth.entity.AuthUser;
import com.cqut.auth.exception.*;
import com.cqut.auth.service.AuthUserService;
import com.cqut.auth.service.AuthenticationService;
import com.cqut.auth.service.RedisService;
import com.cqut.auth.service.TokenService;
import com.cqut.entity.dto.login.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.expirationTime}")
    private long expirationTime;

    @Override
    public JSONObject login(LoginDTO loginDTO) {
        AuthUser authUser = authUserService.loadAuthUserByUserName(loginDTO.getUsername());

        validateLogin(authUser, loginDTO);

        String token = tokenService.generateToken(authUser.getAccount(), "userId", authUser.getUserId());
        authUser.setToken(token);
        afterLogin(authUser);

        return result(authUser);
    }

    private void validateLogin(AuthUser authUser, LoginDTO loginDTO) {
        if (!passwordEncoder.matches(loginDTO.getPassword(), authUser.getPassword())) {
            throw new IncorrectPasswordException();
        }

        if (authUser.isExpired()) {
            throw new ExpiredException();
        }

        if (authUser.isLock()) {
            throw new LockException();
        }

        if (authUser.isDisabled()) {
            throw new DisabledException();
        }
    }

    private void afterLogin(AuthUser authUser) {
        redisService.setToken(authUser.getToken(), authUser, expirationTime, TimeUnit.MINUTES);
    }

    private JSONObject result(AuthUser authUser) {
        JSONObject result = new JSONObject();
        result.put("token", authUser.getToken());
        result.put("userName", authUser.getUserName());
        result.put("authorities", authUser.getAuthorities());
        return result;
    }

    @Override
    public AuthUser findUserByToken(String token) {
        //当authUser为空时，说明token无效或过期，在用到这个方法是需要判断
        AuthUser authUser = redisService.getToken(token);

        if (authUser != null) {
            redisService.expireToken(token, expirationTime, TimeUnit.MINUTES);
        }

        return authUser;
    }

    @Override
    public void logout(String token) {
        redisService.deleteToken(token);
    }
}
