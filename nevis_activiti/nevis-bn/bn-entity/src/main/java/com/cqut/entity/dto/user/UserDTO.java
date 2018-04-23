package com.cqut.entity.dto.user;

import com.cqut.entity.dto.group.Add;
import com.cqut.entity.dto.group.Update;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 用户新增
 * 作者：谭勇
 * */
public class UserDTO {

    //@NotNull(message = "修改时userId不能为空", groups = Update.class)
    private String userId;

    //@Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    //@NotNull(message = "新增时账号不能为空", groups = Add.class)
    private String account;

    //@NotNull(message = "新增时密码不能为空", groups = Add.class)
    private String password;

    //@NotNull(message = "新增时用户名不能为空", groups = Add.class)
    private String userName;

    private String userSex;

    private Date birthdate;

    /**
     * 电话号码和账号是一致的
     * */
    //@Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    private String phoneNumber;

    //@Email(message = "邮箱格式不正确")
    private String userEmail;

    private String qqNumber;

    private String wechartNumber;

    private String headPortrait;

    private Boolean workingStatus = Boolean.FALSE;

    private Boolean isExpired = Boolean.FALSE;

    private Boolean isLock = Boolean.FALSE;

    private Boolean isDisabled = Boolean.FALSE;

    private String userRemark;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private Boolean isDelete = Boolean.FALSE;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getWechartNumber() {
        return wechartNumber;
    }

    public void setWechartNumber(String wechartNumber) {
        this.wechartNumber = wechartNumber;
    }

    public Boolean getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(Boolean workingStatus) {
        this.workingStatus = workingStatus;
    }

    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Boolean isExpired) {
        isExpired = isExpired;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }

    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Boolean isDisabled) {
        isDisabled = isDisabled;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }
}
