package com.cqut.auth.service;

import com.alibaba.fastjson.JSONObject;
import com.cqut.auth.entity.AuthUser;
import com.cqut.entity.dto.login.LoginDTO;

public interface AuthenticationService {

    JSONObject login(LoginDTO loginDTO);

    AuthUser findUserByToken(String token);

    void logout(String token);
}
