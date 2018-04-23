package com.cqut.entity.dto.user;

import javax.validation.constraints.Pattern;

public class UserAccountDTO {

    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
