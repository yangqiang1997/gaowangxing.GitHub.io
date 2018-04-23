package com.cqut.entity.dto.organization;

import com.cqut.entity.entity.Organization;
import com.cqut.util.constants.SystemConstants;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddOrg {
    @NotNull(message = "类型不能为空")
    private String organizationTypeId;

    @NotNull(message = "名字不能为空")
    private String organizationName;

    private String parentOrganizationId;

    private Boolean isExpired;

    private Boolean isLock;

    private String remark;

    private String createId;

    private Date createTime = new Date();

    private Byte isDelete = SystemConstants.FALSE_FLAG;

    public AddOrg(String organizationTypeId, String organizationName, String parentOrganizationId, Boolean isExpired, Boolean isLock, String remark, String createId) {
        this.organizationTypeId = organizationTypeId;
        this.organizationName = organizationName;
        this.parentOrganizationId = parentOrganizationId;
        this.isExpired = isExpired;
        this.isLock = isLock;
        this.remark = remark;
        this.createId = createId;
    }

    public Organization toOrganization(){
        Organization organization = new Organization();
        organization.setOrganizationName(this.organizationName);
        organization.setIsDelete(this.isDelete);
        organization.setRemark(this.remark);
        organization.setOrganizationTypeId(this.organizationTypeId);
        organization.setParentOrganizationId(this.parentOrganizationId);
        organization.setCreateTime(this.createTime);
        organization.setIsExpired(this.isExpired);
        organization.setIsLock(this.isLock);
        organization.setCreateId(this.createId);

        return organization;
    }

    public AddOrg() {
        super();
    }

    public void setOrganizationTypeId(String organizationTypeId) {
        this.organizationTypeId = organizationTypeId == null ? null : organizationTypeId.trim();
    }


    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName == null ? null : organizationName.trim();
    }


    public void setParentOrganizationId(String parentOrganizationId) {
        this.parentOrganizationId = parentOrganizationId == null ? null : parentOrganizationId.trim();
    }


    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }


    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
