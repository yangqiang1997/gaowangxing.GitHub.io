package com.cqut.entity.entity;

public class ResourceRole {
    private String roleId;

    private String resourceId;

    public ResourceRole(String roleId, String resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
    }

    public ResourceRole() {
        super();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }
}