package com.cqut.entity.dto.user;

import javax.validation.constraints.NotNull;

/**
 * 用于用户的查询和删除
 * */
public class UserIdDTO {

    @NotNull(message = "用户id不能为空")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
