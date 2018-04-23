package com.cqut.auth.service;

import com.cqut.auth.entity.AuthUser;

public interface AuthUserService {

    AuthUser loadAuthUserByUserName(String userName);
}
