package com.cqut.entity.dto.organization;

public class DeleteUserDTO {
    private String organizationId;

    private String userId;

    public DeleteUserDTO(String organizationId, String userId){
        this.organizationId = organizationId;
        this.userId = userId;
    }
    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
