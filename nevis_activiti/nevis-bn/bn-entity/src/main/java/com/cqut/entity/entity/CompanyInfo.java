package com.cqut.entity.entity;

public class CompanyInfo {
    private String companyInfoId;

    private String companyRegisteredAddress;

    private String area;

    private String businessLicense;

    private String uniformIdentificationCode;

    private String companyLegalPerson;

    private String companyLegalPersonPhoneNumber;

    private Boolean companyStatus;

    private String organizationId;

    public CompanyInfo(String companyInfoId, String companyRegisteredAddress, String area, String businessLicense, String uniformIdentificationCode, String companyLegalPerson, String companyLegalPersonPhoneNumber, Boolean companyStatus, String organizationId) {
        this.companyInfoId = companyInfoId;
        this.companyRegisteredAddress = companyRegisteredAddress;
        this.area = area;
        this.businessLicense = businessLicense;
        this.uniformIdentificationCode = uniformIdentificationCode;
        this.companyLegalPerson = companyLegalPerson;
        this.companyLegalPersonPhoneNumber = companyLegalPersonPhoneNumber;
        this.companyStatus = companyStatus;
        this.organizationId = organizationId;
    }

    public CompanyInfo() {
        super();
    }

    public String getCompanyInfoId() {
        return companyInfoId;
    }

    public void setCompanyInfoId(String companyInfoId) {
        this.companyInfoId = companyInfoId == null ? null : companyInfoId.trim();
    }

    public String getCompanyRegisteredAddress() {
        return companyRegisteredAddress;
    }

    public void setCompanyRegisteredAddress(String companyRegisteredAddress) {
        this.companyRegisteredAddress = companyRegisteredAddress == null ? null : companyRegisteredAddress.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getUniformIdentificationCode() {
        return uniformIdentificationCode;
    }

    public void setUniformIdentificationCode(String uniformIdentificationCode) {
        this.uniformIdentificationCode = uniformIdentificationCode == null ? null : uniformIdentificationCode.trim();
    }

    public String getCompanyLegalPerson() {
        return companyLegalPerson;
    }

    public void setCompanyLegalPerson(String companyLegalPerson) {
        this.companyLegalPerson = companyLegalPerson == null ? null : companyLegalPerson.trim();
    }

    public String getCompanyLegalPersonPhoneNumber() {
        return companyLegalPersonPhoneNumber;
    }

    public void setCompanyLegalPersonPhoneNumber(String companyLegalPersonPhoneNumber) {
        this.companyLegalPersonPhoneNumber = companyLegalPersonPhoneNumber == null ? null : companyLegalPersonPhoneNumber.trim();
    }

    public Boolean getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Boolean companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }
}