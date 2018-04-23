package com.cqut.entity.entity;

public class DataScopeRole {
    private String roleId;

    private String organizationId;

    public DataScopeRole(String roleId, String organizationId) {
        this.roleId = roleId;
        this.organizationId = organizationId;
    }

    public DataScopeRole() {
        super();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }
}