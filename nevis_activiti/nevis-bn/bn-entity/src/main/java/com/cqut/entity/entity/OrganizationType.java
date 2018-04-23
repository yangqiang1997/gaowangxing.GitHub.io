package com.cqut.entity.entity;

public class OrganizationType {
    private String organizationTypeId;

    private String organizationTypeName;

    private String organizationExtensionInfoTableName;

    private String addUrl;

    private String editUrl;

    private String detailUrl;

    public OrganizationType(String organizationTypeId, String organizationTypeName, String organizationExtensionInfoTableName, String addUrl, String editUrl, String detailUrl) {
        this.organizationTypeId = organizationTypeId;
        this.organizationTypeName = organizationTypeName;
        this.organizationExtensionInfoTableName = organizationExtensionInfoTableName;
        this.addUrl = addUrl;
        this.editUrl = editUrl;
        this.detailUrl = detailUrl;
    }

    public OrganizationType() {
        super();
    }

    public String getOrganizationTypeId() {
        return organizationTypeId;
    }

    public void setOrganizationTypeId(String organizationTypeId) {
        this.organizationTypeId = organizationTypeId == null ? null : organizationTypeId.trim();
    }

    public String getOrganizationTypeName() {
        return organizationTypeName;
    }

    public void setOrganizationTypeName(String organizationTypeName) {
        this.organizationTypeName = organizationTypeName == null ? null : organizationTypeName.trim();
    }

    public String getOrganizationExtensionInfoTableName() {
        return organizationExtensionInfoTableName;
    }

    public void setOrganizationExtensionInfoTableName(String organizationExtensionInfoTableName) {
        this.organizationExtensionInfoTableName = organizationExtensionInfoTableName == null ? null : organizationExtensionInfoTableName.trim();
    }

    public String getAddUrl() {
        return addUrl;
    }

    public void setAddUrl(String addUrl) {
        this.addUrl = addUrl == null ? null : addUrl.trim();
    }

    public String getEditUrl() {
        return editUrl;
    }

    public void setEditUrl(String editUrl) {
        this.editUrl = editUrl == null ? null : editUrl.trim();
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl == null ? null : detailUrl.trim();
    }
}