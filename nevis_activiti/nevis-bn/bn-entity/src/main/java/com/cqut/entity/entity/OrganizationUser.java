package com.cqut.entity.entity;

import javax.validation.constraints.NotNull;

public class OrganizationUser {
    @NotNull(message = "userId不能为空")
    private String userId;

    @NotNull(message = "organizationId不能为空")
    private String organizationId;

    public OrganizationUser(String userId, String organizationId) {
        this.userId = userId;
        this.organizationId = organizationId;
    }

    public OrganizationUser() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }
}