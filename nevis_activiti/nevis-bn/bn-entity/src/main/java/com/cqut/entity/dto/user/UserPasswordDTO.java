package com.cqut.entity.dto.user;

import javax.validation.constraints.NotNull;

/**
 * 作者：谭勇
 * */
public class UserPasswordDTO {

    private String userId;

    @NotNull(message = "新密码不能为空")
    private String password;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
