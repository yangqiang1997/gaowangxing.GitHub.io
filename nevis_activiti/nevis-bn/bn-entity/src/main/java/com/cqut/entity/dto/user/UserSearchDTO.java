package com.cqut.entity.dto.user;

import com.cqut.util.string.StringUtil;

/**
 * 修改人：罗杏函
 * 修改日期：2018-04-07
 */
public class UserSearchDTO {

    private String account;

    private Boolean isLock;

    private String userName;

    private String org;

    private String role;

    public String getAccount() {return account;}

    public void setAccount(String account) { this.account = StringUtil.emptyStr2NullStr(account);}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = StringUtil.emptyStr2NullStr(userName);
    }

    public String getOrg() {return org;};

    public void setOrg(String org) { this.org = StringUtil.emptyStr2NullStr(org);}

    public String getRole() {return role;}

    public void setRole(String role) { this.role = StringUtil.emptyStr2NullStr(role);}

    public Boolean getIsLock() {return isLock;}

    public void setIsLock(Boolean isLock) { this.isLock = isLock;}
}
