package com.cqut.entity.dto.organization;

import com.cqut.entity.entity.Organization;

import java.util.Date;
import java.util.List;

public class FindOrgList {
    private String organizationId;

    private String organizationTypeId;

    private String organizationName;

    private String parentOrganizationId;

    private String organizationTypeName;

    private Boolean isExpired;

    private Boolean isLock;

    private String remark;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private Byte isDelete;

    private List<FindOrgList> children;

    public FindOrgList(Organization organization){
        this.organizationId = organization.getOrganizationId();
        this.organizationTypeId = organization.getOrganizationTypeId();
        this.organizationName = organization.getOrganizationName();
        this.parentOrganizationId = organization.getParentOrganizationId();
        this.isExpired = organization.getIsExpired();
        this.isLock = organization.getIsLock();
        this.remark = organization.getRemark();
        this.createId = organization.getCreateId();
        this.createTime = organization.getCreateTime();
        this.updateId = organization.getUpdateId();
        this.updateTime = organization.getUpdateTime();
        this.isDelete = organization.getIsDelete();
        this.organizationTypeName = organization.getOrganizationTypeName();
    }


    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationTypeId() {
        return organizationTypeId;
    }

    public void setOrganizationTypeId(String organizationTypeId) {
        this.organizationTypeId = organizationTypeId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getParentOrganizationId() {
        return parentOrganizationId;
    }

    public void setParentOrganizationId(String parentOrganizationId) {
        this.parentOrganizationId = parentOrganizationId;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    public Boolean getLock() {
        return isLock;
    }

    public void setLock(Boolean lock) {
        isLock = lock;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public List<FindOrgList> getChildren() {
        return children;
    }

    public void setChildren(List<FindOrgList> children) {
        this.children = children;
    }

    public String getOrganizationTypeName() {
        return organizationTypeName;
    }

    public void setOrganizationTypeName(String organizationTypeName) {
        this.organizationTypeName = organizationTypeName;
    }
}
