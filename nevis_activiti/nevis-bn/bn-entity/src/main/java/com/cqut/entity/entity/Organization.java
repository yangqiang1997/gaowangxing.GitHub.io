package com.cqut.entity.entity;

import com.cqut.entity.dto.group.Update;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Organization {
    @NotNull(message = "修改时ID不能为空",groups = Update.class)
    private String organizationId;

    private String organizationTypeId;

    private String organizationTypeName;

    private String organizationName;

    private String parentOrganizationId;

    private Boolean isExpired;

    private Boolean isLock;

    private String remark;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private Byte isDelete;

    public Organization(String organizationId, String organizationTypeId, String organizationName, String parentOrganizationId, Boolean isExpired, Boolean isLock, String remark, String createId, Date createTime, String updateId, Date updateTime, Byte isDelete) {
        this.organizationId = organizationId;
        this.organizationTypeId = organizationTypeId;
        this.organizationName = organizationName;
        this.parentOrganizationId = parentOrganizationId;
        this.isExpired = isExpired;
        this.isLock = isLock;
        this.remark = remark;
        this.createId = createId;
        this.createTime = createTime;
        this.updateId = updateId;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
    }

    public Organization() {
        super();
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }

    public String getOrganizationTypeId() {
        return organizationTypeId;
    }

    public void setOrganizationTypeId(String organizationTypeId) {
        this.organizationTypeId = organizationTypeId == null ? null : organizationTypeId.trim();
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName == null ? null : organizationName.trim();
    }

    public String getParentOrganizationId() {
        return parentOrganizationId;
    }

    public void setParentOrganizationId(String parentOrganizationId) {
        this.parentOrganizationId = parentOrganizationId == null ? null : parentOrganizationId.trim();
    }

    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
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
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getOrganizationTypeName() {
        return this.organizationTypeName = organizationTypeName == null ? null : organizationTypeName.trim();
    }

    public void setOrganizationTypeName(String organizationTypeName) {
        this.organizationTypeName = organizationTypeName;
    }
}