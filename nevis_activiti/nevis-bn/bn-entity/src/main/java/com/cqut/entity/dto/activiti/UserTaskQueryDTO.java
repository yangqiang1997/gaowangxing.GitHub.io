package com.cqut.entity.dto.activiti;

/**
 * 杨强
 * 2018.4.16
 */
public class UserTaskQueryDTO {
    /**
     * 当前用户的id
     */
    private String userId;

    /**
     * 当前角色的角色类型
     */
    private String roleType;

    /**
     * 最上级组织机构
     */
    private String organization;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
