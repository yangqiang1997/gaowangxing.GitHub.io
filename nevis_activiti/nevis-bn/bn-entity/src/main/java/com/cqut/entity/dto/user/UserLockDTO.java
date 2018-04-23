package com.cqut.entity.dto.user;

/**
 * 创建人:罗杏函
 * 创建时间:2018-04-10
 */
public class UserLockDTO {

    private String userId;

    private Boolean isLock;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }
}
