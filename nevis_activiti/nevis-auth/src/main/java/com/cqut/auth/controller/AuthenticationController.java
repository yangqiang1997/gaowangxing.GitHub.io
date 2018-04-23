package com.cqut.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqut.auth.entity.AuthUser;
import com.cqut.auth.service.AuthenticationService;
import com.cqut.entity.dto.login.LoginDTO;
import com.cqut.entity.global.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONResult login(LoginDTO loginDTO) {
        JSONObject result = authenticationService.login(loginDTO);
        return new JSONResult(result);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public JSONResult logout(@AuthenticationPrincipal AuthUser authUser) {
        authenticationService.logout(authUser.getToken());
        return new JSONResult();
    }
}
